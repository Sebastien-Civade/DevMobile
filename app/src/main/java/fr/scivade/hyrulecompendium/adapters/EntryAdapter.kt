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
import fr.scivade.hyrulecompendium.getMonster
import fr.scivade.hyrulecompendium.popup.MonsterPopup

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
            if (currentItem.category == Tags.MONSTER_CATEGORY){
                val monsterPopup = MonsterPopup(mainActivity)
                getMonster(monsterPopup, mainActivity.getSelectedGame(), currentItem.id){
                    monsterPopup.show()
                }
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}