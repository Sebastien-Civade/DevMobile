package fr.scivade.hyrulecompendium

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

    fun updateData(callback: () -> Unit) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                BOTWFavoriteList.clear()
                TOTKFavoriteList.clear()
                if (snapshot.hasChild("BOTW")) {
                    val botwSnapshot = snapshot.child("BOTW")
                    for (itemSnapshot in botwSnapshot.children) {
                        val botwId = itemSnapshot.getValue(Int::class.java)
                        if(botwId != null){
                            BOTWFavoriteList.add(botwId)
                        }
                    }
                }
                if (snapshot.hasChild("TOTK")) {
                    val totkSnapshot = snapshot.child("TOTK")
                    for (itemSnapshot in totkSnapshot.children) {
                        val totkId = itemSnapshot.getValue(Int::class.java)
                        if(totkId != null){
                            TOTKFavoriteList.add(totkId)
                        }
                    }
                }
                callback()

            }

            override fun onCancelled(error: DatabaseError) {
                println("Erreur updateData : $error")
                callback()
            }
        })
    }
}