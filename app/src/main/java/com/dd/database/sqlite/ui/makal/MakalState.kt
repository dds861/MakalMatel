package com.dd.database.sqlite.ui.makal

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaBaseState
import com.dd.domain.model.CategoryModel
import com.dd.domain.model.MakalModel

data class MakalState(
        val default: String = STRING_EMPTY,
        val categoryId: Int = INT_ZERO,
        val listMakals: List<MakalModel> = listOf()

) : EmaBaseState