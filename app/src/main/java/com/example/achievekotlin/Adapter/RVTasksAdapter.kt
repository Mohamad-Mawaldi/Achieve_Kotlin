package com.example.achievekotlin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.achievekotlin.Models.Task
import com.example.achievekotlin.R
import kotlinx.android.synthetic.main.task_layout.view.*


class RVTasksAdapter( private val tasksList: List<Task>) : RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val vh = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var currentTask = tasksList[position]

        holder.header.text = currentTask.header
        holder.body.text = currentTask.body
        holder.dateTextView.text = currentTask.date
        //holder.isDoneButton.text = currentTask.isDone

    }

}

class MyViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val header:TextView = view.header_textView;
    val body:TextView = view.bodyText_view;
    val dateTextView:TextView = view.date_textView
    //val isDoneButton:TextView = view.done_button


}