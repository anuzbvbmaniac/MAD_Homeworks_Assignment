package com.example.android.todoapp.tasks;


import android.view.View;

import com.example.android.todoapp.data.Task;

/**
 * Listener used with data binding to process user actions.
 */
public interface TaskItemUserActionsListener {
    void onCompleteChanged(Task task, View v);

    void onTaskClicked(Task task);
}
