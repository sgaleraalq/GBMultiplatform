package com.gbmultiplatform.di.modules

import androidx.activity.ComponentActivity
import com.gbmultiplatform.data.network.firebase.PlayerInformationFirebaseAndroid
import com.gbmultiplatform.domain.repository.IPlayersInformationRepository
import com.gbmultiplatform.domain.utils.IPermissionHandler
import com.gbmultiplatform.domain.utils.IToastManager
import com.gbmultiplatform.domain.utils.PermissionsManagerAndroid
import com.gbmultiplatform.domain.utils.ToastManagerAndroid
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedModules: Module = module {
    single{ ComponentActivity() }
    factory<IPermissionHandler>{ PermissionsManagerAndroid() }
    factory<IToastManager> { ToastManagerAndroid(get()) }
    single<IPlayersInformationRepository> { PlayerInformationFirebaseAndroid() }
}