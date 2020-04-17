package com.dd.database.sqlite.ui.home

import com.carmabs.ema.core.state.EmaBaseState
import com.dd.database.sqlite.model.ToolbarModel

data class HomeToolbarState(
        val toolbarTitle: String? = null,
        val toolbarModel: ToolbarModel = ToolbarModel()
) : EmaBaseState