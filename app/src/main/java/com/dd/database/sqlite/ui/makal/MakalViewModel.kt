package com.dd.database.sqlite.ui.makal

import com.dd.database.sqlite.base.BaseToolbarsViewModel
import com.dd.database.sqlite.model.ToolbarModel
import com.dd.database.sqlite.ui.main.MainToolbarsViewModel
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.MakalModel
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.usecase.GetLocalMakalByCategoryIdUseCase
import java.util.*

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
        checkDataState { makalState ->
            mainToolbarsVm.onActionUpdateToolbar {
                it.copy(
                        toolbarTitle = makalState.categoryTitle,
                        telegramButton = ToolbarModel.TelegramButton(
                                visibility = true
                        ),
                        searchButton = ToolbarModel.SearchButton(
                                visibility = true
                        )
                )
            }
        }
    }

    /**
     * Custom functions
     */
    fun onActionItemClicked(makalModel: MakalModel) {
    }

    fun onActionSearch(queryText: String) {
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = getLocalMakalByCategoryIdUseCase.execute(RequestMakalModel(categoryId = it.categoryId))

                        updateToNormalState {
                            copy(
                                    listMakals = responseMakalModel.list.filter {
                                        it.makal_text
                                                .toLowerCase(Locale.ROOT)
                                                .contains(queryText.toLowerCase(Locale.ROOT))
                                    }
                            )
                        }
                    },
                    { e ->
                        updateToErrorState(e)
                    })
        }
    }
}