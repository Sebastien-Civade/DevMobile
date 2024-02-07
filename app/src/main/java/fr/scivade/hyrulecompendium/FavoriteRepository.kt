package fr.scivade.hyrulecompendium

import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.scivade.hyrulecompendium.FavoriteRepository.Singleton.BOTWFavoriteList
import fr.scivade.hyrulecompendium.FavoriteRepository.Singleton.TOTKFavoriteList
import fr.scivade.hyrulecompendium.FavoriteRepository.Singleton.dbRef

class FavoriteRepository {
    object Singleton{
        val dbRef = FirebaseDatabase.getInstance().getReference("FavoriteList")
        val BOTWFavoriteList = arrayListOf<Int>()
        val TOTKFavoriteList = arrayListOf<Int>()
    }

    fun updateData(callback: ()-> Unit){
        dbRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // Check if the snapshot has data for "BOTW"
                if (snapshot.hasChild("BOTW")) {
                    val botwSnapshot = snapshot.child("BOTW")
                    for (itemSnapshot in botwSnapshot.children) {
                        val value = itemSnapshot.getValue(Int::class.java)
                        value?.let { BOTWFavoriteList.add(it) }
                    }
                }

                // Check if the snapshot has data for "TOTK"
                if (snapshot.hasChild("TOTK")) {
                    val totkSnapshot = snapshot.child("TOTK")
                    for (itemSnapshot in totkSnapshot.children) {
                        val value = itemSnapshot.getValue(Int::class.java)
                        value?.let { TOTKFavoriteList.add(it) }
                    }
                }

                callback()
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }
}