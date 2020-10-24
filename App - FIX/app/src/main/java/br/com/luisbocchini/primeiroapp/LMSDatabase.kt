package br.com.luisbocchini.primeiroapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Empresas::class), version = 1)
abstract class LMSDatabase:RoomDatabase() {
    abstract fun disciplinaDAO(): EmpresaDAO

}