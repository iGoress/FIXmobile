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

class TextoActionBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_texto_action_bar)

        val args = intent.extras
        val texto = args?.getString("texto_actionBar")

        setSupportActionBar(toolbar_view)

        supportActionBar?.title = texto
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }


}