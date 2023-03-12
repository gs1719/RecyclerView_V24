package com.example.v24

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.v24.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myRecyclerView: RecyclerView
    lateinit var newsArrayList: ArrayList<News>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        myRecyclerView = binding.recyclerView
        val newsImageArray = arrayOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6
        )
        val newsHeadingArray = arrayOf(
            "U.K. Foreign Secretary James Cleverly raises issue of BBC tax searches with EAM Jaishankar",
            "Cooking gas prices hiked by ₹50 for domestic, ₹350.50 for commercial cylinders",
            "Joe Biden appoints two prominent Indian-American corporate leaders to his Export Council",
            "Sergey Lavrov will raise suspected bombing of Nord Stream II at G20: Russian Foreign Ministry",
            "Belarusian leader Lukashenko visits China amid Ukraine tensions",
            "China rips new U.S. House committee on countering Beijing",
            "Largest gathering of Foreign Ministers hosted by any G20 presidency: Foreign Secretary Vinay Kwatra"

        )

        val newsContent = arrayOf(
//            using getstring so that R.string.  ---> Int its basically id so we have to extract or
//            get the value stored in that id
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content)
        )
        //to set the behaviour of item inside recyclerview  vertically scrolling,horizontally scrolling,uniform grid
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        newsArrayList = arrayListOf<News>()

        for (index in newsImageArray.indices) {
            val news = News(newsHeadingArray[index], newsImageArray[index],newsContent[index])
            newsArrayList.add(news)
        }

        val myAdapter = MyAdapter(newsArrayList,this)
        myRecyclerView.adapter =  myAdapter
        /*
        *                                       Interface
        *                                       ||||||||||||||
        *                                       VVVVVVVVVVVVVV
        * */
        myAdapter.setItemClickListener(object :MyAdapter.onItemClickListener{
            override fun onItemClicking(position: Int) {
//          on clicking each item , what action do you want to perform
                intent = Intent(this@MainActivity,NewsDetailActivity::class.java).apply {
                    putExtra("heading",newsArrayList[position].newsHeading)
                    putExtra("imageId",newsArrayList[position].newsImage)
                    putExtra("newsContent",newsArrayList[position].newsContent)
//                    startActivity(intent) should be always outside
//                    written cause one delay everytime of use
                }
                startActivity(intent)
            }
        })
    }
}