package br.com.luisbocchini.primeiroapp

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_empresas.*
import kotlinx.android.synthetic.main.toolbar.*

class EmpresaActivity : DebugActivity() {

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
}
