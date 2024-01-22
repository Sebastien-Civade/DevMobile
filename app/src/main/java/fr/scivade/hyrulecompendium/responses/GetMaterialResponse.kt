package fr.scivade.hyrulecompendium.responses

import com.google.gson.annotations.SerializedName
import fr.scivade.hyrulecompendium.dataclasses.MaterialModel

data class GetMaterialResponse (

    @SerializedName("data" ) var material : MaterialModel

)