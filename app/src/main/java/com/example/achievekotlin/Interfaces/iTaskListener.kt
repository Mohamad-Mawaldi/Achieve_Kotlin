package com.example.achievekotlin.Interfaces

import com.example.achievekotlin.Models.Task

interface iTaskListener {


    // Delete a Task
    fun deleteTaskListener( task: Task)

    // Update a Task
    fun updateTaskListener(task: Task)

    // Update Task Status
    fun updateTaskStatusListener(task: Task)


}