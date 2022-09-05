package com.example.todolist_app_fd.activities;

import static com.example.todolist_app_fd.Constant.CATEGORIES;
import static com.example.todolist_app_fd.Constant.TASK;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.todolist_app_fd.R;
import com.example.todolist_app_fd.activities.addcategories.AddCategories;
import com.example.todolist_app_fd.databinding.ActivityAddTaskBinding;
import com.example.todolist_app_fd.firbase.Firebase_Auth_SDP;
import com.example.todolist_app_fd.model.AddCategoriesModel;
import com.example.todolist_app_fd.model.AddTaskModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AddTaskActivity extends AppCompatActivity {

    ActivityAddTaskBinding binding;
    private Firebase_Auth_SDP obj;
    private String categoriesName,key,task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_task);

        categoriesName=getIntent().getStringExtra("categoriesName");
        obj=Firebase_Auth_SDP.getInstance();

        binding.addTask.setOnClickListener(view -> {
            task=binding.taskName.getText().toString();
            if (task.isEmpty())
            {
                Toast.makeText(this, "Write Task", Toast.LENGTH_SHORT).show();
            }
            else
            {
                sendData(categoriesName,key,task);
                Log.i("mehmood", "sendData.......: "+categoriesName);
            }
        });
    }

    private void sendData(String s_categoriesName, String s_key,String s_name) {
        s_key = obj.getDatabaseReference().push().getKey();
        String finalS_key = s_key;
        obj.getFirebaseDatabase().getReference().child(CATEGORIES).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AddTaskModel model=new AddTaskModel(s_name,finalS_key,s_categoriesName);
                obj.getFirebaseDatabase().getReference().child(CATEGORIES).child(categoriesName).child(TASK).child(finalS_key).setValue(model);
                Intent intent=new Intent(AddTaskActivity.this, TaskShowActivity.class);
                intent.putExtra("categoriesName",categoriesName);
                startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}