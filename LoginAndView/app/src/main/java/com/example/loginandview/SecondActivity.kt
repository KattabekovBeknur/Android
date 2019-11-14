package com.example.loginandview

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.await
import kotlin.coroutines.CoroutineContext
//import androidx.core.app.ComponentActivity
//import androidx.core.app.ComponentActivity.ExtraData
//import androidx.core.content.ContextCompat.getSystemService
//import android.icu.lang.UCharacter.GraphemeClusterBreak.T
//import android.content.Intent
//import androidx.core.app.ComponentActivity
//import androidx.core.app.ComponentActivity.ExtraData
//import androidx.core.content.ContextCompat.getSystemService
//import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class SecondActivity:AppCompatActivity() {
    private val job = SupervisorJob()

    private val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val uiScope: CoroutineScope = CoroutineScope(coroutineContext)

    var movie = ArrayList<DataItem>()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val recyclerView = findViewById<RecyclerView>(R.id.my_list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false) as RecyclerView.LayoutManager?
        uiScope.launch {
            val result = ApiClient.apiClient.getPopularMovies(page = 1).await()
            for (item in result.movies){
                val movieInfo = ApiClient.apiClient.getMovie(item.id).await()
                movie.add(DataItem(item.id,item.title.replace("\"",""),item.overview.subSequence(0,100).toString().replace("\"","")+"...",movieInfo.get("backdrop_path").toString().replace("\"","")))
                Log.d("result_data", item.overview.subSequence(0,100).toString())
            }
            val movieInfo = ApiClient.apiClient.getMovie(movieId = result.movies.get(0).id).await()
            val adapter = MyAdapter(movie)
            recyclerView.adapter = adapter
            Log.d("result_data_detail", movieInfo.toString())
        }

    }

    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout?")
        builder.setPositiveButton("Yes",
            DialogInterface.OnClickListener { dialogInterface, i ->val intent = Intent(this,MainActivity::class.java)
                this.startActivity(intent)
                this.finish() })
        builder.setNegativeButton("No",
            DialogInterface.OnClickListener { dialogInterface, i -> })

        val ad = builder.create()
        ad.show()
    }
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}