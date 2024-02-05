package com.example.listadetarefas.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context):SQLiteOpenHelper(
    context, BANCO_DADOS, null, 1)
{
    companion object{
        const val BANCO_DADOS = "ListaTarefas.db"
        const val VERSAO = 1
        const val NOME_TABELA_TAREFAS = "Tarefas"
        const val ID_TAREFA = "id_tarefa"
        const val DESCRICAO = "descricao"
        const val DATA_CADASTRO = "data_cadrasto"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE table if NOT EXISTS $NOME_TABELA_TAREFAS(" +
                "  $ID_TAREFA integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "  $DESCRICAO varchar (70)," +
                "  $DATA_CADASTRO DATETIME NOT null DEFAULT CURRENT_TIMESTAMP" +
                "  );"
        try{
            db?.execSQL(sql)
            Log.i("info.db,","Sucesso ao criar tabela")
    }catch (e: Exception){
            Log.i("info.db,","Erro ao criar tabela")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
