package com.example.kisahnesiasimpleapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kisahnesiasimpleapp.DetailActivity
import com.example.kisahnesiasimpleapp.R
import com.example.kisahnesiasimpleapp.model.Story


class ListStoryAdapter (private val listStory: ArrayList<Story>) : RecyclerView.Adapter<ListStoryAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleItemTextView: TextView = itemView.findViewById(R.id.titleItemTextView)
        val synopsisItemTextView: TextView = itemView.findViewById(R.id.synopsisItemTextView)
        val imageItemImageView: ImageView = itemView.findViewById(R.id.imageItemImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listStory.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, synopsis, image) = listStory[position]
        holder.titleItemTextView.text = title
        holder.synopsisItemTextView.text = synopsis
        holder.imageItemImageView.setImageResource(image)
        holder.itemView.setOnClickListener {
            val moveIntent = Intent(holder.itemView.context, DetailActivity::class.java)
            moveIntent.putExtra(DetailActivity.EXTRA_STORY, listStory[position])
            holder.itemView.context.startActivity(moveIntent)
        }
    }
}