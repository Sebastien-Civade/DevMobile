package fr.scivade.hyrulecompendium.adapters

import android.graphics.text.LineBreaker
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.scivade.hyrulecompendium.R
import fr.scivade.hyrulecompendium.Tags
import fr.scivade.hyrulecompendium.activities.MainActivity
import fr.scivade.hyrulecompendium.dataclasses.EntryModel
import fr.scivade.hyrulecompendium.getCreature
import fr.scivade.hyrulecompendium.getEquipment
import fr.scivade.hyrulecompendium.getMaterial
import fr.scivade.hyrulecompendium.getMonster
import fr.scivade.hyrulecompendium.getTreasure
import fr.scivade.hyrulecompendium.popup.CreaturePopup
import fr.scivade.hyrulecompendium.popup.EquipmentPopup
import fr.scivade.hyrulecompendium.popup.MaterialPopup
import fr.scivade.hyrulecompendium.popup.MonsterPopup
import fr.scivade.hyrulecompendium.popup.TreasurePopup

class EntryAdapter(
    private val mainActivity: MainActivity,
    private val itemList: List<EntryModel>
) : RecyclerView.Adapter<EntryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.item_card_name)
        val description: TextView = view.findViewById(R.id.item_card_description)
        val image: ImageView = view.findViewById(R.id.item_card_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.name.text = currentItem.name

        holder.description.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        holder.description.text = currentItem.description

        Glide.with(mainActivity).load(Uri.parse(currentItem.imageUrl)).into(holder.image)

        holder.itemView.setOnClickListener {
            when(currentItem.category){
                Tags.CREATURE_CATEGORY -> {
                    val creaturePopup = CreaturePopup(mainActivity)
                    getCreature(creaturePopup, mainActivity.getSelectedGame(), currentItem.id){
                        creaturePopup.show()
                    }
                }
                Tags.MONSTER_CATEGORY -> {
                    val monsterPopup = MonsterPopup(mainActivity)
                    getMonster(monsterPopup, mainActivity.getSelectedGame(), currentItem.id){
                        monsterPopup.show()
                    }
                }
                Tags.MATERIAL_CATEGORY -> {
                    val materialPopup = MaterialPopup(mainActivity)
                    getMaterial(materialPopup, mainActivity.getSelectedGame(), currentItem.id){
                        materialPopup.show()
                    }
                }
                Tags.EQUIPMENT_CATEGORY -> {
                    val equipmentPopup = EquipmentPopup(mainActivity)
                    getEquipment(equipmentPopup, mainActivity.getSelectedGame(), currentItem.id){
                        equipmentPopup.show()
                    }
                }
                Tags.TREASURE_CATEGORY -> {
                    val treasurePopup = TreasurePopup(mainActivity)
                    getTreasure(treasurePopup, mainActivity.getSelectedGame(), currentItem.id){
                        treasurePopup.show()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}