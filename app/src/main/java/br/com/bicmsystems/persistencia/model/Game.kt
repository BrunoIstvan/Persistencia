package br.com.bicmsystems.persistencia.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Game (
        @PrimaryKey (autoGenerate = true)
        var id: Int = 0,
        var nome: String = "",
        var genero: String = "",
        var plataforma: String = "",
        var usuario: String = ""
)