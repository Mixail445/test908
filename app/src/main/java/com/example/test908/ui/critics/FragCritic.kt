package com.example.test908.ui.critics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test908.databinding.CriticLayoutBinding

class FragCritic : Fragment() {
    private var _binding: CriticLayoutBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = CriticLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}