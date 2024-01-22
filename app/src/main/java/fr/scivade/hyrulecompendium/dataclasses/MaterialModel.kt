package fr.scivade.hyrulecompendium.dataclasses

import com.google.gson.annotations.SerializedName

data class MaterialModel (

    @SerializedName("id"                ) var id              : Int,
    @SerializedName("name"              ) var name            : String,
    @SerializedName("category"          ) var category        : String,
    @SerializedName("description"       ) var description     : String,
    @SerializedName("image"             ) var imageUrl        : String,
    @SerializedName("common_locations"  ) var locations       : ArrayList<String>? = null,
    @SerializedName("hearts_recovered"  ) var heartsRecovered : Float,
    @SerializedName("cooking_effect"    ) var cookingEffect   : String,
    @SerializedName("fuse_attack_power" ) var fuseAttackPower : Int? = null,

)