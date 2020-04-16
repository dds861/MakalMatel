package com.dd.database.sqlite.model

import com.carmabs.ema.core.constants.FLOAT_ZERO
import com.carmabs.ema.core.constants.STRING_EMPTY

/**
 * Model to represent the toolbar
 *
 * <p>
 * Copyright (c) 2019, Babel Sistemas de Información. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:carlos.mateo@babel.es”>Carlos Mateo</a>
 */

data class ToolbarModel(
        val backClickListener: (() -> Unit)? = null,
        val backVisibility: Boolean = false,
        val backDrawableCross: Boolean = false,
        val closeSessionVisibility: Boolean = true,
        val title: String = STRING_EMPTY,
        val visibility: Boolean = true,
        val elevation: Boolean = true,
        val gone: Boolean = true,
        val exitButton: ExitButton? = null
) {
    data class ExitButton(
            val text: String = STRING_EMPTY,
            val onClickListener: (() -> Unit),
            val textSize: Float = FLOAT_ZERO,
            val enabled: Boolean = true
    )
}