package br.com.bicmsystems.persistencia.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.widget.EditText
import br.com.bicmsystems.persistencia.R
import br.com.bicmsystems.persistencia.dao.BancoDeDados
import br.com.bicmsystems.persistencia.model.Game

class NovoGameDialog : DialogFragment() {

    private lateinit var builder: AlertDialog.Builder
    private lateinit var edtNomeGame: EditText
    private lateinit var edtPlataforma: EditText
    private lateinit var edtGenero: EditText

    var sharedPreferences: SharedPreferences? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        builder = AlertDialog.Builder(activity!!)

        // recupera a view onde será inseridos os dados do jogo
        val view = activity!!.layoutInflater.inflate(R.layout.novo_game_dialog, null)

        edtNomeGame = view.findViewById(R.id.edtNomeGame)
        edtGenero = view.findViewById(R.id.edtGenero)
        edtPlataforma = view.findViewById(R.id.edtPlataforma)

        builder.setView(view)
        builder.setTitle("Novo Game")
        builder.setPositiveButton("Adicionar") { _ , _ ->

            sharedPreferences = activity!!.getSharedPreferences("myapp",
                    Context.MODE_PRIVATE)

            val usuario = sharedPreferences?.getString("USUARIO", "")
            val db = BancoDeDados.getDatabase(activity!!.applicationContext)
            val game = Game(0,
                                edtNomeGame.text.toString(),
                                edtGenero.text.toString(),
                                edtPlataforma.text.toString(),
                                usuario!!)

            if (game.nome.isNotEmpty() && game.genero.isNotEmpty() && game.plataforma.isNotEmpty()) {
                InsertAsyncTask(db!!).execute(game)
            }
        }

        builder.setNegativeButton("Cancelar", null)
        return builder.create()

    }

    private inner class InsertAsyncTask internal
        constructor(appDatabase: BancoDeDados) : AsyncTask<Game, Void, String>() {

        private val db: BancoDeDados = appDatabase

        override fun doInBackground(vararg params: Game?): String {
            db.gameDAO().inserir(params[0]!!)
            return ""
        }

    }


}