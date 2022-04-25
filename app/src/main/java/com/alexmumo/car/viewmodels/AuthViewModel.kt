package com.alexmumo.car.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmumo.car.data.AuthRepository
import com.alexmumo.car.util.Event
import com.alexmumo.car.util.Resource
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    // login
    private val _login = MutableLiveData<Event<Resource<AuthResult>>>()
    val login: LiveData<Event<Resource<AuthResult>>> = _login

    // register
    private val _register = MutableLiveData<Event<Resource<AuthResult>>>()
    val register: LiveData<Event<Resource<AuthResult>>> = _register

    fun registerUser(name: String, password: String, email: String, phone: String) {
        val error = if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            "Cannot be empty"
        } else null
        error?.let {
            _register.postValue(Event(Resource.Error(it)))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val results = authRepository.registerUser(name, email, password, phone)
            _register.postValue(Event(results))
        }
    }
    fun signInUser(email: String, password: String) {
        var error = if (email.isEmpty()||password.isEmpty()) {
            "Cannot be empty"
        }else null
        error?.let {
            _login.postValue(Event(Resource.Error(it)))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.signInUser(email, password)
            _login.postValue(Event(result))
        }
    }
}
