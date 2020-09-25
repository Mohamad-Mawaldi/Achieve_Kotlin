package com.example.achievekotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.achievekotlin.Adapter.RVTasksAdapter
import com.example.achievekotlin.Models.Task
import com.example.achievekotlin.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list = ArrayList<Task>()
        list.add(Task("title", "Body", "2020,09", false  ) )
        list.add(Task("title1", "Body", "2020,09", false  ) )
        list.add(Task("title2", "Body", "2020,09", false  ) )
        list.add(Task("title3", "Body", "2020,09", false  ) )

        tasksRecyclerView.adapter = RVTasksAdapter(list)
        tasksRecyclerView.layoutManager = LinearLayoutManager(activity)
        tasksRecyclerView.setHasFixedSize(true)

    }



}