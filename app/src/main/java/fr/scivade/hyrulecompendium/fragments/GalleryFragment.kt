package fr.scivade.hyrulecompendium.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.scivade.hyrulecompendium.FavoriteRepository.Singleton.BOTWFavoriteList
import fr.scivade.hyrulecompendium.FavoriteRepository.Singleton.TOTKFavoriteList
import fr.scivade.hyrulecompendium.R
import fr.scivade.hyrulecompendium.Tags
import fr.scivade.hyrulecompendium.activities.MainActivity
import fr.scivade.hyrulecompendium.adapters.EntryAdapter
import fr.scivade.hyrulecompendium.adapters.EntryCardDecoration
import fr.scivade.hyrulecompendium.dataclasses.EntryModel
import fr.scivade.hyrulecompendium.getAllEntries
import fr.scivade.hyrulecompendium.getCategoryEntries
import fr.scivade.hyrulecompendium.getEntriesById

class GalleryFragment(private val mainActivity: MainActivity) : Fragment() {
    private var entryList = ArrayList<EntryModel>()

    private val entryAdapter = EntryAdapter(mainActivity, entryList)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        setUpRecyclerView(view)

        return view
    }

    fun refreshData(callback: () -> Unit){
        val previousSize = entryList.size
        val selectedGame = mainActivity.getSelectedGame()
        val category = mainActivity.getSelectedCategory()

        when (category) {
            Tags.NO_CATEGORY -> {
                getAllEntries(mainActivity, selectedGame) {
                    notifyItemListChange(previousSize)
                    callback()
                }
            }
            Tags.FAV_CATEGORY -> {
                val favoriteList = if (selectedGame == Tags.BOTW) BOTWFavoriteList else TOTKFavoriteList
                getEntriesById(mainActivity, selectedGame, favoriteList){
                        notifyItemListChange(previousSize)
                        callback()
                }
            }
            else -> {
                getCategoryEntries(mainActivity, selectedGame, category){
                    notifyItemListChange(previousSize)
                    callback()
                }
            }
        }
    }

    private fun notifyItemListChange(previousSize: Int){
        entryAdapter.apply {
            notifyItemRangeRemoved(0, previousSize)
            notifyItemRangeInserted(0, entryList.size)
        }
    }

    private fun setUpRecyclerView(view: View){
        val itemRecyclerView = view.findViewById<RecyclerView>(R.id.gallery_fragment_recycler_view)
        val itemCardSpacing = mainActivity.resources.getDimensionPixelSize(R.dimen.item_card_spacing)
        itemRecyclerView.addItemDecoration(EntryCardDecoration(itemCardSpacing))
        itemRecyclerView.adapter = entryAdapter
    }

    fun changeCategoryInfo(){
        val categoryTitle = view?.findViewById<TextView>(R.id.gallery_fragment_category_title)
        val categoryIcon = view?.findViewById<ImageView>(R.id.gallery_fragment_category_icon)
        when(mainActivity.getSelectedCategory()){
            Tags.CREATURE_CATEGORY -> {
                categoryTitle?.text = getString(R.string.creatures_title)
                categoryIcon?.setImageResource(R.drawable.ic_creature)
            }
            Tags.MONSTER_CATEGORY -> {
                categoryTitle?.text = getString(R.string.monsters_title)
                categoryIcon?.setImageResource(R.drawable.ic_monster)
            }
            Tags.MATERIAL_CATEGORY -> {
                categoryTitle?.text = getString(R.string.materials_title)
                categoryIcon?.setImageResource(R.drawable.ic_material)
            }
            Tags.EQUIPMENT_CATEGORY -> {
                categoryTitle?.text = getString(R.string.equipments_title)
                categoryIcon?.setImageResource(R.drawable.ic_equipment)
            }
            Tags.TREASURE_CATEGORY -> {
                categoryTitle?.text = getString(R.string.treasures_title)
                categoryIcon?.setImageResource(R.drawable.ic_treasure)
            }
            Tags.FAV_CATEGORY -> {
                categoryTitle?.text = getString(R.string.fav_title)
                categoryIcon?.setImageResource(R.drawable.ic_fav_enabled)
            }
        }
    }

    fun setEntryList(entries: ArrayList<EntryModel>){
        entryList.apply {
            clear()
            addAll(entries)
        }
    }
}