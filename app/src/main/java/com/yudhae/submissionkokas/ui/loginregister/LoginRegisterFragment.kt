package com.yudhae.submissionkokas.ui.loginregister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yudhae.submissionkokas.databinding.FragmentLoginRegisterBinding

class LoginRegisterFragment : Fragment() {

    private var _binding: FragmentLoginRegisterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginregisyerViewModel =
            ViewModelProvider(this).get(LoginRegisterViewModel::class.java)

        _binding = FragmentLoginRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        loginregisyerViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}