package com.dd.database.sqlite.ui.makal

import com.dd.database.sqlite.base.BaseViewModel
import com.dd.domain.manager.ResourceManager
import com.dd.domain.usecase.GetMakalUseCase


class MakalViewModel(
        val resourceManager: ResourceManager,
        private val getMakalUseCase: GetMakalUseCase
) : BaseViewModel<MakalState, MakalNavigator.Navigation>() {




    override val initialViewState: MakalState = MakalState()

    override fun onStartFirstTime(statePreloaded: Boolean) {
    }


}