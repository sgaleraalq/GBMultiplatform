package com.gbmultiplatform.presentation.screens.gbapp.matches.detail.detail_ui

import com.gbmultiplatform.design_system.model.LineUpFormation
import com.gbmultiplatform.design_system.model.UIPlayer
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.MatchDetailUIModel

object MatchDetailUIModelFactory {
    val managers = listOf(
        UIPlayer(
            "Manager 1",
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"
        ),
        UIPlayer(
            "Manager 2",
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"
        )
    )

    val benchPlayers = listOf(
        UIPlayer(
            "Player 1",
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"
        ),
        UIPlayer(
            "Player 2",
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"
        ),
        UIPlayer(
            "Player 3",
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"
        ),
        UIPlayer(
            "Player 4",
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"
        ),
        UIPlayer(
            "Player 5",
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"
        ),
        UIPlayer(
            "Player 6",
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"
        ),
        UIPlayer(
            "Player 7",
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"
        ),
        UIPlayer(
            "Player 8",
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"
        )
    )

    fun create(): MatchDetailUIModel {
        return MatchDetailUIModel(
            matchFormation = LineUpFormation.FourThreeThree,
            managers = managers,
            benchPlayers = benchPlayers
        )
    }
}
