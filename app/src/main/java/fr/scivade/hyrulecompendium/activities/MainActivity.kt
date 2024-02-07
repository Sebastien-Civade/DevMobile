package fr.scivade.hyrulecompendium.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import com.google.firebase.FirebaseApp
import fr.scivade.hyrulecompendium.FavoriteRepository
import fr.scivade.hyrulecompendium.fragments.GalleryFragment
import fr.scivade.hyrulecompendium.R
import fr.scivade.hyrulecompendium.Session
import fr.scivade.hyrulecompendium.Tags

class MainActivity : AppCompatActivity() {
    private val galleryFragment= GalleryFragment(this)

    private var selectedGame: String = Tags.BOTW
    private var selectedCategory: String = Tags.NO_CATEGORY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = FavoriteRepository()

        repo.updateData{
            setUpToolbar(this)

            setUpToolBarListener()

            setUpGameSwitchListener()

            Session.setToast(this)

            launchGallery()
        }
    }

    private fun launchGallery(){
        galleryFragment.refreshData{
            loadGalleryFragment()
        }
    }

    private fun setUpToolbar(mainActivityContext: MainActivity){
        val toolBar = findViewById<Toolbar>(R.id.main_activity_toolbar)
        toolBar.apply {
            setTitle(R.string.toolbar_title)
            setTitleTextAppearance(mainActivityContext, R.style.ToolbarTheme)
        }
        setSupportActionBar(toolBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.category_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpToolBarListener(){
        val toolbar = findViewById<Toolbar>(R.id.main_activity_toolbar)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.category_menu_creatures -> {
                    selectedCategory = Tags.CREATURE_CATEGORY
                    galleryFragment.refreshData{
                        galleryFragment.changeCategoryInfo()
                    }
                    return@setOnMenuItemClickListener true
                }
                R.id.category_menu_monsters -> {
                    selectedCategory = Tags.MONSTER_CATEGORY
                    galleryFragment.refreshData{
                        galleryFragment.changeCategoryInfo()
                    }
                    return@setOnMenuItemClickListener true
                }
                R.id.category_menu_materials -> {
                    selectedCategory = Tags.MATERIAL_CATEGORY
                    galleryFragment.refreshData{
                        galleryFragment.changeCategoryInfo()
                    }
                    return@setOnMenuItemClickListener true
                }
                R.id.category_menu_equipments -> {
                    selectedCategory = Tags.EQUIPMENT_CATEGORY
                    galleryFragment.refreshData{
                        galleryFragment.changeCategoryInfo()
                    }
                    return@setOnMenuItemClickListener true
                }
                R.id.category_menu_treasures -> {
                    selectedCategory = Tags.TREASURE_CATEGORY
                    galleryFragment.refreshData{
                        galleryFragment.changeCategoryInfo()
                    }
                    return@setOnMenuItemClickListener true
                }
                else -> false
            }
        }
    }

    private fun loadGalleryFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_activity_fragment_container, galleryFragment)
            .commit()
    }

    fun getGalleryFragment() : GalleryFragment {
        return galleryFragment
    }

    private fun setUpGameSwitchListener(){
        val gameSwitch = findViewById<SwitchCompat>(R.id.game_switch)
        gameSwitch.setOnCheckedChangeListener { _, isChecked ->
            selectedGame = if (isChecked) Tags.TOTK else Tags.BOTW
            galleryFragment.refreshData{}
        }
    }

    fun getSelectedGame() : String {
        return selectedGame
    }

    fun getSelectedCategory() : String {
        return selectedCategory
    }
}