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
import fr.scivade.hyrulecompendium.R
import fr.scivade.hyrulecompendium.activities.MainActivity
import fr.scivade.hyrulecompendium.dataclasses.CreatureModel

class CreaturePopup(
    private val mainActivity: MainActivity,
) : Dialog(mainActivity) {

    private lateinit var creature: CreatureModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_creature)

        resizeWindow()

        setUpCloseButton()

        setUpImage()

        setUpInfo()
    }

    private fun setUpImage() {
        val imageView = findViewById<ImageView>(R.id.creature_popup_image)
        Glide.with(context).load(Uri.parse(creature.imageUrl)).into(imageView)
    }

    private fun setUpInfo(){
        findViewById<TextView>(R.id.creature_popup_name).text = creature.name

        findViewById<TextView>(R.id.creature_popup_description).apply {
            justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            text = creature.description
        }

        var locationsText = ""
        findViewById<TextView>(R.id.creature_popup_locations).apply {
            if (creature.locations.isNullOrEmpty()){
                text = mainActivity.getString(R.string.none_text)
            } else{
                creature.locations?.forEach {
                    locationsText += it + "\n"
                }
                text = locationsText
            }
        }

        if (!creature.drops.isNullOrEmpty()){
            var dropsText = ""
            findViewById<TextView>(R.id.creature_popup_drops).apply {
                if (creature.drops.isNullOrEmpty()){
                    text = mainActivity.getString(R.string.none_text)
                } else{
                    creature.drops?.forEach {
                        dropsText += it + "\n"
                    }
                    text = dropsText
                }
            }
        } else{
            findViewById<TextView>(R.id.creature_popup_drops_title).isVisible = false
            findViewById<TextView>(R.id.creature_popup_drops).isVisible = false
        }

        if (!creature.cookingEffect.isNullOrEmpty()){
            findViewById<TextView>(R.id.creature_popup_cooking_effect).text = creature.cookingEffect
        } else{
            findViewById<TextView>(R.id.creature_popup_cooking_effect_title).isVisible = false
            findViewById<TextView>(R.id.creature_popup_cooking_effect).isVisible = false
        }

        if (creature.heartsRecovered != null){
            findViewById<TextView>(R.id.creature_popup_hearts_recovered).text = "${creature.heartsRecovered}"
        } else{
            findViewById<TextView>(R.id.creature_popup_hearts_recovered_title).isVisible = false
            findViewById<TextView>(R.id.creature_popup_hearts_recovered).isVisible = false
        }

    }

    private fun setUpCloseButton(){
        findViewById<ImageView>(R.id.creature_popup_close_icon).setOnClickListener {
            dismiss()
        }
    }

    fun setCreature(creature: CreatureModel){
        this.creature = creature
    }

    private fun resizeWindow(){
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}