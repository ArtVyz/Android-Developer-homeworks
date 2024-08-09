package com.example.homewrk16.data

import com.example.homewrk16.data.api.RetrofitInstance
import com.example.homewrk16.entity.UsefulActivity
import javax.inject.Inject

class UsefulActivitiesRepository @Inject constructor() {
    suspend fun getUsefulActivity () : UsefulActivity {
        return RetrofitInstance.searchUserApi.getActivity()
    }
}