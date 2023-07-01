package com.example.newsapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newsapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        var binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        binding.recylerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsListAdapter(this)
        binding.recylerView.adapter = mAdapter

    }

    private fun fetchData(){
        val url = "https://saurav.tech/NewsAPI/everything/cnn.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()){
                    val newJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newJsonObject.getString("title"),
                        newJsonObject.getString("author"),
                        newJsonObject.getString("url"),
                        newJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                mAdapter.updatedNews(newsArray)
            },
            {

            }
        )
        MySingletonClass.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onCLickBehavior(item: News){
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))

    }

}