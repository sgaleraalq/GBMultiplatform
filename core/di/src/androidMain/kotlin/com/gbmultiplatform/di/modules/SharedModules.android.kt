package com.gbmultiplatform.di.modules

import com.gbmultiplatform.data.network.firebase.player_information.PlayerInformationFirebaseAndroid
import com.gbmultiplatform.data.network.firebase.IPlayersInformationFirebase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedModules: Module = module {
    single<IPlayersInformationFirebase> { PlayerInformationFirebaseAndroid() }
}