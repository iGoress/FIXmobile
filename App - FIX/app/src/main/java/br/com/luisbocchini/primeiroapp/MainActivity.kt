package br.com.luisbocchini.primeiroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.button3
import kotlinx.android.synthetic.main.login.login
import kotlinx.android.synthetic.main.login.senha
import kotlinx.android.synthetic.main.login_constraint.*

class MainActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_constraint)

        val host = "http://luisbocchini01.pythonanywhere.com/"
        val TAG = "WS_LMSApp"

        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome  = Prefs.getString("lembrarNome")
            var lembrarSenha  = Prefs.getString("lembrarSenha")
            login.setText(lembrarNome)
            senha.setText(lembrarSenha)
            checkBox.isChecked = lembrar

        }

    button3.setOnClickListener {
        var textLogin = login.text.toString()
        var textSenha = senha.text.toString()

        // armazenar valor do checkbox
        Prefs.setBoolean("lembrar", checkBox.isChecked)
        // verificar se é para pembrar nome e senha
        if (checkBox.isChecked) {
            Prefs.setString("lembrarNome", textLogin)
            Prefs.setString("lembrarSenha", textSenha)
        } else{
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

        Thread {
            val result = LoginService.getUsuario(textLogin, textSenha)
            runOnUiThread {
                if (result == "true") {
                    Toast.makeText(this, "Usuário correto", Toast.LENGTH_LONG).show()
                    var intent = Intent(this, TelaInicialActivity::class.java)
                    intent.putExtra("nome_usuario", textLogin)
                    intent.putExtra("numero", 10)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show()
                }
            }

        }.start()
    }


    }
}