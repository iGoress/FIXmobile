package br.com.luisbocchini.primeiroapp

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_empresa.*

class EmpresaCadastroActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_empresa)
        setTitle("Nova Empresa")

        salvarEmpresa.setOnClickListener {
            val empresa = Empresas()
            empresa.nome = nomeEmpresa.text.toString()
            empresa.foto = urlFoto.text.toString()

            taskAtualizar(empresa)
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
