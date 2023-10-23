package com.yudhae.submissionkokas.ui.loginregister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginRegisterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Belum punya akun?. Daftarkan segera."
    }
    val text: LiveData<String> = _text
}