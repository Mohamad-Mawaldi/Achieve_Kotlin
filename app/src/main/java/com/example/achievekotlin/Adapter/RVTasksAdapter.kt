package com.example.achievekotlin.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.achievekotlin.Interfaces.iTaskListener
import com.example.achievekotlin.Models.Task
import com.example.achievekotlin.R
import kotlinx.android.synthetic.main.task_layout.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class RVTasksAdapter(
    private val tasksList: ArrayList<Task>,
    private val activity: FragmentActivity?,
    private val taskClickListener: iTaskListener
) : RecyclerView.Adapter<MyViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {



        Log.d("10000 from adapter",tasksList.toString())

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

        holder.header.text = currentTask.title
        holder.title.text = currentTask.title
        holder.body.text = currentTask.body
        holder.dateTextView.text = FormatDate(currentTask.date)[0] + "\n" + FormatDate(currentTask.date)[1]


        val isExpanded = currentTask.isExpanded

        holder.expandableLayout.animate().apply {
            duration = 1500
            holder.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
        }.start()


        holder.linearLayout.setOnClickListener {
            currentTask.isExpanded = !currentTask.isExpanded
            this.notifyItemChanged(position)

        }


        when (currentTask.done) {
            true -> holder.checkBox.setImageResource(R.drawable.check_mark)
            false -> holder.checkBox.setImageResource(R.drawable.circle)
        }

        holder.checkBox.setOnClickListener {

            taskClickListener.updateTaskStatusListener(currentTask)

            holder.checkBox.animate().apply {
                duration = 1000
                scaleY(1f)
                scaleX(1f)
            }.withStartAction {
                when (currentTask.done) {
                    true -> currentTask.done = false
                    false -> currentTask.done = true
                }
                this.notifyItemChanged(position)

            }.start()
        }

        holder.deleteButton.setOnClickListener {
            taskClickListener.deleteTaskListener(currentTask)
        }

        holder.editButton.setOnClickListener {

            taskClickListener.updateTaskListener(currentTask)
        }

    }


    fun FormatDate(taskDate: String): MutableList<String> {

        val current = LocalDateTime.now()
        val formattedDate = current.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val formattedTime = current.format(DateTimeFormatter.ofPattern("HH:mm"))

        var dateParts = taskDate.split(" ").toMutableList()

        if (dateParts[0] == formattedDate) {

            dateParts[0] = "Today"

            if (dateParts[1] == formattedTime) dateParts[1] = "now"
        }

        return dateParts

    }

}


class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val linearLayout: LinearLayout = view.linearLayout
    val expandableLayout: RelativeLayout = view.expandableLayout
    val checkBox: ImageView = view.done_check
    val header: TextView = view.header_textView
    val body: TextView = view.bodyText_view
    val title: TextView = view.titleText_view
    val dateTextView: TextView = view.date_textView
    val deleteButton: Button = view.deleteButton
    val editButton: Button = view.editButton

}