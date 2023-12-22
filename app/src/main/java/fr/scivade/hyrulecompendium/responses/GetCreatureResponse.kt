package fr.scivade.hyrulecompendium.responses

import com.google.gson.annotations.SerializedName
import fr.scivade.hyrulecompendium.dataclasses.CreatureModel

data class GetCreatureResponse (

    @SerializedName("data" ) var creature : CreatureModel

)