package com.yudhae.submissionkokas.ui.makanan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yudhae.submissionkokas.databinding.FragmentMakananBinding

class MakananFragment : Fragment() {

    private var _binding: FragmentMakananBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gridViewModel =
            ViewModelProvider(this)[MakananViewModel::class.java]

        _binding = FragmentMakananBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textInfoMakanan
        gridViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}