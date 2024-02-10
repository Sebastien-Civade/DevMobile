package fr.scivade.hyrulecompendium

import android.util.Log
import fr.scivade.hyrulecompendium.activities.MainActivity
import fr.scivade.hyrulecompendium.popup.CreaturePopup
import fr.scivade.hyrulecompendium.popup.EquipmentPopup
import fr.scivade.hyrulecompendium.popup.MaterialPopup
import fr.scivade.hyrulecompendium.popup.MonsterPopup
import fr.scivade.hyrulecompendium.popup.TreasurePopup
import fr.scivade.hyrulecompendium.responses.GetCreatureResponse
import fr.scivade.hyrulecompendium.responses.GetEntriesResponse
import fr.scivade.hyrulecompendium.responses.GetEquipmentResponse
import fr.scivade.hyrulecompendium.responses.GetMaterialResponse
import fr.scivade.hyrulecompendium.responses.GetMonsterResponse
import fr.scivade.hyrulecompendium.responses.GetTreasureResponse
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

fun getCreature(creaturePopup: CreaturePopup, game: String, id: Int, callback: () -> Unit){
    val retrofitService = RetrofitServiceBuilder.getRetrofitService()
    retrofitService.getCreature(id, game).enqueue(object: Callback<GetCreatureResponse> {
        override fun onResponse(call: Call<GetCreatureResponse>, response: Response<GetCreatureResponse>){
            try {
                val responseBody = response.body()!!
                creaturePopup.setCreature(responseBody.creature)
                callback()
            } catch (ex: Exception){
                ex.printStackTrace()
            }
        }
        override fun onFailure(call: Call<GetCreatureResponse>, t: Throwable) {
            Log.e("Error (getCreature - $id) : ", "" + t.message)
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

fun getMaterial(materialPopup: MaterialPopup, game: String, id: Int, callback: () -> Unit){
    val retrofitService = RetrofitServiceBuilder.getRetrofitService()
    retrofitService.getMaterial(id, game).enqueue(object: Callback<GetMaterialResponse> {
        override fun onResponse(call: Call<GetMaterialResponse>, response: Response<GetMaterialResponse>){
            try {
                val responseBody = response.body()!!
                materialPopup.setMaterial(responseBody.material)
                callback()
            } catch (ex: Exception){
                ex.printStackTrace()
            }
        }
        override fun onFailure(call: Call<GetMaterialResponse>, t: Throwable) {
            Log.e("Error (getMaterial - $id) : ", "" + t.message)
        }
    })
}

fun getEquipment(equipmentPopup: EquipmentPopup, game: String, id: Int, callback: () -> Unit){
    val retrofitService = RetrofitServiceBuilder.getRetrofitService()
    retrofitService.getEquipment(id, game).enqueue(object: Callback<GetEquipmentResponse> {
        override fun onResponse(call: Call<GetEquipmentResponse>, response: Response<GetEquipmentResponse>){
            try {
                val responseBody = response.body()!!
                equipmentPopup.setEquipment(responseBody.equipment)
                callback()
            } catch (ex: Exception){
                ex.printStackTrace()
            }
        }
        override fun onFailure(call: Call<GetEquipmentResponse>, t: Throwable) {
            Log.e("Error (getEquipment - $id) : ", "" + t.message)
        }
    })
}

fun getTreasure(treasurePopup: TreasurePopup, game: String, id: Int, callback: () -> Unit){
    val retrofitService = RetrofitServiceBuilder.getRetrofitService()
    retrofitService.getTreasure(id, game).enqueue(object: Callback<GetTreasureResponse> {
        override fun onResponse(call: Call<GetTreasureResponse>, response: Response<GetTreasureResponse>){
            try {
                val responseBody = response.body()!!
                treasurePopup.setTreasure(responseBody.treasure)
                callback()
            } catch (ex: Exception){
                ex.printStackTrace()
            }
        }
        override fun onFailure(call: Call<GetTreasureResponse>, t: Throwable) {
            Log.e("Error (getTreasure - $id) : ", "" + t.message)
        }
    })
}


