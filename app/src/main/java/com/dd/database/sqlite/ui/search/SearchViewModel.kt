package com.dd.database.sqlite.ui.search

import com.dd.database.sqlite.base.BaseToolbarsViewModel
import com.dd.database.sqlite.model.ToolbarModel
import com.dd.database.sqlite.ui.main.MainToolbarsViewModel
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.MakalModel
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.usecase.GetLocalMakalByCategoryIdUseCase
import com.dd.domain.usecase.GetLocalMakalByQueryTextUseCase
import java.util.*

class SearchViewModel(
        val resourceManager: ResourceManager,
        private val getLocalMakalByCategoryIdUseCase: GetLocalMakalByCategoryIdUseCase,
        private val getLocalMakalByQueryTextUseCase: GetLocalMakalByQueryTextUseCase
) : BaseToolbarsViewModel<SearchState, SearchNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val initialViewState: SearchState = SearchState()
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

    fun onActionQueryTextSubmit(query: String) {
    }

    fun onActionQueryTextChange(newText: String) {
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = getLocalMakalByQueryTextUseCase.execute(RequestMakalModel(queryText = newText))
                        updateToNormalState {
                            copy(
                                    listMakals = filterMakalsByQueryText(newText, responseMakalModel.list)
                            )
                        }
                    },
                    { e ->
                        updateToErrorState(e)
                    })
        }
    }

    private fun filterMakalsByQueryText(queryText: String, list: List<MakalModel>): List<MakalModel> {
        if (queryText.isNotEmpty()) {
            val listMakalModels: MutableList<MakalModel> = mutableListOf()

            list.map { makalModel ->
                makalModel.makal_text.toLowerCase(Locale.ROOT)
                        .replace("\n", " ")
                        .replace(",", "")
                        .replace(".", "")
                        .replace(" - ", "")
                        .replace("- ", "")
                        .replace(" -", "")
                        .split(" ")
                        .filter { s -> s.contains(queryText) }
                        .map { MakalModel(makal_text = it) }
            }.map { listMakals ->
                listMakals.map {
                    listMakalModels.add(it)
                }
            }
            return listMakalModels.distinct()
                    .sortedByDescending { it.makal_text }
                    .sortedBy { it.makal_text.length }
        } else {
            return listOf()
        }
    }
}