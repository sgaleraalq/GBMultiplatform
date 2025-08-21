package com.gbmultiplatform.presentation.screens.gbapp.matches

import androidx.lifecycle.ViewModel
import com.gbmultiplatform.domain.model.team.ITeamProvider
import com.gbmultiplatform.domain.model.match.MatchModel
import com.gbmultiplatform.domain.model.match.MatchResult

class MatchesViewModel(
    private val teamProvider: ITeamProvider,
    private val getResultUseCase: GetMatchResultUseCase
) : ViewModel() {
    fun provideMatches() = List(10) {
        teamProvider.provideMatch()
    }

    val appTeam = teamProvider.provideAppTeam()

    fun calculateResult(match: MatchModel): MatchResult =
        getResultUseCase(match, appTeam)
}
