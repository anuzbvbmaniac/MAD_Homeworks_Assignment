package com.example.android.todoapp.taskdetail;

import static org.hamcrest.core.IsNot.not;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.app.Activity;
import android.content.Intent;

import com.example.android.todoapp.R;
import com.example.android.todoapp.TestUtils;
import com.example.android.todoapp.data.FakeTasksRemoteDataSource;
import com.example.android.todoapp.data.Task;
import com.example.android.todoapp.data.source.TasksRepository;
import com.example.android.todoapp.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TaskDetailScreenTest {

    private static String TASK_TITLE = "ATSL";

    private static String TASK_DESCRIPTION = "Rocks";

    private static Task ACTIVE_TASK = new Task(TASK_TITLE, TASK_DESCRIPTION, false);

    private static Task COMPLETED_TASK = new Task(TASK_TITLE, TASK_DESCRIPTION, true);

    @Rule
    public ActivityTestRule<TaskDetailActivity> mTaskDetailActivityTestRule =
            new ActivityTestRule<>(TaskDetailActivity.class, true /* Initial touch mode  */,
                    false /* Lazily launch activity */);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }


    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

    private void loadActiveTask() {
        startActivityWithWithStubbedTask(ACTIVE_TASK);
    }

    private void loadCompletedTask() {
        startActivityWithWithStubbedTask(COMPLETED_TASK);
    }

    private void startActivityWithWithStubbedTask(Task task) {
        // Add a task stub to the fake service api layer.
        TasksRepository.destroyInstance();
        FakeTasksRemoteDataSource.getInstance().addTasks(task);

        // Lazily start the Activity from the ActivityTestRule this time to inject the start Intent
        Intent startIntent = new Intent();
        startIntent.putExtra(TaskDetailActivity.EXTRA_TASK_ID, task.getId());
        mTaskDetailActivityTestRule.launchActivity(startIntent);
    }

    @Test
    public void activeTaskDetails_DisplayedInUi() throws Exception {
        loadActiveTask();

        // Check that the task title and description are displayed
        onView(withId(R.id.task_detail_title)).check(matches(withText(TASK_TITLE)));
        onView(withId(R.id.task_detail_description)).check(matches(withText(TASK_DESCRIPTION)));
        onView(withId(R.id.task_detail_complete)).check(matches(not(isChecked())));
    }

    @Test
    public void completedTaskDetails_DisplayedInUi() throws Exception {
        loadCompletedTask();

        // Check that the task title and description are displayed
        onView(withId(R.id.task_detail_title)).check(matches(withText(TASK_TITLE)));
        onView(withId(R.id.task_detail_description)).check(matches(withText(TASK_DESCRIPTION)));
        onView(withId(R.id.task_detail_complete)).check(matches(isChecked()));
    }

    @Test
    public void orientationChange_menuAndTaskPersist() {
        loadActiveTask();

        // Check delete menu item is displayed and is unique
        onView(withId(R.id.menu_delete)).check(matches(isDisplayed()));

        TestUtils.rotateOrientation(mTaskDetailActivityTestRule.getActivity());

        // Check that the task is shown
        onView(withId(R.id.task_detail_title)).check(matches(withText(TASK_TITLE)));
        onView(withId(R.id.task_detail_description)).check(matches(withText(TASK_DESCRIPTION)));

        // Check delete menu item is displayed and is unique
        onView(withId(R.id.menu_delete)).check(matches(isDisplayed()));
    }

}
