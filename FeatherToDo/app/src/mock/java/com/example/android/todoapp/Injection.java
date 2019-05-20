
package com.example.android.todoapp;

import android.content.Context;

import com.example.android.todoapp.data.FakeTasksRemoteDataSource;
import com.example.android.todoapp.data.source.TasksDataSource;
import com.example.android.todoapp.data.source.TasksRepository;
import com.example.android.todoapp.data.source.local.TasksLocalDataSource;
import com.example.android.todoapp.data.source.local.ToDoDatabase;
import com.example.android.todoapp.util.AppExecutors;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        ToDoDatabase database = ToDoDatabase.getInstance(context);
        return TasksRepository.getInstance(FakeTasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance(new AppExecutors(),
                        database.taskDao()));
    }
}
