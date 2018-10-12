package br.com.bicmsystems.persistencia.view.lista

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import br.com.bicmsystems.persistencia.dao.BancoDeDados
import br.com.bicmsystems.persistencia.model.Game

class ListaGameViewModel(application : Application) : AndroidViewModel(
        application
) {

    lateinit var games : LiveData<List<Game>>

    private val db: BancoDeDados = BancoDeDados.getDatabase(application.applicationContext)!!


    init {

        carregaDados()

    }

    private fun carregaDados() {

        games = db.gameDAO().lerGames()

    }


}