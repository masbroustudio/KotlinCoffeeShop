package com.yudhae.submissionkokas.ui.minuman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MinumanViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Maaf, Halaman ini belum tersedia."
    }
    val text: LiveData<String> = _text
}