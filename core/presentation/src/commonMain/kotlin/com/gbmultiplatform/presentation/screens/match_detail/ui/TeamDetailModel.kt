package com.gbmultiplatform.presentation.screens.match_detail.ui

import com.gbmultiplatform.domain.model.team.TeamModel

data class TeamDetailModel(
    val team: TeamModel,
    val score: Int,
    val isLocal: Boolean
)