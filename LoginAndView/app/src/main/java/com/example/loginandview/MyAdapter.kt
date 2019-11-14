package com.example.loginandview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
class MyAdapter(val movieList: ArrayList<DataItem>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var index: Int = 0
    val myList: ArrayList<DataItem> = movieList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: DataItem=movieList[position]
        holder.textView1.text = movie.title
        holder.textView2.text = movie.description
        Picasso.get().load("http://image.tmdb.org/t/p/w500/"+movie.url).into(holder.imageView)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView1 = itemView.findViewById(R.id.textView1) as TextView
        val textView2 = itemView.findViewById(R.id.textView2) as TextView
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        init {
            itemView.setOnClickListener{
                val intent = Intent(itemView.context,SingleMovieActivity::class.java)
                intent.putExtra("index",adapterPosition)
                itemView.context.startActivity(intent)
            }
        }
    }
}