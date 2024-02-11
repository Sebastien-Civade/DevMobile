package fr.scivade.hyrulecompendium.popup

import android.app.Dialog
import android.graphics.text.LineBreaker
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import fr.scivade.hyrulecompendium.FavoriteRepository
import fr.scivade.hyrulecompendium.R
import fr.scivade.hyrulecompendium.Tags
import fr.scivade.hyrulecompendium.activities.MainActivity
import fr.scivade.hyrulecompendium.dataclasses.MaterialModel
import fr.scivade.hyrulecompendium.getAllEntries

class MaterialPopup(
    private val mainActivity: MainActivity,
) : Dialog(mainActivity) {

    private lateinit var material: MaterialModel
    private val selectedGame = mainActivity.getSelectedGame()
    private val favoriteList = if (selectedGame == Tags.BOTW) FavoriteRepository.Singleton.BOTWFavoriteList else FavoriteRepository.Singleton.TOTKFavoriteList
    private val repo = FavoriteRepository()
    private val galleryFragment = mainActivity.getGalleryFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_material)

        resizeWindow()

        setUpCloseButton()

        setUpImage()

        setUpInfo()

        setFav()

        updateFav()
    }

    private fun setUpImage() {
        val imageView = findViewById<ImageView>(R.id.material_popup_image)
        if(selectedGame == Tags.BOTW){
            Glide.with(context).load(Uri.parse(material.imageUrl)).into(imageView)
        } else {
            Glide.with(mainActivity).load(Uri.parse("https://i.imgur.com/cEbgSy9.png")).into(imageView)
        }
    }

    private fun setUpInfo(){
        findViewById<TextView>(R.id.material_popup_name).text = material.name

        findViewById<TextView>(R.id.material_popup_description).apply {
            justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            text = material.description
        }

        findViewById<TextView>(R.id.material_popup_cooking_effect).text = material.cookingEffect

        findViewById<TextView>(R.id.material_popup_hearts_recovered).text = "${material.heartsRecovered}"

        var locationsText = ""
        findViewById<TextView>(R.id.material_popup_locations).apply {
            if (material.locations.isNullOrEmpty()){
                text = mainActivity.getString(R.string.none_text)
            } else{
                material.locations?.forEach {
                    locationsText += it + "\n"
                }
                text = locationsText
            }
        }

        if (material.fuseAttackPower != null){
            findViewById<TextView>(R.id.material_popup_fuse_attack_power).text = "${material.fuseAttackPower}"
        } else{
            findViewById<TextView>(R.id.material_popup_fuse_attack_power_title).isVisible = false
            findViewById<TextView>(R.id.material_popup_fuse_attack_power).isVisible = false
        }
    }

    private fun setUpCloseButton(){
        findViewById<ImageView>(R.id.material_popup_close_icon).setOnClickListener {
            dismiss()
        }
    }

    fun setMaterial(material: MaterialModel){
        this.material = material
    }

    private fun resizeWindow(){
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun setFav(){
        if (favoriteList.contains(material.id)) {
            findViewById<ImageView>(R.id.material_popup_fav_icon).setImageResource(R.drawable.ic_fav_enabled)
        } else {
            findViewById<ImageView>(R.id.material_popup_fav_icon).setImageResource(R.drawable.ic_fav_disabled)
        }
    }

    private fun updateFav(){
        findViewById<ImageView>(R.id.material_popup_fav_icon).setOnClickListener {
            if(favoriteList.contains(material.id)){
                favoriteList.remove(material.id)
                repo.sendData(favoriteList, selectedGame){
                    setFav()
                    galleryFragment.refreshData {}
                }
            } else {
                favoriteList.add(material.id)
                repo.sendData(favoriteList, selectedGame){
                    setFav()
                    galleryFragment.refreshData {}
                }
            }
        }
    }
}