package com.example.achievekotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.achievekotlin.Adapter.RVTasksAdapter
import com.example.achievekotlin.Models.Task
import com.example.achievekotlin.R
import com.example.achievekotlin.iFragmentListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private lateinit var fragmentListener: iFragmentListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        fragmentListener = activity as iFragmentListener

        view.logout_button.setOnClickListener {
            fragmentListener.logOut()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list = ArrayList<Task>()
        var adapter = RVTasksAdapter(list, activity )
        list.add(Task("title", "Body", "2020,09", false  ) )
        list.add(Task("title1", "Body", "2020,09", false  ) )
        list.add(Task("title2", "Body", "2020,09", false  ) )
        list.add(Task("title4", "Body", "2020,09", false  ) )
        list.add(Task("title5", "Body", "2020,09", false  ) )
        list.add(Task("title6", "Body", "2020,09", true  ) )
        list.add(Task("title7", "Body", "2020,09", true  ) )
        list.add(Task("title8", "Body", "2020,09", true  ) )

        tasksRecyclerView.adapter = adapter
        tasksRecyclerView.layoutManager = LinearLayoutManager(activity)
        tasksRecyclerView.setHasFixedSize(true)

    }



}