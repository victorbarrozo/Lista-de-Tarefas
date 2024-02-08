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
            conteudos.put("${DatabaseHelper.COLUNA_DESCRICAO}", tarefa.descricao)
            escrever.insert(
                DatabaseHelper.NOME_TABELA_TAREFAS,
                null,
                conteudos
            )
            Log.i("info.db","Sucesso ao Salvar tarefa")
        }catch (e: Exception){
            Log.i("info.db","Erro ao Salvar tarefa")
            return false
        }
             return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        val args = arrayOf (tarefa.idTarefa.toString() )
        val conteudos = ContentValues()
        conteudos.put("${DatabaseHelper.COLUNA_DESCRICAO}", tarefa.descricao)

        try{
            escrever.update(
                DatabaseHelper.NOME_TABELA_TAREFAS,
                conteudos,
                "${DatabaseHelper.COLUNA_ID_TAREFA} = ?",
                args
            )
            Log.i("info_db","Sucesso ao atrualizar tarefa")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db","Erro ao atualizar tarefa")
            return false
        }
        return true
    }

    override fun remover(idTarefa: Int): Boolean {

        val args = arrayOf (idTarefa.toString() )
        try{
            escrever.delete(
                DatabaseHelper.NOME_TABELA_TAREFAS,
                "${DatabaseHelper.COLUNA_ID_TAREFA} = ?",
                args
            )
            Log.i("info_db","Sucesso ao remover tarefa")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db","Erro ao remover tarefa")
            return false
        }
        return true
    }

    override fun listar(): List<Tarefa> {

    val listaTarefas = mutableListOf<Tarefa>()

        val sql = "SELECT ${DatabaseHelper.COLUNA_ID_TAREFA}, " +
                  "${DatabaseHelper.COLUNA_DESCRICAO}, " +
                  "strftime('%d/%m/%Y %H:%Mh',${DatabaseHelper.COLUNA_DATA_CADASTRO})" +
                  "${DatabaseHelper.COLUNA_DATA_CADASTRO} " +
                  "FROM ${DatabaseHelper.NOME_TABELA_TAREFAS}"

        val cursor = ler.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex(DatabaseHelper.COLUNA_ID_TAREFA)
        val indiceDescricao = cursor.getColumnIndex(DatabaseHelper.COLUNA_DESCRICAO)
        val indiceData = cursor.getColumnIndex(DatabaseHelper.COLUNA_DATA_CADASTRO)

        while (cursor.moveToNext() ){
            val idTarefa = cursor.getInt( indiceId )
            val descricao = cursor.getString( indiceDescricao )
            val dataCadastro = cursor.getString( indiceData )


            listaTarefas.add(
                Tarefa(idTarefa, descricao, dataCadastro)
            )
        }

        return listaTarefas
    }

}