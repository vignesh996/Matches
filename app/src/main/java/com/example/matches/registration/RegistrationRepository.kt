package com.example.matches.registration

import android.util.Log
import com.example.matches.helper.ApisResponse
import com.example.matches.model.ErrorResponse
import com.example.matches.model.RegisterDetails
import com.example.matches.model.RegisterResponse
import com.example.matches.network.ApiCall
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class RegistrationRepository {

    suspend fun registerPlayer(registerDetails: RegisterDetails)  : ApisResponse<RegisterResponse> {

        return try {
            val callApi = ApiCall.retrofitClient.registerPlayer(registerDetails)
            ApisResponse.Success(callApi)
        } catch (e: HttpException) {
            ApisResponse.Error(e)


        }
    }

    private fun errorMessagefromapi(httpException: HttpException): String? {
        var errorMessage: String? = null
        val error = httpException.response()?.errorBody()

        try {
            val adapter = Gson().getAdapter(ErrorResponse::class.java)
            val errorParser = adapter.fromJson(error?.string())
            errorMessage = errorParser.error.message
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            return errorMessage
        }
    }
}