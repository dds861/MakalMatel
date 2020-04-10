package com.dd.database.sqlite.ema.ui.backdata.userlist

import android.view.View
import com.carmabs.ema.android.ui.EmaRecyclerAdapter
import com.dd.database.sqlite.ema.ui.backdata.userlist.EmaBackUserModel

/**
 *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * Date: 2019-11-07
 */

class EmaBackUserAdapter(override val listItems: MutableList<EmaBackUserModel> = mutableListOf()) : EmaRecyclerAdapter<EmaBackUserModel>() {

    override val layoutItemId: Int = com.dd.database.sqlite.R.layout.item_back_user

    override fun View.bind(item: EmaBackUserModel, viewType: Int) {
        tvItemUserName.text = item.name
        tvItemUserSurname.text = item.surname

    }
}