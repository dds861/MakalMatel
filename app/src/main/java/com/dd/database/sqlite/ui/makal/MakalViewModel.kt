package com.dd.database.sqlite.ui.makal

import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.base.BaseToolbarsViewModel
import com.dd.database.sqlite.model.ToolbarModel
import com.dd.database.sqlite.ui.category.CategoryViewModel
import com.dd.database.sqlite.ui.main.MainToolbarsViewModel
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.MakalModel
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.usecase.GetLocalMakalByCategoryIdUseCase

class MakalViewModel(
        val resourceManager: ResourceManager,
        private val getLocalMakalByCategoryIdUseCase: GetLocalMakalByCategoryIdUseCase
) : BaseToolbarsViewModel<MakalState, MakalNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val initialViewState: MakalState = MakalState()
    /**
     * Custom variables
     */
    /**
     * Default functions
     */
    override fun onStartFirstTime(statePreloaded: Boolean) {
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = getLocalMakalByCategoryIdUseCase.execute(RequestMakalModel(categoryId = it.categoryId))
                        updateToNormalState {
                            copy(
                                    listMakals = responseMakalModel.list
                            )
                        }
                    },
                    { e ->
                        updateToErrorState(e)
                    })
        }
    }

    override fun onConfigureToolbars(mainToolbarsVm: MainToolbarsViewModel) {
        mainToolbarsVm.onActionUpdateToolbar {
            it.copy(
                    toolbarTitle = resourceManager.getToolbarTitle(),
                    toolbarTitleVisibility = false,
                    toolbarLogoVisibility = false,
                    backButton = ToolbarModel.BackButton(
                            visibility = true
                    ),
                    telegramButton = ToolbarModel.TelegramButton(
                            visibility = true
                    ),
                    searchButton = ToolbarModel.SearchButton(
                            visibility = true
                    )
            )
        }
    }

    /**
     * Custom functions
     */
    fun onActionItemClicked(makalModel: MakalModel) {
    }


}