package com.dd.database.sqlite.ui.makal

import android.view.View
import com.carmabs.ema.android.ui.EmaRecyclerAdapter
import com.dd.database.sqlite.R
import com.dd.domain.model.MakalModel
import kotlinx.android.synthetic.main.item_makal.view.*

class MakalAdapter(override val listItems: MutableList<MakalModel> = mutableListOf(),
                   private val itemListener: (MakalModel) -> Unit) : EmaRecyclerAdapter<MakalModel>() {
    override val layoutItemId: Int = R.layout.item_makal

    override fun View.bind(item: MakalModel, viewType: Int) {
        text2.text = item.makal_text.replace("\\\\n".toRegex(), "\n")



        ivCopy.setOnClickListener { itemListener.invoke(MakalModel(makal_id = item.makal_id, makal_text = item.makal_text, copyClicked = true)) }
        ivShare.setOnClickListener { itemListener.invoke(MakalModel(makal_id = item.makal_id, makal_text = item.makal_text, shareClicked = true)) }
    }
}