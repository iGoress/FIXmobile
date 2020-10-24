package br.com.luisbocchini.primeiroapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.Timer
import kotlin.concurrent.schedule




open class TelaInicialActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() = this
    private var empresas = listOf<Empresas>()
    private var REQUEST_REMOVE= 2
    private var REQUEST_CADASTRO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

       // val botaoClinicas = btnClinicas.text.toString()
       // val botaoUsuarios = btnUsuarios.text.toString()
        //val botaoContato = btnContato.text.toString()
        //val botaoEmpresas = btnEmpresas.text.toString()
        val args = intent.extras
        val nome = args?.getString("nome_usuario")
        Toast.makeText(this, "Usuário: $nome", Toast.LENGTH_LONG).show()

        setSupportActionBar(toolbar_view)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //btnClinicas.setOnClickListener{
          //  var intent = Intent(this, TextoActionBarActivity::class.java)
            //intent.putExtra("texto_actionBar", botaoClinicas)
            //startActivity(intent)
        //}
        //btnUsuarios.setOnClickListener{
          //  var intent = Intent(this, TextoActionBarActivity::class.java)
            //intent.putExtra("texto_actionBar", botaoUsuarios)
            //tartActivity(intent)
        //}
        //btnContato.setOnClickListener{
          //  var intent = Intent(this, TextoActionBarActivity::class.java)
            //intent.putExtra("texto_actionBar", botaoContato)
            //startActivity(intent)
        //}


        configuraMenuLateral()
        // configurar cardview
        recyclerEmpresas?.layoutManager = LinearLayoutManager(context)
        recyclerEmpresas?.itemAnimator = DefaultItemAnimator()
        recyclerEmpresas?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        // task para recuperar as empresas
        taskEmpresas()
    }
    fun taskEmpresas() {
        Thread {
            this.empresas = EmpresaService.getEmpresas(context)
            runOnUiThread {
                // atualizar lista
                recyclerEmpresas?.adapter = EmpresaAdapter(empresas) { onClickEmpresa(it) }
            }
        }.start()
    }

    // tratamento do evento de clicar em uma empresa
    fun onClickEmpresa(empresa: Empresas) {
        Toast.makeText(context, "Clicou empresa ${empresa.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, EmpresaActivity::class.java)
        intent.putExtra("empresa", empresa)
        startActivityForResult(intent, REQUEST_REMOVE)
    }

    // configuraçao do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {

        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar_view, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    // método que deve ser implementado quando a activity implementa a interface NavigationView.OnNavigationItemSelectedListener
    // para tratar os eventos de clique no menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_empresas -> {
                Toast.makeText(this, "Clicou empresas", Toast.LENGTH_SHORT).show()
            }


            R.id.nav_usuarios -> {
                Toast.makeText(this, "Clicou usuários", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, UsuariosActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_configuracao -> {
                Toast.makeText(this, "Clicou configurações", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, ConfigActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_sair -> {
                Toast.makeText(this, "Clicou sair", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        // fecha menu depois de tratar o evento
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if(id == R.id.action_config){
            Toast.makeText(this, "Botão config", Toast.LENGTH_LONG).show()
        }
        else if(id == android.R.id.home){
            finish()
        }
        else if(id == R.id.action_configuracao){
            var intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }
        else if(id == R.id.action_sair){
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else if(id == R.id.action_atualizar){
            progress_bar.visibility = View.VISIBLE
            Timer("SettingUp", false).schedule(6000){
                progress_bar.visibility = View.INVISIBLE
            }
        }else if (id == R.id.action_adicionar) {
            // iniciar activity de cadastro
            val intent = Intent(context, EmpresaCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        }
        return super.onOptionsItemSelected(item)
    }

    // esperar o retorno do cadastro da disciplina
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            // atualizar lista de disciplinas
            taskEmpresas()
        }
    }
}