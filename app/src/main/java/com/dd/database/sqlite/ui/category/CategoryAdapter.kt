package com.dd.database.sqlite.ui.category

import android.view.View
import com.carmabs.ema.android.ui.EmaRecyclerAdapter
import com.dd.database.sqlite.R
import kotlinx.android.synthetic.main.item_category.view.*


class CategoryAdapter(override val listItems: MutableList<CategoryModel> = mutableListOf(),
                      private val itemListener: (CategoryModel) -> Unit) : EmaRecyclerAdapter<CategoryModel>() {

    override val layoutItemId: Int = R.layout.item_category

    override fun View.bind(item: CategoryModel, viewType: Int) {
        tvCategoryTitle.text = item.title

        tvCategoryTitle.setOnClickListener { itemListener.invoke(item) }
    }
}