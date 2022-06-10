package com.alexmumo.car.ui.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alexmumo.car.databinding.FragmentPasswordResetBinding
import com.google.firebase.auth.FirebaseAuth

class PasswordResetFragment : Fragment() {
    private lateinit var binding: FragmentPasswordResetBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPasswordResetBinding.inflate(layoutInflater, container, false)
        binding.resetBtn.setOnClickListener {
            sendLink()
        }
        return binding.root
    }

    private fun sendLink() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.sendPasswordResetEmail(binding.resetTextEmail.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_LONG).show()
            }
    }
}
