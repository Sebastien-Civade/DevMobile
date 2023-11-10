package fr.scivade.hyrulecompendium

import com.google.gson.annotations.SerializedName

data class EntriesModel (

    @SerializedName("data" ) var entries : List<EntryModel>

)