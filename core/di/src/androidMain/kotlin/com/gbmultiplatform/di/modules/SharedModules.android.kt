package com.gbmultiplatform.di.modules

import com.gbmultiplatform.data.network.firebase.FirebaseRepositoryAndroid
import com.gbmultiplatform.data.network.firebase.IFirebase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedModules: Module = module {
    single<IFirebase> { FirebaseRepositoryAndroid() }
}