package com.gbmultiplatform.di.modules

import com.gbmultiplatform.domain.repository.IPlayersInformationRepository
import com.gbmultiplatform.data.network.firebase.PlayerInformationFirebaseAndroid
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedModules: Module = module {
    single<IPlayersInformationRepository> { PlayerInformationFirebaseAndroid() }
}