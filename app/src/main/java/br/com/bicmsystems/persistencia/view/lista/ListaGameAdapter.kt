package br.com.bicmsystems.persistencia.view.lista

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.bicmsystems.persistencia.R
import br.com.bicmsystems.persistencia.model.Game

class ListaGameAdapter(var games: List<Game>) : RecyclerView.Adapter<ListaGameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {

        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_game, parent, false)

        return GameViewHolder(v)

    }

    override fun getItemCount(): Int {

        return games.size

    }

    fun setList(games: List<Game>) {

        this.games = games

    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        val game = games[position]
        holder.tvGame.text = game.nome
        holder.tvGenero.text = game.genero
        holder.tvPlataforma.text = game.plataforma

    }


    class GameViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvGame : TextView = v.findViewById(R.id.tvGame)
        var tvGenero : TextView = v.findViewById(R.id.tvGenero)
        var tvPlataforma : TextView = v.findViewById(R.id.tvPlataforma)

    }

}