package com.gbmultiplatform.presentation.screens.gbapp.matches

import androidx.lifecycle.ViewModel
import com.gbmultiplatform.model.team.ITeamProvider

class MatchesViewModel(
    private val teamProvider: ITeamProvider
) : ViewModel() {
    fun provideRandomTeam() = teamProvider.provideRandomTeam()
    fun provideMatches() = List(10) {
        teamProvider.provideMatch()
    }

    val appTeam = teamProvider.provideAppTeam()
}
