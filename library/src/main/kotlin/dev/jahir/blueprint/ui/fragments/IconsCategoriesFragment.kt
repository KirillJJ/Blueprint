package dev.jahir.blueprint.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dev.jahir.blueprint.data.models.Icon
import dev.jahir.blueprint.data.models.IconsCategory
import dev.jahir.blueprint.ui.activities.BlueprintActivity
import dev.jahir.blueprint.ui.adapters.IconsCategoriesAdapter
import dev.jahir.frames.extensions.resources.hasContent
import dev.jahir.frames.extensions.resources.lower
import dev.jahir.frames.ui.fragments.base.BaseFramesFragment

class IconsCategoriesFragment : BaseFramesFragment<IconsCategory>() {

    private val iconsCategoriesAdapter: IconsCategoriesAdapter by lazy {
        IconsCategoriesAdapter(::onOpenCategory, ::onIconClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = iconsCategoriesAdapter
        loadData()
    }

    override fun loadData() {
        (activity as? BlueprintActivity)?.loadIconsCategories()
    }

    override fun getFilteredItems(
        originalItems: ArrayList<IconsCategory>,
        filter: String
    ): ArrayList<IconsCategory> {
        if (!filter.hasContent()) return originalItems
        return ArrayList(
            originalItems.map {
                IconsCategory(
                    it.title,
                    ArrayList(
                        it.getIcons().filter { icon -> icon.name.lower().contains(filter.lower()) })
                )
            })
    }

    override fun updateItemsInAdapter(items: ArrayList<IconsCategory>) {
        iconsCategoriesAdapter.categories = items
    }

    private fun onOpenCategory(category: IconsCategory) {

    }

    private fun onIconClick(icon: Icon) {
        
    }

    companion object {
        internal const val TAG = "icons_categories_fragment"

        @JvmStatic
        fun create(): IconsCategoriesFragment = IconsCategoriesFragment()
    }
}