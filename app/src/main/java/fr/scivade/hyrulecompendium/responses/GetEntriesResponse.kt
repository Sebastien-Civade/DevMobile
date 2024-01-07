package fr.scivade.hyrulecompendium.responses

import com.google.gson.annotations.SerializedName
import fr.scivade.hyrulecompendium.dataclasses.EntryModel

data class GetEntriesResponse (

    @SerializedName("data" ) var entries : ArrayList<EntryModel> = arrayListOf()

)