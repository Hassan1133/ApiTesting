package com.example.testapi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapi.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        GlobalScope.launch(Dispatchers.IO) {
            try {

                val users = RetrofitInstance.api.getUsers()
                runOnUiThread {
                    setDataToRecycler(users)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun setDataToRecycler(list: List<UserInfoItem>) {
        val adapter = Adapter(this@MainActivity, list)
        binding.recyclerView.adapter = adapter
    }
}