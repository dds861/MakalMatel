package com.dd.database.sqlite.ui.category

import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaBaseState

data class CategoryState(
        val default: String = STRING_EMPTY,
        val listCategories: List<CategoryModel> = listOf()
) : EmaBaseState