package com.example.homework13.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework13.RetrofitInstance
import com.example.homework13.UsersInfo
import com.example.homework13.databinding.FragmentMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RetrofitInstance.searchUserApi.getUserInfo().enqueue(object : Callback<List<UsersInfo>> {
            override fun onResponse(
                call: Call<List<UsersInfo>>,
                response: Response<List<UsersInfo>>
            ) {
                val user = response.body()?.first() ?: return
            }
            override fun onFailure(call: Call<List<UsersInfo>>, t: Throwable) {
                Log.e("Network", "Something went wro")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}