package fr.scivade.hyrulecompendium.responses

import com.google.gson.annotations.SerializedName
import fr.scivade.hyrulecompendium.dataclasses.EquipmentModel

data class GetEquipmentResponse (

    @SerializedName("data" ) var equipment : EquipmentModel

)