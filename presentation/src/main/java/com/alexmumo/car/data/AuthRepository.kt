package com.alexmumo.car.data

import com.alexmumo.car.models.User
import com.alexmumo.car.util.Resource
import com.alexmumo.car.util.safeCall
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val databaseReference = FirebaseDatabase.getInstance().getReference("users")

    suspend fun registerUser(name: String, email: String, password: String, phone: String): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val results = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                val uid = results.user?.uid!!
                val user = User(name, email,phone, uid)
                databaseReference.child(uid).setValue(user).await()
                Resource.Success(results)
            }
        }
    }
    suspend fun signInUser(email: String, password: String): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            val results = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(results)
        }
    }
}
