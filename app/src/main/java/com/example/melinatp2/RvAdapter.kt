package com.example.melinatp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RvAdapter(val todos: List<Todo>) : RecyclerView.Adapter<RvAdapter.VH>() {

    // Transformer une view instanciée de rv_todo en ViewHolder
    inner class VH(view: View) : RecyclerView.ViewHolder(view)

    // Création du ViewHolder (VH)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_todo, parent, false)

        return VH(view)
    }

    // Relier les données de la collection aux VH
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvSerie).text = todos[position].anime.toString()
            findViewById<TextView>(R.id.tvPersonnage).text = todos[position].character
            findViewById<TextView>(R.id.tvCitation).text = todos[position].quote
        }
    }

    override fun getItemCount(): Int {
        //retourne le nom d'items dans todos
        return todos.size
    }
}