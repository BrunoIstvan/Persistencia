package br.com.bicmsystems.persistencia.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import br.com.bicmsystems.persistencia.model.Game


@Dao
interface GameDAO {

    @Insert
    fun inserir(game: Game)

    @Query("SELECT * FROM Game")
    fun lerGames(): LiveData<List<Game>>

    @Query("SELECT * FROM Game WHERE usuario = :usuario")
    fun lerGamesPorUsuario(usuario: String): LiveData<List<Game>>

    @Update
    fun atualizar(game: Game)

    @Delete
    fun apagar(game: Game)

}