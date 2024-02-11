package fr.scivade.hyrulecompendium.popup

import android.app.Dialog
import android.graphics.text.LineBreaker
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.scivade.hyrulecompendium.FavoriteRepository
import fr.scivade.hyrulecompendium.R
import fr.scivade.hyrulecompendium.Tags
import fr.scivade.hyrulecompendium.activities.MainActivity
import fr.scivade.hyrulecompendium.dataclasses.MonsterModel

class MonsterPopup(
    private val mainActivity: MainActivity,
) : Dialog(mainActivity) {

    private lateinit var monster: MonsterModel
    private val selectedGame = mainActivity.getSelectedGame()
    private val favoriteList = if (selectedGame == Tags.BOTW) FavoriteRepository.Singleton.BOTWFavoriteList else FavoriteRepository.Singleton.TOTKFavoriteList
    private val repo = FavoriteRepository()
    private val galleryFragment = mainActivity.getGalleryFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_monster)

        resizeWindow()

        setUpCloseButton()

        setUpImage()

        setUpInfo()

        setFav()

        updateFav()
    }

    private fun setUpImage() {
        val imageView = findViewById<ImageView>(R.id.monster_popup_image)
        Glide.with(context).load(Uri.parse(monster.imageUrl)).into(imageView)
    }

    private fun setUpInfo(){
        findViewById<TextView>(R.id.monster_popup_name).text = monster.name

        findViewById<TextView>(R.id.monster_popup_description).apply {
            justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            text = monster.description
        }

        var locationsText = ""
        findViewById<TextView>(R.id.monster_popup_locations).apply {
            if (monster.locations.isNullOrEmpty()){
                text = mainActivity.getString(R.string.none_text)
            } else{
                monster.locations?.forEach {
                    locationsText += it + "\n"
                }
                text = locationsText
            }
        }

        var dropsText = ""
        findViewById<TextView>(R.id.monster_popup_drops).apply {
            if (monster.drops.isNullOrEmpty()){
                text = mainActivity.getString(R.string.none_text)
            } else{
                monster.drops?.forEach {
                    dropsText += it + "\n"
                }
                text = dropsText
            }
        }
    }

    private fun setUpCloseButton(){
        findViewById<ImageView>(R.id.monster_popup_close_icon).setOnClickListener {
            dismiss()
        }
    }

    fun setMonster(monster: MonsterModel){
        this.monster = monster
    }

    private fun resizeWindow(){
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun setFav(){
        if (favoriteList.contains(monster.id)) {
            findViewById<ImageView>(R.id.monster_popup_fav_icon).setImageResource(R.drawable.ic_fav_enabled)
        } else {
            findViewById<ImageView>(R.id.monster_popup_fav_icon).setImageResource(R.drawable.ic_fav_disabled)
        }
    }

    private fun updateFav(){
        findViewById<ImageView>(R.id.monster_popup_fav_icon).setOnClickListener {
            if(favoriteList.contains(monster.id)){
                favoriteList.remove(monster.id)
                repo.sendData(favoriteList, selectedGame){
                    setFav()
                    galleryFragment.refreshData {}
                }
            } else {
                favoriteList.add(monster.id)
                repo.sendData(favoriteList, selectedGame){
                    setFav()
                    galleryFragment.refreshData {}
                }
            }
        }
    }
}