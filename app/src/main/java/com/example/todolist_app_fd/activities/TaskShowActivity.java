package com.example.todolist_app_fd.activities;

import static com.example.todolist_app_fd.Constant.CATEGORIES;
import static com.example.todolist_app_fd.Constant.TASK;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.todolist_app_fd.R;
import com.example.todolist_app_fd.adapter.TaskAdapter;
import com.example.todolist_app_fd.databinding.ActivityTaskShowBinding;
import com.example.todolist_app_fd.firbase.Firebase_Auth_SDP;
import com.example.todolist_app_fd.interface_.CheckBox;
import com.example.todolist_app_fd.model.AddTaskModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TaskShowActivity extends AppCompatActivity implements CheckBox {

    ActivityTaskShowBinding binding;
    String categoriesName;
    Firebase_Auth_SDP obj;
    ArrayList<AddTaskModel> list;
    TaskAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_task_show);

        categoriesName=getIntent().getStringExtra("categoriesName");

        obj=Firebase_Auth_SDP.getInstance();

        list = new ArrayList<>();
        adapter = new TaskAdapter(this, list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycleView.setLayoutManager(linearLayoutManager);

        obj.getFirebaseDatabase().getReference().child(CATEGORIES).child(categoriesName).child(TASK).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    AddTaskModel model=dataSnapshot.getValue(AddTaskModel.class);
                    list.add(model);
                    binding.recycleView.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.addTask.setOnClickListener(view -> {
            Intent intent=new Intent(TaskShowActivity.this,AddTaskActivity.class);
            intent.putExtra("categoriesName",categoriesName);
            startActivity(intent);
            finish();

        });
    }

    @Override
    public void selectBox(boolean check, AddTaskModel model, View view) {
        String task=model.getTask();


        if (check)
        {
            Toast.makeText(this, ""+task, Toast.LENGTH_SHORT).show();
            view.setVisibility(View.VISIBLE);
        }
        else
        {
            view.setVisibility(View.INVISIBLE);
        }


    }
}