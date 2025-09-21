package com.gbmultiplatform.di.modules

import androidx.activity.ComponentActivity
import com.gbmultiplatform.data.network.firebase.PlayerInformationAndroid
import com.gbmultiplatform.domain.firebase.IPlayersInformation
import com.gbmultiplatform.domain.utils.IToastManager
import com.gbmultiplatform.domain.utils.ToastManagerAndroid
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedModules: Module = module {
    factory<IToastManager> { ToastManagerAndroid(get()) }
    single { ComponentActivity() }
    single<IPlayersInformation> { PlayerInformationAndroid() }
}