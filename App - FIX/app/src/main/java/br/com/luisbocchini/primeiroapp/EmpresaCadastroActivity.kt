package br.com.luisbocchini.primeiroapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro_empresa.*

class EmpresaCadastroActivity : DebugActivity() {
    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_empresa)
        setTitle("Nova Empresa")

        salvarEmpresa.setOnClickListener {
            val empresa = Empresas()
            empresa.nome = nomeEmpresa.text.toString()
            empresa.foto = urlFoto.text.toString()

            if(nomeEmpresa.text.toString() == "" || urlFoto.text.toString() == ""){
                Toast.makeText(context, "Campos inválidos", Toast.LENGTH_SHORT).show()
            }else{
                taskAtualizar(empresa)
            }
        }
    }

    private fun taskAtualizar(disciplina: Empresas) {
        // Thread para salvar a discilpina
        Thread {
            EmpresaService.save(disciplina)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
