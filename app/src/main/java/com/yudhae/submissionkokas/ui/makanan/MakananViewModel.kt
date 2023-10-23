package com.yudhae.submissionkokas.ui.makanan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MakananViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Halaman Minuman"
    }
    val text: LiveData<String> = _text
}