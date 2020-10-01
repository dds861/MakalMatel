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
            mainToolbarsVm.onActionUpdateToolbar { toolbarModel ->
                toolbarModel.copy(
                        toolbarTitle = makalState.categoryTitle,
                        telegramButton = ToolbarModel.TelegramButton(
                                visibility = true
                        ),
                        searchButton = ToolbarModel.SearchButton(
                                visibility = true,
                                setOnQueryTextFocusChangeListener = {
                                    onActionFilterToolbarHintsByQueryText(it)
                                }
                        )
                )
            }
        }
    }

    /**
     * Custom functions
     */
    private fun filterToolbarHintsByQueryText(queryText: String, list: List<MakalModel>): List<MakalModel> {
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

    private fun filterToolbarMakalsByQueryText(queryText: String, list: List<MakalModel>): List<MakalModel> {
        return if (queryText.isNotEmpty()) {
            val listMakalModels: MutableList<MakalModel> = mutableListOf()

            list.map { makalModel ->
                val indexBeforeQueryText = makalModel.makal_text.indexOf(queryText)
                if (indexBeforeQueryText < 0 || (indexBeforeQueryText >= 0 && makalModel.makal_text[indexBeforeQueryText - 1] == ' ')) {
                    listMakalModels.add(makalModel)
                }
            }
            listMakalModels.distinct()
                    .sortedByDescending { it.makal_text }
                    .sortedBy { it.makal_text.length }
        } else {
            listOf()
        }
    }

    private fun onActionFilterToolbarHintsByQueryText(queryText: String) {



        //load results of hints by keyword
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = getLocalMakalByQueryTextUseCase.execute(RequestMakalModel(queryText = queryText))
                        updateToNormalState {
                            copy(
                                    listMakals = filterToolbarHintsByQueryText(queryText, responseMakalModel.list),
                                    adapterType = SearchState.AdapterType.HINT
                            )
                        }
                    },
                    { e ->
                        updateToErrorState(e)
                    })
        }
    }

    fun onActionFilterToolbarMakalsByQueryText(queryText: String) {
        mainToolbarsVm.onActionShowInterstitialAd()
        //update text on searchView
        mainToolbarsVm.onActionSearchViewText(queryText)

        //load makals to recyclerview
        checkDataState {
            executeUseCaseWithException(
                    {
                        val responseMakalModel = getLocalMakalByQueryTextUseCase.execute(RequestMakalModel(queryText = queryText))
                        updateToNormalState {
                            copy(
                                    listMakals = filterToolbarMakalsByQueryText(queryText, responseMakalModel.list),
                                    adapterType = SearchState.AdapterType.MAKALS
                            )
                        }
                    },
                    { e ->
                        updateToErrorState(e)
                    })
        }
    }
}