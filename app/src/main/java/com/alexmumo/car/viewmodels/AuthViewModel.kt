package com.alexmumo.car.viewmodels

import androidx.lifecycle.ViewModel
import com.alexmumo.car.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AuthViewModel constructor(private val authRepository: AuthRepository) : ViewModel()
