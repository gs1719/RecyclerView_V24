package com.example.v24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.v24.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide()
        setContentView(view)


        val newsHeading = intent.getStringExtra("heading")
        val newsImageId= intent.getIntExtra("imageId",R.drawable.img1)
        val newsContent = intent.getStringExtra("newsContent")

        binding.newsImage.setImageResource(newsImageId)
        binding.newsHeading.text = newsHeading
        binding.tvNewsContent.text = newsContent
    }
}