package com.dd.data.repository

import android.util.Log
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.model.ResponseMakalModel
import com.dd.domain.repository.Repository
import com.google.firebase.database.*

class FirebaseRepository : Repository.FirebaseRepository {
    private var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var databaseReference: DatabaseReference

    companion object {
        private const val FIREBASE_LIKE_TABLE_NAME = "makal_like"
    }

    init {
        databaseReference = firebaseDatabase.getReference(FIREBASE_LIKE_TABLE_NAME)
    }

    override suspend fun writeToDb(requestMakalModel: RequestMakalModel): ResponseMakalModel {
        val makalModel = requestMakalModel.makalModel
        databaseReference.child(makalModel.makal_id.toString()).setValue(makalModel.makal_like.toString())
        return ResponseMakalModel()
    }

    override suspend fun readFromDb(requestMakalModel: RequestMakalModel): ResponseMakalModel {
        // Read from the database
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.value
                Log.i("autolog", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.i("autolog", "Failed to read value.", error.toException())
            }
        })

        return ResponseMakalModel()
    }
}