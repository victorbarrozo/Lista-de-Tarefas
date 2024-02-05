package com.example.listadetarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.listadetarefas.database.TarefaDAO
import com.example.listadetarefas.databinding.ActivityAdcTarefaBinding
import com.example.listadetarefas.databinding.ActivityMainBinding
import com.example.listadetarefas.model.Tarefa

class AdcTarefaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAdcTarefaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {
            if(binding.editTarefas.text.isNotEmpty()){
                val descricao = binding.editTarefas.text.toString()
                val tarefa = Tarefa(
                    -1, descricao, "defaut"
                    )
                val tarefaDAO = TarefaDAO(this)
                if(tarefaDAO.salvar(tarefa)){
                    Toast.makeText(this,"Tarefa cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }

            }else{
                Toast.makeText(this, "Preencha uma tarefa!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun salvar() {
        TODO("Not yet implemented")
    }
}