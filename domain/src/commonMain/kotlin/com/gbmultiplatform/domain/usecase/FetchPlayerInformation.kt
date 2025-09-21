package com.gbmultiplatform.domain.usecase

import com.gbmultiplatform.domain.firebase.IPlayersInformation

class FetchPlayerInformation(
    private val repository: IPlayersInformation
) {
    suspend operator fun invoke(playerId: String) =
        repository.fetchPlayerInformation(playerId)
}