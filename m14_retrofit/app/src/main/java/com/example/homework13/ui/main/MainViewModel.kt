package com.example.homework13.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework13.Results
import com.example.homework13.RetrofitInstance
import com.example.homework13.State
import com.example.homework13.UsersInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(State.LOADING)
    val state = _state.asStateFlow()
    val searchingString = MutableStateFlow("")

    fun getUserModel(){
        RetrofitInstance.searchUserApi.getUserInfo().enqueue(object : Callback<UsersInfo> {
            override fun onResponse(
                call: Call<UsersInfo>,
                response: Response<UsersInfo>
            ) {
                if (response.isSuccessful) {
                    val user = response.body() ?: return
                    searchingString.value = user.toString()
                    _state.value = State.SUCCESS
                }
            }

            override fun onFailure(call: Call<UsersInfo>, t: Throwable) {
                Log.e("RetrofitText", "GetUserFailure11111111")
                _state.value = State.ERROR
            }
        }
        )
    }
}