package com.dd.data.manager

import android.content.Context
import com.dd.data.R
import com.dd.domain.manager.ResourceManager

/**
 *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * Date: 2019-11-08
 */

class AndroidResourceManager(private val context: Context) : ResourceManager {

    override fun getResultErrorFillName(): String {
        return "text0"
    }

    override fun getResultErrorFillSurname(): String {
        return "text1"
    }

    override fun getHomeUserToolbarTitle(): String {
        return "text2"
    }

    override fun getCongratulations(): String {
        return "text3"
    }

    override fun getNumberPeople(number: Int): String {
        return "text4"
    }
}