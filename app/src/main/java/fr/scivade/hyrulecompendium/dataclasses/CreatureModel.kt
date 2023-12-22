package fr.scivade.hyrulecompendium.dataclasses

import com.google.gson.annotations.SerializedName

data class CreatureModel (

    @SerializedName("id"               ) var id              : Int,
    @SerializedName("name"             ) var name            : String,
    @SerializedName("category"         ) var category        : String,
    @SerializedName("description"      ) var description     : String,
    @SerializedName("image"            ) var imageUrl        : String,
    @SerializedName("common_locations" ) var locations       : ArrayList<String>,
    @SerializedName("edible"           ) var edible          : Boolean,
    @SerializedName("drops"            ) var drops           : ArrayList<String>? = null,
    @SerializedName("cooking_effect"   ) var cookingEffect   : String? = null,
    @SerializedName("hearts_recovered" ) var heartsRecovered : Float? = null

)