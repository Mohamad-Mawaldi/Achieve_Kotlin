package com.example.achievekotlin.Fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.achievekotlin.Adapter.RVTasksAdapter
import com.example.achievekotlin.Models.Task
import com.example.achievekotlin.R
import com.example.achievekotlin.Interfaces.iFragmentListener
import com.example.achievekotlin.Interfaces.iTaskListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.add_update_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class HomeFragment : Fragment(), iTaskListener {


    private var auth: FirebaseAuth = Firebase.auth
    private var database = Firebase.database
    private lateinit var myRef: DatabaseReference
    private lateinit var fragmentListener: iFragmentListener
    private lateinit var list: ArrayList<Task>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        list = ArrayList()
        fragmentListener = activity as iFragmentListener
        myRef = database.getReference("users").child(auth.currentUser!!.uid).child("tasks")


        view.logout_button.setOnClickListener {
            auth.signOut()
            fragmentListener.logOut()
        }


        view.add_floating_btn.setOnClickListener {


            val currentID = (0..1000000).random().toString()

            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val formattedDate = current.format(formatter)



            loadAddOrUpdateDialog("Add a Task", Task("","",formattedDate,currentID))
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        loadDataDB(myRef, list, tasksRecyclerView)


    }


    private fun setUpRecycleView(list: ArrayList<Task>) {


        var adapter = RVTasksAdapter(list, activity, this)
        tasksRecyclerView.adapter = adapter
        tasksRecyclerView.layoutManager = LinearLayoutManager(activity)


    }

    private fun loadDataDB(
        DBRef: DatabaseReference,
        list: ArrayList<Task>,
        recyclerView: RecyclerView
    ) {

/*        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val formattedDate = current.format(formatter)*/


        DBRef.orderByChild("date").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                list.clear()

                val tasks = dataSnapshot.children.iterator()

                while (tasks.hasNext()) {

                    val item = tasks.next()
                    val map = item.getValue() as HashMap<String, Any?>

                    list.add(
                        Task(
                            map.get("title") as String,
                            map.get("body") as String,
                            map.get("date") as String,
                            map.get("id") as String,
                            map.get("done") as Boolean

                        )
                    )
                }
                val alist = list.sortedByDescending { task -> task.date }

                list.clear()

                list.addAll(alist)

                setUpRecycleView(list )

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }


    private fun loadAddOrUpdateDialog(
        dialogTitle: String, task: Task
    ) {

        val mDialogView = LayoutInflater.from(context!!).inflate(R.layout.add_update_dialog, null)

        val mBuilder = AlertDialog.Builder(context!!)
            .setView(mDialogView)

        mDialogView.dialog_title_text_view.text = dialogTitle


         if (!task.body.isNullOrEmpty())  mDialogView.dialog_body_edit_view.setText(task.body)
         if (!task.title.isNullOrEmpty())  mDialogView.dialog_title_edit_view.setText(task.title)

        val mAlertDialog = mBuilder.show()
        mDialogView.dialog_submit_btn.setOnClickListener {

            mAlertDialog.dismiss()


            val title = mDialogView.dialog_title_edit_view.text
            val description = mDialogView.dialog_body_edit_view.text

            when {
                title.isNullOrEmpty() -> Toast.makeText(
                    activity,
                    "Please fill in the Title",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {task.title = title.toString();
                        task.body = description.toString();
                    addOrUpdateTask(task)}
            }
        }
        mDialogView.dialog_cancel_btn.setOnClickListener {
            mAlertDialog.dismiss()

        }

    }


    private fun addOrUpdateTask(task: Task) {

        myRef.child(task.id).setValue(task).addOnCompleteListener { task ->
            if (task.isSuccessful) Toast.makeText(activity, "Task Added", Toast.LENGTH_SHORT).show()
            else Toast.makeText(activity, task.exception?.message.toString(), Toast.LENGTH_SHORT)
                .show()
        }


    }

    override fun deleteTaskListener(task: Task) {
        myRef.child(task.id).removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) Toast.makeText(
                activity,
                "Deleted successfully",
                Toast.LENGTH_SHORT
            ).show()
            else Toast.makeText(activity, task.exception?.message.toString(), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun updateTaskListener(task: Task) {
        loadAddOrUpdateDialog("Edit a Task", task)
    }

    override fun updateTaskStatusListener(task: Task) {

        task.done = !task.done

        myRef.child(task.id).setValue(task)

            .addOnCompleteListener { task ->
                if (!task.isSuccessful) Toast.makeText(
                    activity,
                    task.exception?.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

}