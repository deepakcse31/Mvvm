package com.example.mvvm.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.Adapter.NoteRecyclerAdapter
import com.example.mvvm.Model.Blog
import com.example.mvvm.R
import com.example.mvvm.ViewModel.MainViewModel
import com.example.mvvm.ViewModelFactory.MainViewModelFactory
import java.util.Optional.of

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var button: Button
    // var viewManger=LinearLayout(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // installSplashScreen()
       setContentView(R.layout.activity_main)
        /*installSplashScreen().apply {

        }

         */
        recyclerView=findViewById(R.id.recycler)
        val application = requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)

        inttiallizeAdapter()
        button=findViewById(R.id.button)
        button.setOnClickListener {
            addData()
        }
    }

    private fun inttiallizeAdapter()
    {
        recyclerView.layoutManager=LinearLayoutManager(this)
        observeData()

    }
    fun observeData(){
        viewModel.lst.observe(this, Observer{
            //Log.i("data",it.toString())
            recyclerView.adapter= NoteRecyclerAdapter(viewModel, it, this)

        })
    }
    fun addData(){
        var txtplce = findViewById<EditText>(R.id.titletxt)
        var title=txtplce.text.toString()
        if(title.isNullOrBlank()){
            Toast.makeText(this,"Enter value!",Toast.LENGTH_LONG).show()
        }else{
            var blog= Blog(title)
            viewModel.add(blog)
            txtplce.text.clear()
            recyclerView.adapter?.notifyDataSetChanged()
        }

    }


}