package com.example.matches.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.matches.model.RegisterDetails
import kotlinx.coroutines.Dispatchers

class RegistrationViewModel : ViewModel() {

    fun registerPlayer(registerDetails: RegisterDetails) = liveData(Dispatchers.IO) {
        emit(RegistrationRepository().registerPlayer(registerDetails))
    }

}