package com.example.todo_app;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TodoActivity extends AppCompatActivity {

    private String[] mTodos;
    private int mTodoIndex = 0;
    private Button buttonNext,buttonPrev, buttonDetail;

    public static final String TODO_INDEX = "com.example.todoIndex";
    public static final String TAG = TodoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        if (savedInstanceState !=null){
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
            Log.d(TAG, "onCreate(): activity recreated");
        }else{
            Log.d(TAG, "onCreate(): activity a new");
        }
        Resources res = getResources();
        mTodos = res.getStringArray(R.array.todos);

        final TextView textViewTodo;
        textViewTodo = (TextView)findViewById(R.id.textViewTodo);
        textViewTodo.setText(mTodos[mTodoIndex]);

        buttonNext = (Button)findViewById(R.id.btn_next);
        buttonPrev = (Button)findViewById(R.id.btn_prev);
        buttonDetail = (Button)findViewById(R.id.button_detail);

        buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodoActivity.this, TodoActivityDetail.class);
                startActivity(intent);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTodoIndex = (mTodoIndex + 1) % mTodos.length;
                textViewTodo.setText(mTodos[mTodoIndex]);
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTodoIndex = (mTodoIndex - 1);
                textViewTodo.setText(mTodos[mTodoIndex]);
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart(): activity is about to become visible");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TODO_INDEX, mTodoIndex);
        Log.d(TAG, "onSaveInstanceState(): save the state of the object in this method");
    }
}
