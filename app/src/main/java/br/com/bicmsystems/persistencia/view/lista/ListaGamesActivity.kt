package br.com.bicmsystems.persistencia.view.lista

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import br.com.bicmsystems.persistencia.LoginActivity
import br.com.bicmsystems.persistencia.R
import br.com.bicmsystems.persistencia.dialog.NovoGameDialog
import br.com.bicmsystems.persistencia.model.Game

import kotlinx.android.synthetic.main.activity_lista_games.*
import kotlinx.android.synthetic.main.content_lista_games.*

class ListaGamesActivity : AppCompatActivity() {

    private var adapter: ListaGameAdapter? = null
    private var games : List<Game> = listOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_games)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val dialog = NovoGameDialog()
            dialog.show(fragmentManager!!, "CriarJogo")
        }

        mostraDados()

        rvGames.layoutManager = LinearLayoutManager(this)
        adapter = ListaGameAdapter(games!!)
        rvGames.adapter = adapter

    }

    private fun mostraDados() {

        ViewModelProviders.of(this)
                .get(ListaGameViewModel::class.java)
                .games
                .observe(this, Observer<List<Game>> { games ->
                    adapter?.setList(games!!)
                    rvGames.adapter.notifyDataSetChanged()
                })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_lista, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings ->  true
            R.id.action_exit -> {
                sair()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun sair() {

        val sharedPreferences = getSharedPreferences("myapp",
            Context.MODE_PRIVATE)

        if(sharedPreferences.getBoolean("MANTER_CONECTADO", false)) {
            val editor = sharedPreferences.edit()
            editor.remove("MANTER_CONECTADO")
            editor.apply()
        }
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }

}
