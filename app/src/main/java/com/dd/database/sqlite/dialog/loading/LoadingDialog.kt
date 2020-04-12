package com.dd.database.sqlite.dialog.loading

import android.view.View
import com.carmabs.ema.android.ui.dialog.EmaBaseDialog
import com.dd.database.sqlite.R
import kotlinx.android.synthetic.main.dialog_loading.view.*

/**
 * Simple dialog
 *
 *
 * @author <a href="mailto:apps.carmabs@gmail.com">Carlos Mateo Benito</a>
 */
class LoadingDialog : EmaBaseDialog<LoadingDialogData>() {

    override val layoutId: Int = R.layout.dialog_loading

    override fun setupData(data: LoadingDialogData, view: View) {
        view.tvDialogLoadingTitle.text = data.title
        view.tvDialogLoadingMessage.text = data.message

        isCancelable = false
    }
}