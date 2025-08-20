package com.gbmultiplatform.presentation.screens.gbapp.match_detail.states

import com.gbmultiplatform.design_system.model.LineUpFormation
import com.gbmultiplatform.design_system.model.UIPlayer
import com.gbmultiplatform.helper.GazteluBiraUtils.GAZTELU_BIRA
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.states.details.DetailsTeamModel
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.states.line_up.LineUpModel
import kotlin.random.Random

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

    fun create(): LineUpModel {
        return LineUpModel(
            matchFormation = LineUpFormation.FourThreeThree,
            managers = managers,
            benchPlayers = benchPlayers
        )
    }

    const val SHORT_DESCRIPTION = "This is a friendly match between Gaztelu Bira and Gaztelu Bira. The match will take place on 01/01/2025 in Pamplona, Spain."
    const val LONG_DESCRIPTION = "This is a friendly match between Gaztelu Bira and Gaztelu Bira. The match will take place on 01/01/2025 in Pamplona, Spain. " +
            "It promises to be an exciting encounter with both teams showcasing their skills." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field." +
            " Fans can expect a thrilling game with plenty of action on the field."

    fun createDetailsInfo(): DetailsTeamModel {
        val descriptions = listOf(SHORT_DESCRIPTION, LONG_DESCRIPTION, null)
        val randomDescription = descriptions.random()
        val randomLocation = listOf("Pamplona, Spain", "Madrid, Spain", "Barcelona, Spain", null).random()

        return DetailsTeamModel(
            local = GAZTELU_BIRA,
            visitor = GAZTELU_BIRA,
            date = "01/01/2025",
            description = randomDescription,
            location = randomLocation
        )
    }
}
