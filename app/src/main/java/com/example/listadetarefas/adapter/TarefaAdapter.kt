package com.example.listadetarefas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadetarefas.databinding.ItemTarefaBinding
import com.example.listadetarefas.model.Tarefa


class TarefaAdapter(
    val onClickExcluir: (Int) -> Unit,
    val onClickEditar: (Tarefa) -> Unit

) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listaTarefa: List<Tarefa> = emptyList()
    fun adicionarLista(lista: List<Tarefa>) {
        this.listaTarefa = lista
        notifyDataSetChanged()
    }

    inner class TarefaViewHolder(itemBinding: ItemTarefaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private val binding: ItemTarefaBinding

        init {
            binding = itemBinding
        }

        fun bind(tarefa: Tarefa) {
            binding.textTarefa.text = tarefa.descricao
            binding.textData.text = tarefa.dataCadastro

            binding.btnExcluir.setOnClickListener {
                onClickExcluir(tarefa.idTarefa)
            }
            binding.btnEditar.setOnClickListener {
                onClickEditar(tarefa)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val itemTarefaBinding = ItemTarefaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TarefaViewHolder(itemTarefaBinding)
    }


    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listaTarefa[position]
        holder.bind(tarefa)
    }

    override fun getItemCount(): Int {
        return listaTarefa.size
    }

}
