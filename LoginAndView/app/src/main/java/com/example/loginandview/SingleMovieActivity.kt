package com.example.loginandview

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonArray
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class SingleMovieActivity:AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var text: TextView
    private lateinit var text2: TextView
    private lateinit var text3: TextView
    private lateinit var photo1: ImageView
    private lateinit var photo2: ImageView
    private val job = SupervisorJob()
    var movie = ArrayList<Int>()
    private val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val uiScope: CoroutineScope = CoroutineScope(coroutineContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_movie_layout)
        title= findViewById(R.id.title)
        text = findViewById(R.id.text)
        text2 = findViewById(R.id.text2)
        text3 = findViewById(R.id.text3)
        photo1 = findViewById(R.id.imageView)
        photo2 = findViewById(R.id.imageView2)
        val position: Int = getIntent().getIntExtra("index",0)
        uiScope.launch {
            val result = ApiClient.apiClient.getPopularMovies(page = 1).await()
            for (item in result.movies){
//                val movieInfo = ApiClient.apiClient.getMovie(item.id).await()
                movie.add(item.id)
//                movie.add(DataItem(item.id,item.title.replace("\"",""),item.overview.subSequence(0,100).toString().replace("\"","")+"...",movieInfo.get("backdrop_path").toString().replace("\"","")))
            }
                val movieInfo = ApiClient.apiClient.getMovie(movie[position]).await()
                title.setText(movieInfo.get("title").toString().replace("\"",""))
                text.setText(movieInfo.get("overview").toString().replace("\"",""))
                text2.setText("Total budget: "+movieInfo.get("budget").toString().replace("\"",""))
                text3.setText("Popularity of the film "+movieInfo.get("popularity").toString().replace("\"",""))
                Picasso.get().load("http://image.tmdb.org/t/p/w500/"+movieInfo.get("backdrop_path").toString().replace("\"","")).into(photo1)
                Picasso.get().load("http://image.tmdb.org/t/p/w500/"+movieInfo.get("poster_path").toString().replace("\"","")).into(photo2)

        }
    }
}