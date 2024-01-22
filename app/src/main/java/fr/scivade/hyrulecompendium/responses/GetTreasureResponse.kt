package fr.scivade.hyrulecompendium.responses

import com.google.gson.annotations.SerializedName
import fr.scivade.hyrulecompendium.dataclasses.TreasureModel

data class GetTreasureResponse (

    @SerializedName("data" ) var treasure : TreasureModel

)