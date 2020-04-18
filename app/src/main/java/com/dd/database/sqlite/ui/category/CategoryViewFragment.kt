package com.dd.database.sqlite.ui.category

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.core.state.EmaExtraData
import com.dd.database.sqlite.R
import com.dd.database.sqlite.base.BaseToolbarsFragment
import com.dd.database.sqlite.ui.category.CategoryViewModel.Companion.TELEGRAM_CLICKED
import com.dd.database.sqlite.ui.main.MainToolbarsViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import org.kodein.di.generic.instance

class CategoryViewFragment : BaseToolbarsFragment<CategoryState, CategoryViewModel, CategoryNavigator.Navigation>() {
    /**
     * Default variables
     */
    override val layoutId: Int = R.layout.fragment_category
    override val navigator: CategoryNavigator by instance()
    override val viewModelSeed: CategoryViewModel by instance()

    /**
     * Custom variables
     */
    private lateinit var vm: CategoryViewModel

    /**
     * Default functions
     */
    override fun onInitializedWithToolbarsManagement(viewModel: CategoryViewModel, mainToolbarViewModel: MainToolbarsViewModel) {
        vm = viewModel
        setupRecycler()
        setupListeners()
    }

    override fun onSingleEvent(data: EmaExtraData) {
    }

    override fun onNormal(data: CategoryState) {
        loadRecyclerViews(data)
    }

    override fun onAlternative(data: EmaExtraData) {
    }

    override fun onError(error: Throwable) {
        Log.i("autolog", "error: " + error);
    }

    override fun onSingle(data: EmaExtraData) {
    }

    /**
     * Custom functions
     */
    private fun setupRecycler() {
        rvCategory.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun loadRecyclerViews(data: CategoryState) {
        rvCategory.adapter = data.listCategories.toMutableList().let { it ->
            CategoryAdapter(listItems = it) {
                vm.onActionCategoryClick(it)
            }
        }
    }

    private fun setupListeners() {
    }

    private fun launchTelegram() {
        startActivity(try {
            requireContext().packageManager.getPackageInfo(resources.getString(R.string.telegramPackage), PackageManager.GET_ACTIVITIES)
            Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=maqal7"))
        } catch (e: PackageManager.NameNotFoundException) {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/maqal7"))
        })
    }
}
