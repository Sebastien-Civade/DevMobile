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
import fr.scivade.hyrulecompendium.R
import fr.scivade.hyrulecompendium.activities.MainActivity
import fr.scivade.hyrulecompendium.dataclasses.TreasureModel

class TreasurePopup(
    private val mainActivity: MainActivity,
) : Dialog(mainActivity) {

    private lateinit var treasure: TreasureModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_treasure)

        resizeWindow()

        setUpCloseButton()

        setUpImage()

        setUpInfo()
    }

    private fun setUpImage() {
        val imageView = findViewById<ImageView>(R.id.treasure_popup_image)
        Glide.with(context).load(Uri.parse(treasure.imageUrl)).into(imageView)
    }

    private fun setUpInfo(){
        findViewById<TextView>(R.id.treasure_popup_name).text = treasure.name

        findViewById<TextView>(R.id.treasure_popup_description).apply {
            justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            text = treasure.description
        }

        var locationsText = ""
        findViewById<TextView>(R.id.treasure_popup_locations).apply {
            if (treasure.locations.isNullOrEmpty()){
                text = mainActivity.getString(R.string.none_text)
            } else{
                treasure.locations?.forEach {
                    locationsText += it + "\n"
                }
                text = locationsText
            }
        }

        var dropsText = ""
        findViewById<TextView>(R.id.treasure_popup_drops).apply {
            if (treasure.drops.isNullOrEmpty()){
                text = mainActivity.getString(R.string.none_text)
            } else{
                treasure.drops?.forEach {
                    dropsText += it + "\n"
                }
                text = dropsText
            }
        }
    }

    private fun setUpCloseButton(){
        findViewById<ImageView>(R.id.treasure_popup_close_icon).setOnClickListener {
            dismiss()
        }
    }

    fun setTreasure(monster: TreasureModel){
        this.treasure = monster
    }

    private fun resizeWindow(){
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}