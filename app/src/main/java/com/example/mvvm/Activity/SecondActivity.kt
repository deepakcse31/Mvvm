package com.example.mvvm.Activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.Model.Sum
import com.example.mvvm.Networking.RetrofitService
import com.example.mvvm.R
import com.example.mvvm.ViewModel.SecondViewModel
import com.example.mvvm.ViewModelFactory.MainViewModelFactory
import com.example.mvvm.ViewModelFactory.SecondViewModelFactory
import com.example.mvvm.databinding.SecondActivityBinding

class SecondActivity : AppCompatActivity() {
        private lateinit var binding: SecondActivityBinding
        val viewModel: SecondViewModel by viewModels()

    private val retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Observerdata()
        binding.submitbutton.setOnClickListener {
            adddata()
        }

    }
    fun Observerdata()
    {
        viewModel.result.observe(this, {

            Toast.makeText(this, "result : "+it, Toast.LENGTH_SHORT).show()
            //binding.txtresult.setText(it)
        })
    }
    fun adddata()
    {
        var sum = Sum(binding.firsttext.text.toString().toInt(),binding.secondtext.text.toString().toInt())
        viewModel.add(sum)
    }
}