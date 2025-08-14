package com.gbmultiplatform.presentation.screens.gbapp.match_detail.states

import com.gbmultiplatform.model.team.TeamModel

data class TeamDetailModel(
    val team: TeamModel,
    val score: Int,
    val isLocal: Boolean
)
