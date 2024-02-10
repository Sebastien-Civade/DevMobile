package fr.scivade.hyrulecompendium.responses

import com.google.gson.annotations.SerializedName
import fr.scivade.hyrulecompendium.dataclasses.EntryModel

data class GetEntryResponse (
    @SerializedName("data" ) var entry : EntryModel
)