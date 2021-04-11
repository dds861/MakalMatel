package com.dd.domain.model

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.constants.LONG_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaModel

data class ResponseMakalModel(
        val result: String = STRING_EMPTY,
        val randomMakal: String = STRING_EMPTY,
        val makalIdAndLikeMap: HashMap<String, String> = HashMap(),
        val list: List<MakalModel> = listOf()
)

data class MakalModel(
        val makal_category_id: Int = INT_ZERO,
        val makal_id: Long = LONG_ZERO,
        val makal_text: String = STRING_EMPTY,
        val makal_like: Int = INT_ZERO
) : EmaModel
