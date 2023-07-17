package com.example.melinatp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melinatp2.databinding.ActivityMainBinding
import com.example.melinatp2.databinding.ActivitySecondBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Faire appel au endpoint pour appeler le web API
        val endpoint = "https://animechan.vercel.app/api/quotes/anime?title=final%20fantasy%20vii"

        //TypeToken
        val typeToken = object : TypeToken<List<Todo>>() {}.type

        lifecycleScope.launch(Dispatchers.IO) {
            val strTodos = URL(endpoint).readText()

            //println(strTodos)

            val todos = Gson().fromJson<List<Todo>>(strTodos, typeToken)

            //println(todos)

            // Pousser le résultat sur le UI
            this@SecondActivity.runOnUiThread {
                binding.rvTodos.apply {
                    adapter = RvAdapter(todos)
                    layoutManager = LinearLayoutManager(this@SecondActivity)
                }
            }
        }
    }
}