package br.com.luisbocchini.primeiroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_constraint)

    button3.setOnClickListener{
        var textLogin = login.text.toString()
        var textSenha = senha.text

        if(textLogin.toString() == "aluno" && textSenha.toString() == "impacta"){
            var intent = Intent(this, TelaInicialActivity::class.java)
            intent.putExtra("nome_usuario", textLogin)
            intent.putExtra("numero", 10)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show()
        }
      }
    }
}