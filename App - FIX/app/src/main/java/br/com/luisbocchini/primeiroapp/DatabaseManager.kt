package br.com.luisbocchini.primeiroapp

import androidx.room.Room

object DatabaseManager {

    private var dbInstance: LMSDatabase

    init{
        val contexto = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                contexto,
                LMSDatabase::class.java,
                "lms.sqllite"
        ).build()
    }

    fun getDisciplinaDAO(): EmpresaDAO{
        return dbInstance.disciplinaDAO()
    }
}