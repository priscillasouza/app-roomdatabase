package com.example.roomapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.fragments.ListFragmentDirections
import com.example.roomapp.model.Student
import kotlinx.android.synthetic.main.layout_item_recycler_view.view.*

class ListAdpter: RecyclerView.Adapter<ListAdpter.MyViewHolder>() {

    private var studentList = emptyList<Student>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_recycler_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = studentList[position]
        holder.itemView.textViewStudentId.text = currentItem.id.toString()
        holder.itemView.textViewStudentName.text = currentItem.name
        holder.itemView.textViewStudentLastName.text = currentItem.lastName
        holder.itemView.textViewStudentAge.text = currentItem.age.toString()
        holder.itemView.textViewStudentEmail.text = currentItem.email

        //quando clicar no item será direcionado para a tela de atualizar os dados
        holder.itemView.layoutRecyclerView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return studentList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(student: List<Student>) {
        this.studentList = student
        notifyDataSetChanged()
    }
}