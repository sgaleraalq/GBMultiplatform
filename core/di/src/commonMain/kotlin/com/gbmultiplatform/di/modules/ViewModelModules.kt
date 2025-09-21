/*
 * Designed and developed by 2025 sgaleraalq (Sergio Galera)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gbmultiplatform.di.modules

import com.gbmultiplatform.di.provider.PlayerProvider
import com.gbmultiplatform.di.provider.TeamProvider
import com.gbmultiplatform.domain.model.player.IPlayerProvider
import com.gbmultiplatform.domain.model.team.ITeamProvider
import com.gbmultiplatform.domain.usecase.FetchAllPlayersInformation
import com.gbmultiplatform.domain.usecase.FetchPlayerInformation
import com.gbmultiplatform.domain.usecase.InsertNewPlayer
import com.gbmultiplatform.domain.usecase.ShowCamera
import com.gbmultiplatform.domain.usecase.ShowGallery
import com.gbmultiplatform.domain.utils.PermissionBridge
import com.gbmultiplatform.domain.utils.SharedImagesBridge
import com.gbmultiplatform.presentation.MainViewModel
import com.gbmultiplatform.presentation.screens.auth.welcome.WelcomeViewModel
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel
import com.gbmultiplatform.presentation.screens.match_detail.MatchDetailViewModel
import com.gbmultiplatform.presentation.screens.matches.GetMatchResultUseCase
import com.gbmultiplatform.presentation.screens.matches.MatchesViewModel
import com.gbmultiplatform.presentation.screens.player_info_detail.PlayerInformationViewModel
import com.gbmultiplatform.presentation.screens.review_photo.ReviewPhotoViewModel
import com.gbmultiplatform.presentation.screens.stats.StatsViewModel
import com.gbmultiplatform.presentation.screens.team.TeamViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::StatsViewModel)
    viewModelOf(::MatchesViewModel)
    viewModelOf(::MatchDetailViewModel)
    viewModelOf(::TeamViewModel)
    viewModelOf(::InsertPlayerViewModel)
    viewModelOf(::ReviewPhotoViewModel)
    viewModelOf(::PlayerInformationViewModel)
}

val useCasesModule = module {
    factoryOf(::ShowCamera)
    factoryOf(::ShowGallery)
    factoryOf(::InsertNewPlayer)
    factoryOf(::FetchAllPlayersInformation)
    factoryOf(::FetchPlayerInformation)
}

val viewModelHelpersModule = module {
    factory<IPlayerProvider> { PlayerProvider() }
    factory<ITeamProvider> { TeamProvider() }
    factory { GetMatchResultUseCase() }
    single { PermissionBridge() }
    single { SharedImagesBridge() }
}
