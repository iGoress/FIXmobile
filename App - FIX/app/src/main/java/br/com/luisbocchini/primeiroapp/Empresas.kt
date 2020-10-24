package br.com.luisbocchini.primeiroapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName="empresa")
class Empresas : Serializable {
    @PrimaryKey
    var id:Long = 0
    var nome = ""
    var foto = ""

    override fun toString(): String {
        return "Empresa(nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}