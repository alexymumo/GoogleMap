package com.alexmumo.car.ui.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexmumo.car.databinding.FragmentPasswordResetBinding

class PasswordResetFragment : Fragment() {
    private lateinit var binding: FragmentPasswordResetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPasswordResetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
