package com.example.homework18.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework18.databinding.MyViewForAdapterBinding
import com.example.homework18.entity.Photo

class MyAdapter() : RecyclerView.Adapter<MySimpleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySimpleViewHolder {
        val binding = MyViewForAdapterBinding.inflate(LayoutInflater.from(parent.context))
        return MySimpleViewHolder(binding)
    }
    private var data = emptyList<Photo>()
    fun setData (data: List<Photo>) {
        this.data = data
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MySimpleViewHolder, position: Int) {
        val item = data[position]
        with(holder.binding) {
            photoDate.text = item.data
            item.let {
                Glide
                    .with(savedPhoto.context)
                    .load(it.path)
                    .into(savedPhoto)
            }
        }
    }

}

class MySimpleViewHolder(val binding: MyViewForAdapterBinding) :
    RecyclerView.ViewHolder(binding.root)