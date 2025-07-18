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

package com.gbmultiplatform.presentation.navigation

class NavigationHandler : MainNavigationState {
    private var delegate: MainNavigationState? = null

    fun attach(state: MainNavigationState) {
        delegate = state
    }

    private fun requireDelegate(): MainNavigationState =
        delegate ?: error("NavigationHandler not initialized. Call attach() first.")

    override val currentDestination
        get() = requireDelegate().currentDestination

    override fun navigate(destination: MainDestination) = requireDelegate().navigate(destination)
    override fun navigateBack() = requireDelegate().navigateBack()
    override fun popUpToHome() = requireDelegate().popUpToHome()
    override fun navigateToTop(destination: MainDestination) = requireDelegate().navigateToTop(destination)
}