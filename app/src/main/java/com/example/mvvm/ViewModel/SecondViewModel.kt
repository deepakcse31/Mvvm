package com.example.mvvm.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.Model.Movie
import com.example.mvvm.Model.Sum
import com.example.mvvm.Networking.RetrofitService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class SecondViewModel : ViewModel() {
     var _result = MutableLiveData<Int>().apply { value = 0 }
    val movieList = MutableLiveData<List<Movie>>()
    private val retrofitService = RetrofitService.getInstance()

    val result:LiveData<Int> get() {
        return _result
    }

    fun add(sum: Sum){
        val response=retrofitService.getAllMovies()
        response.enqueue(object : retrofit2.Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                Log.e("MovieList--->","MovieList"+response.body())
                movieList.postValue(response.body())
                movieList.value=response.body()
                Log.e("movielistdata===>","movielistdata===>"+movieList.value)
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
               // errorMessage.postValue(t.message)
            }
        })
        _result.value=sum.x1+sum.X2;
    }
}