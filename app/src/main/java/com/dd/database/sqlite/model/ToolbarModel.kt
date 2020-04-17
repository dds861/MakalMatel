package com.dd.database.sqlite.model

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
        val telegramButton: TelegramButton? = null,
        val backButton: BackButton? = null,
        val searchButton: SearchButton? = null,
        val toolbarTitle: String = STRING_EMPTY,
        val toolbarTitleVisibility: Boolean = false,
        val toolbarLogoVisibility: Boolean = false,
        val toolbarVisibility: Boolean = true,
        val toolbarElevation: Boolean = false
) {
    data class SearchButton(
            val onClickListener: (() -> Unit),
            val visibility: Boolean = true
    )

    data class TelegramButton(
            val onClickListener: (() -> Unit),
            val visibility: Boolean = true
    )

    data class BackButton(
            val onClickListener: (() -> Unit),
            val visibility: Boolean = false
    )
}