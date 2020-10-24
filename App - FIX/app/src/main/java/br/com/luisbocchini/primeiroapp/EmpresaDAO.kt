package br.com.luisbocchini.primeiroapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmpresaDAO {

    @Query("SELECT * FROM empresa where id=:id")
    fun getById(id: Long): Empresas?

    @Query("SELECT * FROM empresa")
    fun findAll(): List<Empresas>

    @Insert
    fun insert(disciplina: Empresas)

    @Delete
    fun delete(disciplina: Empresas)



}