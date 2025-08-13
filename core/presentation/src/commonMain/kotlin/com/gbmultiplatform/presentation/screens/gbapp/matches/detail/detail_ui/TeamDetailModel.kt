package com.gbmultiplatform.presentation.screens.gbapp.matches.detail.detail_ui

import com.gbmultiplatform.model.team.TeamModel

data class TeamDetailModel(
    val team: TeamModel,
    val score: Int,
    val isLocal: Boolean
)