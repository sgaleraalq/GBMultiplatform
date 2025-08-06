package com.gbmultiplatform.presentation.screens.gbapp.matches

import androidx.lifecycle.ViewModel
import com.gbmultiplatform.model.team.ITeamProvider
import com.gbmultiplatform.model.match.MatchModel

class MatchesViewModel(
    private val teamProvider: ITeamProvider,
    private val getResultUseCase: GetMatchResultUseCase
) : ViewModel() {
    fun provideRandomTeam() = teamProvider.provideRandomTeam()
    fun provideMatches() = List(10) {
        teamProvider.provideMatch()
    }

    val appTeam = teamProvider.provideAppTeam()

    fun calculateResult(match: MatchModel) =
        getResultUseCase(match, appTeam)
}
