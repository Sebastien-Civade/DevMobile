package fr.scivade.hyrulecompendium.dataclasses

import com.google.gson.annotations.SerializedName

data class EquipmentModel (

    @SerializedName("id"               ) var id          : Int,
    @SerializedName("name"             ) var name        : String,
    @SerializedName("category"         ) var category    : String,
    @SerializedName("description"      ) var description : String,
    @SerializedName("image"            ) var imageUrl    : String,
    @SerializedName("common_locations" ) var locations   : ArrayList<String>? = null,
    @SerializedName("properties"       ) var properties  : PropertiesModel,

)