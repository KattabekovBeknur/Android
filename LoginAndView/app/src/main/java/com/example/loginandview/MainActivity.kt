package com.example.loginandview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.await
import kotlin.coroutines.CoroutineContext
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {

    private val job = SupervisorJob()

    private val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val uiScope: CoroutineScope = CoroutineScope(coroutineContext)

    private lateinit var loginViewModel: MovieDBLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginViewModel = ViewModelProviders.of(this).get(MovieDBLogin::class.java)

        editTextUserName.setText("Beknur")
        editTextPassword.setText("android")
//        Picasso.get().load("http://image.tmdb.org/t/p/w500//n6bUvigpRFqSwmPp1m2YADdbRBc.jpg").into(imageView)
//        "http://image.tmdb.org/t/p/ "
        buttonLogin.setOnClickListener {
            (loginViewModel.login(
                username = editTextUserName.text.toString(),
                password = editTextPassword.text.toString()
            ))
            setData()

        }

    }
    private fun setData() {
        loginViewModel.liveData.observe(this, Observer { result ->
            when(result) {
                is MovieDBLogin.State.ShowLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is MovieDBLogin.State.HideLoading -> {
                    progressBar.visibility = View.GONE
                }
                is MovieDBLogin.State.ApiResult -> {
                    Log.d("activity_result", result.result)
                    if(result.result!="null") {
                        val intent = Intent(this, SecondActivity::class.java)
//                intent.putExtra("index",adapterPosition)
                        this.startActivity(intent)
//                        Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()
//                        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
                    }else{
                        textView.text = "Try to type again your password or login"
//                        Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show()
//                        this.finish()
                    }
                }
                is MovieDBLogin.State.Error -> {
                    Toast.makeText(this, "Error in connection", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
