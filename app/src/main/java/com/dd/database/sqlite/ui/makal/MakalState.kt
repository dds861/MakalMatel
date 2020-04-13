package com.dd.database.sqlite.ui.makal

import com.carmabs.ema.core.constants.STRING_EMPTY
import com.carmabs.ema.core.state.EmaBaseState

data class MakalState(
        val default: String = STRING_EMPTY
) : EmaBaseState