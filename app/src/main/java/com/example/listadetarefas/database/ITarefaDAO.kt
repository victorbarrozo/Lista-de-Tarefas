package com.example.listadetarefas.database

import com.example.listadetarefas.model.Tarefa

interface ITarefaDAO {

    fun salvar( tarefa: Tarefa ): Boolean
    fun atualizar( tarefa: Tarefa ): Boolean
    fun remover( idTarefa: Int ): Boolean
    fun listar(): List<Tarefa>
}