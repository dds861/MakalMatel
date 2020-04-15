package com.dd.database.sqlite.ui.makal

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.R
import com.dd.database.sqlite.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_makal.*
import org.kodein.di.generic.instance

class MakalViewFragment
    : BaseFragment<MakalState, MakalViewModel, MakalNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val layoutId: Int = R.layout.fragment_makal
    override val navigator: MakalNavigator by instance()
    override val viewModelSeed: MakalViewModel by instance()

    /**
     * Default functions
     */
    override fun onAlternative(data: EmaExtraData) {
    }

    override fun onNormal(data: MakalState) {
        loadRecyclerViews(data)
    }

    override fun onError(error: Throwable) {}

    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onSingle(data: EmaExtraData) {
    }

    override fun onInitialized(viewModel: MakalViewModel) {
        setupRecycler()
    }

    /**
     * Custom functions
     */
    private fun setupRecycler() {
        rvMakals.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun loadRecyclerViews(data: MakalState) {
        rvMakals.adapter = data.listMakals.toMutableList().let { it ->
            MakalAdapter(listItems = it) {
                if (it.copyClicked) {
                    copyToClipboard(it.makal_text)
                } else if (it.shareClicked) {
                    shareText(it.makal_text)
                }
            }
        }
    }

    private fun copyToClipboard(text: String) {
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val clip = ClipData.newPlainText("Makal text", text)
        clipboard?.setPrimaryClip(clip)
        Toast.makeText(context, R.string.TextCopied, Toast.LENGTH_SHORT).show()
    }

    private fun shareText(text: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, text)
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(sharingIntent, resources.getString(R.string.share_using)))
    }
}

