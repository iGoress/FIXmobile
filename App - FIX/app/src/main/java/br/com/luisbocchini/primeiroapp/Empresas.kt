package br.com.luisbocchini.primeiroapp

import java.io.Serializable

class Empresas : Serializable {

    var id:Long = 0
    var nome = ""
    var ementa = ""
    var foto = ""
    var professor = ""

    override fun toString(): String {
        return "Empresa(nome='$nome')"
    }
}