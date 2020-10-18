package com.dd.data.repository

import android.util.Log
import com.dd.domain.model.RequestMakalModel
import com.dd.domain.model.ResponseMakalModel
import com.dd.domain.repository.Repository
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class FirebaseRepository : Repository.FirebaseRepository {
    private lateinit var database: DatabaseReference

    companion object {
        private const val FIREBASE_LIKE_TABLE_NAME = "makal_like"
    }

    init {
        database = Firebase.database.getReference(FIREBASE_LIKE_TABLE_NAME)
    }

    override suspend fun writeToDb(requestMakalModel: RequestMakalModel): ResponseMakalModel {
        val makalModel = requestMakalModel.makalModel
        val result = database.child(makalModel.makal_id.toString()).setValue(makalModel.makal_like.toString()).isSuccessful
        return ResponseMakalModel(result = result.toString())
    }

    override suspend fun updateDb(requestMakalModel: RequestMakalModel) {
        val makalModel = requestMakalModel.makalModel
        val childUpdates = hashMapOf<String, Any>(makalModel.makal_id.toString() to makalModel.makal_like.toString())
        database.updateChildren(childUpdates)
    }


    override suspend fun readFromDb(requestMakalModel: RequestMakalModel) {
        // Read from the database

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val post = dataSnapshot.getValue<HashMap<String, String>>()
                Log.i("autolog", "Value is: $post")

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.i("autolog", "Failed to read value.", error.toException())
            }
        }


//        database.addValueEventListener(postListener)
        database.addListenerForSingleValueEvent(postListener)
    }


    override suspend fun onLikeClicked(requestMakalModel: RequestMakalModel) {
        database.runTransaction(object : Transaction.Handler {
            override fun doTransaction(mutableData: MutableData): Transaction.Result {

                var value: Long = 0
                if (mutableData.child(requestMakalModel.makalModel.makal_id.toString()).value != null) {
                    val numQuestions = mutableData.child(requestMakalModel.makalModel.makal_id.toString()).value as String
                    value = numQuestions.toLong(16)
                }
                value++
                val incHex = java.lang.Long.toHexString(value)

                mutableData.child(requestMakalModel.makalModel.makal_id.toString()).value = incHex
                Log.i("autolog", "doTransaction: $mutableData")
                return Transaction.success(mutableData)
            }

            override fun onComplete(
                    databaseError: DatabaseError?,
                    committed: Boolean,
                    currentData: DataSnapshot?
            ) {
                // Transaction completed
            }
        })
    }
}