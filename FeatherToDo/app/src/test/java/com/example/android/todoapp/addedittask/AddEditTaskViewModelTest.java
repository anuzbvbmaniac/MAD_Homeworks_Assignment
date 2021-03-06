package com.example.android.todoapp.addedittask;


import com.example.android.todoapp.addedittask.AddEditTaskViewModel;
import com.example.android.todoapp.data.Task;
import com.example.android.todoapp.data.source.TasksDataSource;
import com.example.android.todoapp.data.source.TasksRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;


public class AddEditTaskViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private TasksRepository mTasksRepository;

    @Captor
    private ArgumentCaptor<TasksDataSource.GetTaskCallback> mGetTaskCallbackCaptor;

    private AddEditTaskViewModel mAddEditTaskViewModel;

    @Before
    public void setupAddEditTaskViewModel() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mAddEditTaskViewModel = new AddEditTaskViewModel(mTasksRepository);
    }

    @Test
    public void saveNewTaskToRepository_showsSuccessMessageUi() {
        // When the ViewModel is asked to save a task
        mAddEditTaskViewModel.description.setValue("Some Task Description");
        mAddEditTaskViewModel.title.setValue("New Task Title");
        mAddEditTaskViewModel.saveTask();

        // Then a task is saved in the repository and the view updated
        verify(mTasksRepository).saveTask(any(Task.class)); // saved to the model
    }

    @Test
    public void populateTask_callsRepoAndUpdatesView() {
        Task testTask = new Task("TITLE", "DESCRIPTION", "1");

        // Get a reference to the class under test
        mAddEditTaskViewModel = new AddEditTaskViewModel(mTasksRepository);


        // When the ViewModel is asked to populate an existing task
        mAddEditTaskViewModel.start(testTask.getId());

        // Then the task repository is queried and the view updated
        verify(mTasksRepository).getTask(eq(testTask.getId()), mGetTaskCallbackCaptor.capture());

        // Simulate callback
        mGetTaskCallbackCaptor.getValue().onTaskLoaded(testTask);

        // Verify the fields were updated
        assertThat(mAddEditTaskViewModel.title.getValue(), is(testTask.getTitle()));
        assertThat(mAddEditTaskViewModel.description.getValue(), is(testTask.getDescription()));
    }
}
