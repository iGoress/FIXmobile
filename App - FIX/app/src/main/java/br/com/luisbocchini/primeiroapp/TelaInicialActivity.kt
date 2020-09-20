package br.com.luisbocchini.primeiroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.Timer
import kotlin.concurrent.schedule

class TelaInicialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        val botaoClinicas = btnClinicas.text.toString()
        val botaoUsuarios = btnUsuarios.text.toString()
        val botaoContato = btnContato.text.toString()
        val args = intent.extras
        val nome = args?.getString("nome_usuario")
        Toast.makeText(this, "Usuário: $nome", Toast.LENGTH_LONG).show()

        setSupportActionBar(toolbar_view)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnClinicas.setOnClickListener{
            var intent = Intent(this, TextoActionBarActivity::class.java)
            intent.putExtra("texto_actionBar", botaoClinicas)
            startActivity(intent)
        }
        btnUsuarios.setOnClickListener{
            var intent = Intent(this, TextoActionBarActivity::class.java)
            intent.putExtra("texto_actionBar", botaoUsuarios)
            startActivity(intent)
        }
        btnContato.setOnClickListener{
            var intent = Intent(this, TextoActionBarActivity::class.java)
            intent.putExtra("texto_actionBar", botaoContato)
            startActivity(intent)
        }
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
        }
        return super.onOptionsItemSelected(item)
    }
}