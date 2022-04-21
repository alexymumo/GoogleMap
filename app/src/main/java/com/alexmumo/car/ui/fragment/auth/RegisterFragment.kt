package com.alexmumo.car.ui.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alexmumo.car.R
import com.alexmumo.car.databinding.FragmentRegisterBinding
import com.alexmumo.car.util.EventObserver
import com.alexmumo.car.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        registerUser()
        binding.firstnameText.editableText?.toString()
        binding.passwordText.editableText?.toString()
        binding.textEmail.editableText?.toString()
        binding.textRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        return binding.root
    }

    private fun registerUser() {
        viewModel.register.observe(viewLifecycleOwner, EventObserver(
            onError = {
                binding.progressBar.isVisible = false
            },
            onLoading = {
                binding.progressBar.isVisible = true
            }

        ){
            binding.progressBar.isVisible = false
            Toast.makeText(requireContext(), "Register", Toast.LENGTH_LONG).show()
        }
        )

    }
}
