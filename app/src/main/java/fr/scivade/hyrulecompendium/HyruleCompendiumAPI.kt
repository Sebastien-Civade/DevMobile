package fr.scivade.hyrulecompendium

import fr.scivade.hyrulecompendium.responses.GetCreatureResponse
import fr.scivade.hyrulecompendium.responses.GetEntriesResponse
import fr.scivade.hyrulecompendium.responses.GetMonsterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HyruleCompendiumAPI {

    @GET("all")
    fun getAllEntries(@Query("game") game: String
    ): Call<GetEntriesResponse>

    @GET("category/{category}")
    fun getCategoryEntries(
        @Path("category") category: String,
        @Query("game") game: String
    ): Call<GetEntriesResponse>

    @GET("entry/{id}")
    fun getCreature(
        @Path("id") category: Int,
        @Query("game") game: String
    ): Call<GetCreatureResponse>

    @GET("entry/{id}")
    fun getMonster(
        @Path("id") category: Int,
        @Query("game") game: String
    ): Call<GetMonsterResponse>
}