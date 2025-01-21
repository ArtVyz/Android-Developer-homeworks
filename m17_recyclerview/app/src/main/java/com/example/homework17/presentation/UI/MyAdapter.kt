package com.example.homework17.presentation.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework17.databinding.MyViewForAdapterBinding
import com.example.homework17.entity.Photos

class MyAdapter(
    private val onClick: (Photos) -> Unit
) : RecyclerView.Adapter<MySimpleViewHolder>() {

    private var data: List<Photos> = emptyList()
    fun setData(data: List<Photos>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySimpleViewHolder {
        val binding = MyViewForAdapterBinding.inflate(LayoutInflater.from(parent.context))
        return MySimpleViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MySimpleViewHolder, position: Int) {
        val item = data[position]
        with(holder.binding) {
            rover.text = "Rover: ${item.rover.name}"
            sol.text = "Sol: ${item.id}"
            data.text = "Data: ${item.earth_date}"
            camera.text = "Camera: ${item.camera.name}"
            item.let {
                Glide
                    .with(photoImage.context)
                    .load(it.img_src)
                    .into(photoImage)
            }
        }
        holder.binding.root.setOnClickListener {
            onClick(item)
        }
    }

}

class MySimpleViewHolder(val binding: MyViewForAdapterBinding) :
    RecyclerView.ViewHolder(binding.root)