package fr.scivade.hyrulecompendium.dataclasses

import com.google.gson.annotations.SerializedName

data class PropertiesModel (

    @SerializedName("attack"  ) var attack  : Int,
    @SerializedName("defense" ) var defense : Int,
    @SerializedName("effect"  ) var effect  : String? = null,
    @SerializedName("type"    ) var type    : String? = null,

)