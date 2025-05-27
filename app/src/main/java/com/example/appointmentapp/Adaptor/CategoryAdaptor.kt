package com.example.appointmentapp.Adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appointmentapp.Domain.CategoryModel
import com.example.appointmentapp.databinding.ViewholderCategoryBinding

//Takes a list of CategoryModel (each has a name and picture)
//
//Shows each item in a RecyclerView with a title and image
//
//Uses Glide to load the image from the internet
//
//Uses ViewBinding to access and set the views
class CategoryAdaptor(val items: MutableList<CategoryModel>): RecyclerView.Adapter<CategoryAdaptor.Viewholder>() {
    private lateinit var context:android.content.Context

   inner class Viewholder(val binding: ViewholderCategoryBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdaptor.Viewholder {
       context=parent.context
        val binding= ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdaptor.Viewholder, position: Int) {
        val item=items[position]
        holder.binding.degreetxt.text=item.Name

        Glide.with(context)
            .load(item.Picture)
            .into(holder.binding.img)


    }

    override fun getItemCount(): Int=items.size

}