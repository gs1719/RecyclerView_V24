package com.example.v24

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private var newsArrayList: ArrayList<News>, var context: Activity) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    //  part2 --start
    private lateinit var myListener: onItemClickListener

    interface onItemClickListener {

        fun onItemClicking(position: Int)

    }

    fun setItemClickListener(listener: onItemClickListener) {
        myListener = listener
    }
//part2 --end

// RecyclerView ----> Adapter -----> DataSource
//    adapter has three methods

// layout manager calls these three methods while it is positioning items within the Recycler View
// v24 RV part1 timestamp--->47.42

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
//  to create view instance
//  if layout manager fail to load views them it call this method

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_row, parent, false)
//        part2                         |
//                                      V
        return MyViewHolder(itemView,myListener)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
//        populate items with data
        val currentItem = newsArrayList[position]
        holder.hTitle.text = currentItem.newsHeading
        holder.hImage.setImageResource(currentItem.newsImage)

    }

    override fun getItemCount(): Int {
//       for example return information about the number of items
        // returns how many list items are present in your array

        return newsArrayList.size
    }

    /*
                                           Part 2
                                           ||||||||||||||
                                           vvvvvvvvvvvvvv        */
    class MyViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        // it holds the view so that views are not created everytime
        // so that memory can be saved

        val hTitle: TextView = itemView.findViewById(R.id.header_title)
        val hImage: ShapeableImageView = itemView.findViewById(R.id.header_image)

        /*
         val hTitle = itemView.findViewById<TextView>(R.id.header_title)
         val hImage = itemView.findViewById<ShapeableImageView>(R.id.header_image)
         */

        //part2 --start
        init {
            itemView.setOnClickListener {
                listener.onItemClicking(adapterPosition)
            }
        }
        //part2 --end
    }
}