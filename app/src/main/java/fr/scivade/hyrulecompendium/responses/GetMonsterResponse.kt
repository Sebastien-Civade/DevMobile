package fr.scivade.hyrulecompendium.responses

import com.google.gson.annotations.SerializedName
import fr.scivade.hyrulecompendium.dataclasses.MonsterModel

data class GetMonsterResponse (

    @SerializedName("data" ) var monster : MonsterModel

)