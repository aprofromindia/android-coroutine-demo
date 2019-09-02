package com.github.aprofromindia.myapplication.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import com.github.aprofromindia.myapplication.R
import com.github.aprofromindia.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.vm = viewModel

        binding.btn.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                getString(R.string.toast_msg),
                Toast.LENGTH_SHORT
            ).show()
            Timber.i("${binding.btn} clicked")
        }
    }

    override fun onResume() {
        super.onResume()
        repeat(1000) {
            viewModel.viewModelScope.launch {
                delay(10000)
                println("coroutines $it.")
            }
        }
    }
}
