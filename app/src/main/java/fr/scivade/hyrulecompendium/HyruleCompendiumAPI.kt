package fr.scivade.hyrulecompendium

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HyruleCompendiumAPI {

    @GET("all")
    fun getAllEntries(@Query("game") game: String
    ): Call<EntriesModel>

}