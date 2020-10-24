package br.com.luisbocchini.primeiroapp

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_empresas.*
import kotlinx.android.synthetic.main.toolbar.*

class EmpresaActivity : DebugActivity() {

    private val context: Context get() = this
    var empresa: Empresas? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresas)

        // recuperar onjeto de empresa da Intent
        empresa = intent.getSerializableExtra("empresa") as Empresas

        // configurar título com nome da Empresa e botão de voltar da Toobar
        // colocar toolbar
        setSupportActionBar(toolbar_view)

        // alterar título da ActionBar
        supportActionBar?.title = empresa?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados da Empresa
        nomeEmpresa.text = empresa?.nome
        Picasso.with(this).load(empresa?.foto).fit().into(imagemEmpresa,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })

    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_empresa, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado
        // remover a disciplina no WS
        if  (id == R.id.action_remover) {
            // alerta para confirmar a remeção
            // só remove se houver confirmação positiva
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir a empresa?")
                .setPositiveButton("Sim") {
                        dialog, which ->
                    dialog.dismiss()
                    taskExcluir()
                }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                }.create().show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.empresa != null && this.empresa is Empresas) {
            // Thread para remover a empresa
            Thread {
                EmpresaService.delete(this.empresa as Empresas)
                runOnUiThread {
                    // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }
}
