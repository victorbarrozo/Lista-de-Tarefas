package com.example.listadetarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listadetarefas.database.TarefaDAO
import com.example.listadetarefas.databinding.ActivityMainBinding
import com.example.listadetarefas.model.Tarefa

class   MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var listaTarefas = emptyList<Tarefa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this,AdcTarefaActivity::class.java)
        binding.fabAdc.setOnClickListener {
            startActivity(intent)
        }
        listaTarefas = TarefaDAO(applicationContext).listar()
        listaTarefas.forEach {tarefa ->
            Log.i("info.db,","${tarefa.descricao}\n")
        }
    }
}


