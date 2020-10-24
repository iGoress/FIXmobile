package br.com.luisbocchini.primeiroapp

import android.app.Application
import java.lang.IllegalStateException

class LMSApplication: Application() {

    override fun onCreate(){
        super.onCreate()
        appInstance = this
    }

    companion object{
        private var appInstance: LMSApplication? = null
        fun getInstance(): LMSApplication{
            if(appInstance == null){
                throw IllegalStateException("Configure application no Manifest")
            }

            return appInstance!!
        }
    }
}