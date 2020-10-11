package br.com.luisbocchini.primeiroapp

import android.content.Context

object EmpresaService {

    fun getEmpresas (context: Context): List<Empresas> {
        val empresas = mutableListOf<Empresas>()

        // criar 10 empresas
        for (i in 1..10) {
            val d = Empresas()
            d.nome = "Empresa $i"
            d.ementa = "Ementa Empresa $i"
            d.professor = "Cordenador Empresa $i"
            d.foto = "https://lh3.googleusercontent.com/proxy/4yxqmW4-j5WC-SZnpv7rd6blkGiCHxyuzc2FoFtwqwSScl8i46GjAdI_kqA4NGFkL-LCuqCTMwvkVAXDghP5dG5fQssvjXfYMXqWr-7biwMvTKTJk1pXZJmFzEgZ9wAzEIJ-wD4TOcP2bhbYJn4"
            empresas.add(d)
        }

        return empresas
    }

}