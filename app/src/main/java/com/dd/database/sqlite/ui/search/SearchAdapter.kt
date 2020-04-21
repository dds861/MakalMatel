package com.dd.database.sqlite.ui.search

import android.content.Context
import android.view.View
import com.carmabs.ema.android.ui.EmaRecyclerAdapter
import com.dd.database.sqlite.R
import com.dd.domain.model.MakalModel
import kotlinx.android.synthetic.main.item_makal.view.*
import kotlinx.android.synthetic.main.item_search.view.*

class SearchAdapter(private val context: Context,
                    private val adapterType: SearchState.AdapterType,
                    override val listItems: MutableList<MakalModel> = mutableListOf(),
                    private val itemListener: (MakalModel) -> Unit) : EmaRecyclerAdapter<MakalModel>() {
    override val layoutItemId: Int = getLayoutItemId()

    private fun getLayoutItemId(): Int {
        return when (adapterType) {
            SearchState.AdapterType.HINT -> R.layout.item_search
            SearchState.AdapterType.MAKALS -> R.layout.item_makal
        }
    }

    override fun View.bind(item: MakalModel, viewType: Int) {
        when (adapterType) {
            SearchState.AdapterType.HINT -> {
                tvText.text = item.makal_text

                rootView.setOnClickListener {
                    itemListener.invoke(item)
                }
            }

            SearchState.AdapterType.MAKALS -> text2.text = item.makal_text
        }
    }
}