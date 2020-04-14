package com.dd.database.sqlite.ui.makal

import android.util.Log
import com.dd.database.sqlite.base.BaseViewModel
import com.dd.domain.manager.ResourceManager
import com.dd.domain.model.MakalModel
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.usecase.GetLocalMakalByCategoryIdUseCase

class MakalViewModel(
        val resourceManager: ResourceManager,
        private val getLocalMakalByCategoryIdUseCase: GetLocalMakalByCategoryIdUseCase
) : BaseViewModel<MakalState, MakalNavigator.Navigation>() {
    override val initialViewState: MakalState = MakalState()

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

    fun onActionCopyClick(makalModel: MakalModel) {
        Log.i("autolog", "Copy: " + makalModel.makal_text);
    }

    fun onActionShareClick(makalModel: MakalModel) {
        Log.i("autolog", "Share: " + makalModel.makal_text);
    }
}