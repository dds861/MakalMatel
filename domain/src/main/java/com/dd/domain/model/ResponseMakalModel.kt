package com.dd.domain.model

import com.carmabs.ema.core.constants.INT_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaModel

data class ResponseMakalModel(
        val result: String = STRING_EMPTY,
        val list: List<MakalModel> = listOf()
)


data class MakalModel(
        val makal_id: Int = INT_ZERO,
        val makal_text: String = STRING_EMPTY
) : EmaModel
