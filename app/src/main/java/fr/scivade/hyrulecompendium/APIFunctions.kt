package fr.scivade.hyrulecompendium

import android.util.Log
import fr.scivade.hyrulecompendium.activities.MainActivity
import fr.scivade.hyrulecompendium.popup.MonsterPopup
import fr.scivade.hyrulecompendium.responses.GetEntriesResponse
import fr.scivade.hyrulecompendium.responses.GetMonsterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

fun getAllEntries(mainActivity: MainActivity, game: String, callback: () -> Unit){
    val retrofitService = RetrofitServiceBuilder.getRetrofitService()

    retrofitService.getAllEntries(game).enqueue(object: Callback<GetEntriesResponse> {
        override fun onResponse(call: Call<GetEntriesResponse>, response: Response<GetEntriesResponse>){
            try {
                val responseBody = response.body()!!
                mainActivity.getGalleryFragment().setEntryList(responseBody.entries)
                if(responseBody.entries.isEmpty()) Session.showToast(R.string.get_all_entries_no_result_toast)
                callback()
            } catch (ex: Exception){
                ex.printStackTrace()
            }
        }
        override fun onFailure(call: Call<GetEntriesResponse>, t: Throwable) {
            Log.e("Error (getAllEntries) : ", "" + t.message)
        }
    })
}

fun getCategoryEntries(mainActivity: MainActivity, game: String, category: String, callback: () -> Unit){
    val retrofitService = RetrofitServiceBuilder.getRetrofitService()
    retrofitService.getCategoryEntries(category, game).enqueue(object: Callback<GetEntriesResponse> {
        override fun onResponse(call: Call<GetEntriesResponse>, response: Response<GetEntriesResponse>){
            try {
                val responseBody = response.body()!!
                mainActivity.getGalleryFragment().setEntryList(responseBody.entries)
                if(responseBody.entries.isEmpty()) Session.showToast(R.string.get_all_entries_no_result_toast)
                callback()
            } catch (ex: Exception){
                ex.printStackTrace()
            }
        }
        override fun onFailure(call: Call<GetEntriesResponse>, t: Throwable) {
            Log.e("Error (getCategoryEntries - $category) : ", "" + t.message)
        }
    })
}

fun getMonster(monsterPopup: MonsterPopup, game: String, id: Int, callback: () -> Unit){
    val retrofitService = RetrofitServiceBuilder.getRetrofitService()
    retrofitService.getMonster(id, game).enqueue(object: Callback<GetMonsterResponse> {
        override fun onResponse(call: Call<GetMonsterResponse>, response: Response<GetMonsterResponse>){
            try {
                val responseBody = response.body()!!
                monsterPopup.setMonster(responseBody.monster)
                callback()
            } catch (ex: Exception){
                ex.printStackTrace()
            }
        }
        override fun onFailure(call: Call<GetMonsterResponse>, t: Throwable) {
            Log.e("Error (getMonster - $id) : ", "" + t.message)
        }
    })
}
