package com.gbmultiplatform.di.modules

import com.gbmultiplatform.data.network.firebase.PlayerInformationFirebaseAndroid
import com.gbmultiplatform.domain.repository.IPlayersInformationRepository
import com.gbmultiplatform.domain.utils.IPermissionHandler
import com.gbmultiplatform.domain.utils.IToastManager
import com.gbmultiplatform.domain.utils.PermissionsManagerAndroid
import com.gbmultiplatform.domain.utils.ToastManagerAndroid
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedModules: Module = module {
    factory<IPermissionHandler>{ PermissionsManagerAndroid(get()) }
    factory<IToastManager> { ToastManagerAndroid(get()) }
    single<IPlayersInformationRepository> { PlayerInformationFirebaseAndroid() }
}