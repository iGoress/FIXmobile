package br.com.luisbocchini.primeiroapp

import android.content.Context
import br.com.luisbocchini.primeiroapp.LoginService.parserJson

object EmpresaService {

    val host = "https://luisbocchini01.pythonanywhere.com"
    val TAG = "WS_LMSApp"
    fun getEmpresas (context: Context): List<Empresas> {

        var disciplinas = ArrayList<Empresas>()
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/empresas"
            val json = HttpHelper.get(url)
            disciplinas = parserJson(json)
            // salvar offline
            for (d in disciplinas) {
                saveOffline(d)
            }
            return disciplinas
        }else{

            //val empresas = mutableListOf<Empresas>()

            // criar 10 empresas
            //for (i in 1..10) {
            //  val d = Empresas()
            //d.nome = "Empresa $i"
            //d.ementa = "Ementa Empresa $i"
            //d.professor = "Cordenador Empresa $i"
            //d.foto = "https://www.repousobemmecare.com.br/imagens/academias/asilos-para-idosos-carentes.jpg"
            // empresas.add(d)
            val dao = DatabaseManager.getDisciplinaDAO()
            val empresas = dao.findAll()
            return empresas

            //return DatabaseManager.getDisciplinaDAO().findAll()
        }
    }
    fun save(disciplina: Empresas): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val json = HttpHelper.post("$host/empresas", disciplina.toJson())
            return parserJson(json)
        }
        else {
            saveOffline(disciplina)
            return Response("OK", "Empresa salva no dispositivo")
        }
        //val json = HttpHelper.post("$host/disciplinas", disciplina.toJson())
        //return parserJson(json)

       // return DatabaseManager.getDisciplinaDAO().insert(disciplina)
        //saveOffline(disciplina)
        //return Response("OK", "Empresa salva no dispositivo")
    }

    fun saveOffline(disciplina: Empresas) : Boolean {
        val dao = DatabaseManager.getDisciplinaDAO()

        if (! existeEmpresa(disciplina)) {
            dao.insert(disciplina)
        }

        return true

    }

    fun existeEmpresa(empresa: Empresas): Boolean {
        val dao = DatabaseManager.getDisciplinaDAO()
        return dao.getById(empresa.id) != null
    }

    fun delete(disciplina: Empresas): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/empresas/${disciplina.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getDisciplinaDAO()
            dao.delete(disciplina)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }

    }

        //return empresas
    }

