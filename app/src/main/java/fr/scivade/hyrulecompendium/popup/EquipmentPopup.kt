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
import fr.scivade.hyrulecompendium.dataclasses.EquipmentModel

class EquipmentPopup(
    private val mainActivity: MainActivity,
) : Dialog(mainActivity) {

    private lateinit var equipment: EquipmentModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_equipment)

        resizeWindow()

        setUpCloseButton()

        setUpImage()

        setUpInfo()
    }

    private fun setUpImage() {
        val imageView = findViewById<ImageView>(R.id.equipment_popup_image)
        Glide.with(context).load(Uri.parse(equipment.imageUrl)).into(imageView)
    }

    private fun setUpInfo(){
        findViewById<TextView>(R.id.equipment_popup_name).text = equipment.name

        findViewById<TextView>(R.id.equipment_popup_description).apply {
            justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            text = equipment.description
        }

        var locationsText = ""
        findViewById<TextView>(R.id.equipment_popup_locations).apply {
            if (equipment.locations.isNullOrEmpty()){
                text = mainActivity.getString(R.string.none_text)
            } else{
                equipment.locations?.forEach {
                    locationsText += it + "\n"
                }
                text = locationsText
            }
        }

        findViewById<TextView>(R.id.equipment_popup_attack).text = equipment.properties.attack.toString()

        findViewById<TextView>(R.id.equipment_popup_defense).text = equipment.properties.defense.toString()

        if (equipment.properties.type == null){
            findViewById<TextView>(R.id.equipment_popup_type_title).isVisible = false
            findViewById<TextView>(R.id.equipment_popup_type).isVisible = false
        } else{
            findViewById<TextView>(R.id.equipment_popup_type).text = equipment.properties.type
        }

        if (equipment.properties.effect == null){
            findViewById<TextView>(R.id.equipment_popup_effect_title).isVisible = false
            findViewById<TextView>(R.id.equipment_popup_effect).isVisible = false
        } else{
            findViewById<TextView>(R.id.equipment_popup_effect).text = equipment.properties.effect
        }

    }

    private fun setUpCloseButton(){
        findViewById<ImageView>(R.id.equipment_popup_close_icon).setOnClickListener {
            dismiss()
        }
    }

    fun setEquipment(monster: EquipmentModel){
        this.equipment = monster
    }

    private fun resizeWindow(){
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}