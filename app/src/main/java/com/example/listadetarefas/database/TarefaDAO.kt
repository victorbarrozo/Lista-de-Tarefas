package com.example.listadetarefas.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.listadetarefas.model.Tarefa

class TarefaDAO(context: Context):ITarefaDAO {

    private val escrever = DatabaseHelper(context).writableDatabase
    private val ler = DatabaseHelper(context).readableDatabase

    override fun salvar(tarefa: Tarefa): Boolean {
        try{
            val conteudos = ContentValues()
            conteudos.put("${DatabaseHelper.DESCRICAO}", tarefa.descricao)
            escrever.insert(
                DatabaseHelper.NOME_TABELA_TAREFAS,
                null,
                conteudos
            )
            Log.i("info.db,","Sucesso ao Salvar tarefa")
        }catch (e: Exception){
            Log.i("info.db,","Erro ao Salvar tarefa")
            return false
        }
             return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        TODO("Not yet implemented")
    }

    override fun remover(idTarefa: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun listar(): List<Tarefa> {
    val listaTarefas = mutableListOf<Tarefa>()

        val sql = "SELECT ${DatabaseHelper.ID_TAREFA}, ${DatabaseHelper.DESCRICAO}, " +
                  "strftime('%d/%m/%Y %H:%Mh',${DatabaseHelper.DATA_CADASTRO})" +
                  "${DatabaseHelper.DATA_CADASTRO}" +
                  " FROM ${DatabaseHelper.NOME_TABELA_TAREFAS}"

        val cursor = ler.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex(DatabaseHelper.ID_TAREFA)
        val indiceDescricao = cursor.getColumnIndex(DatabaseHelper.DESCRICAO)
        val indiceData = cursor.getColumnIndex(DatabaseHelper.DATA_CADASTRO)



        while (cursor.moveToNext()){
            val idTarefa = cursor.getInt(indiceId)
            val idDescricao = cursor.getString(indiceDescricao)
            val idData = cursor.getString(indiceData)


            listaTarefas.add(
                Tarefa(idTarefa, idDescricao, idData)
            )
        }

        return listaTarefas
    }

}