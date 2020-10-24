package br.com.luisbocchini.primeiroapp

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object LoginService {

    //TROQUE PELA URL DE ONDE ESTÁ O WS
    // Veja um exemplo no repositório https://github.com/fesousa/aula-android-kotlin-api
    val host = "https://luisbocchini01.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getUsuario (nome: String, senha: String): String {
        val nome_usuario = nome.toString()
        val senha_usuario = senha.toString()
        //if (AndroidUtils.isInternetDisponivel(context)) {
        val url = "$host/valida_usuario/$nome_usuario/$senha_usuario"
        val json = HttpHelper.get(url)
        return parserJson(json)
        //} else {
        //  return ArrayList<Disciplina>()
        //}
    }


    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}
