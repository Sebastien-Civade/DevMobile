package fr.scivade.hyrulecompendium

import com.google.gson.annotations.SerializedName

data class PropertiesModelBOTW (

    @SerializedName("attack"  ) var attack  : Int,
    @SerializedName("defense" ) var defense : Int

        )