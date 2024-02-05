package com.example.listadetarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listadetarefas.databinding.ActivityMainBinding

class   MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this,AdcTarefaActivity::class.java)
        binding.fabAdc.setOnClickListener {
            startActivity(intent)
        }
    }
}


