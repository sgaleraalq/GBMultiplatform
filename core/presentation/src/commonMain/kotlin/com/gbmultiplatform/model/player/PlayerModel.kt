package com.gbmultiplatform.model.player

data class PlayerModel(
    val id: String,
    val name: String,
    val image: String,
    val goals: Int,
    val assists: Int,
    val penaltiesProvoked: Int,
    val cleanSheets: Int,
    val saves: Int,
    val yellowCards: Int,
    val redCards: Int,
    val gamesPlayed: Int,
    val percentage: Double
)
