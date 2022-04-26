package com.alexmumo.car.ui.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alexmumo.car.R
import com.alexmumo.car.databinding.FragmentLoginBinding
import com.alexmumo.car.util.EventObserver
import com.alexmumo.car.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        signInUser()
        binding.loginbtn.setOnClickListener {
            viewModel.signInUser(
                binding.emailLayout.editText?.text.toString(),
                binding.passwordLayout.editText?.text.toString()
            )
        }
        binding.textRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.forgotTextview.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_passwordResetFragment)
        }
        return binding.root
    }

    private fun signInUser() {
        viewModel.login.observe(
            viewLifecycleOwner,
            EventObserver(
                onError = {
                    binding.progressBar.isVisible = false
                },
                onLoading = {
                    binding.progressBar.isVisible = true
                }
            ) {
                binding.progressBar.isVisible = false
                findNavController().navigate(R.id.action_loginFragment_to_mapsFragment)
            }
        )
    }
}
