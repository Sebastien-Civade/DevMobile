package fr.scivade.hyrulecompendium

import fr.scivade.hyrulecompendium.responses.GetCreatureResponse
import fr.scivade.hyrulecompendium.responses.GetEntriesResponse
import fr.scivade.hyrulecompendium.responses.GetEntryResponse
import fr.scivade.hyrulecompendium.responses.GetEquipmentResponse
import fr.scivade.hyrulecompendium.responses.GetMaterialResponse
import fr.scivade.hyrulecompendium.responses.GetMonsterResponse
import fr.scivade.hyrulecompendium.responses.GetTreasureResponse
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
        @Path("id") id: Int,
        @Query("game") game: String
    ): Call<GetCreatureResponse>

    @GET("entry/{id}")
    fun getMonster(
        @Path("id") id: Int,
        @Query("game") game: String
    ): Call<GetMonsterResponse>

    @GET("entry/{id}")
    fun getMaterial(
        @Path("id") id: Int,
        @Query("game") game: String
    ): Call<GetMaterialResponse>

    @GET("entry/{id}")
    fun getEquipment(
        @Path("id") id: Int,
        @Query("game") game: String
    ): Call<GetEquipmentResponse>

    @GET("entry/{id}")
    fun getTreasure(
        @Path("id") id: Int,
        @Query("game") game: String
    ): Call<GetTreasureResponse>

    @GET("entry/{id}")
    fun getEntry(
        @Path("id") id: Int,
        @Query("game") game: String
    ): Call<GetEntryResponse>
}