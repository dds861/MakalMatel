package com.dd.data.manager

import android.content.Context
import com.dd.data.R
import com.dd.domain.manager.ResourceManager



class AndroidResourceManager(private val context: Context) : ResourceManager {

    override fun getCategoryList(): List<String> {
        return listOf("a", "b", "d", "e", "f", "g", "h", "i")
    }
}