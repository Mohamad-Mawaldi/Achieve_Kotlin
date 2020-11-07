package com.example.achievekotlin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Dimension

import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity

import androidx.recyclerview.widget.RecyclerView
import com.example.achievekotlin.Models.Task
import com.example.achievekotlin.R

import kotlinx.android.synthetic.main.task_layout.view.*


class RVTasksAdapter(private val tasksList: List<Task>, private val activity: FragmentActivity?) : RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val vh = MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
        )
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



        val isExpanded = currentTask.isExpanded

        holder.expandableLayout.animate().apply {
            duration = 1000
            holder.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
        }.start()


        holder.linearLayout.setOnClickListener {
            currentTask.isExpanded =!currentTask.isExpanded
            this.notifyItemChanged(position)
        }


        when (currentTask.isDone) {
            true -> holder.checkBox.setImageResource(R.drawable.check_mark)
            false -> holder.checkBox.setImageResource(R.drawable.circle)
        }


        holder.checkBox.setOnClickListener {

            holder.checkBox.animate().apply {
                duration = 1000
                rotationYBy(360f)
            }.withEndAction() {
                when (currentTask.isDone) {
                    true -> currentTask.isDone = false
                    false -> currentTask.isDone = true
                }
                this.notifyItemChanged(position)
            }.start()
        }

       }
    }


class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val linearLayout : LinearLayout = view.linearLayout
    val expandableLayout: RelativeLayout = view.expandableLayout
    val checkBox: ImageView = view.done_check
    val header: TextView = view.header_textView
    val body: TextView = view.bodyText_view
    val dateTextView: TextView = view.date_textView

}