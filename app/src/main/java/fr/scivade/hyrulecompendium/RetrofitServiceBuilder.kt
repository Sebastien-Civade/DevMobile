package fr.scivade.hyrulecompendium

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceBuilder {
    private const val hyruleCompendiumUrl = "https://botw-compendium.herokuapp.com/api/v3/compendium/"
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(hyruleCompendiumUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val retrofitService = buildService(HyruleCompendiumAPI::class.java)

    private fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

    fun getRetrofitService(): HyruleCompendiumAPI {
        return retrofitService
    }
}

