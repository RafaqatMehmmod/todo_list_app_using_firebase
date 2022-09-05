package com.example.todolist_app_fd.activities;

import static com.example.todolist_app_fd.Constant.CATEGORIES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.todolist_app_fd.R;
import com.example.todolist_app_fd.activities.addcategories.AddCategories;
import com.example.todolist_app_fd.adapter.CategoriesAdapter;
import com.example.todolist_app_fd.databinding.ActivityMainBinding;
import com.example.todolist_app_fd.firbase.Firebase_Auth_SDP;
import com.example.todolist_app_fd.interface_.ClickCategories;
import com.example.todolist_app_fd.model.AddCategoriesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ClickCategories {

    ActivityMainBinding binding;
    Firebase_Auth_SDP obj;
    ArrayList<AddCategoriesModel> list;
    CategoriesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this, R.layout.activity_main);


       obj=Firebase_Auth_SDP.getInstance();

        list = new ArrayList<>();
        adapter = new CategoriesAdapter(this, list,this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycleView.setLayoutManager(new GridLayoutManager(this,2));

        obj.getFirebaseDatabase().getReference().child(CATEGORIES).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    AddCategoriesModel model=dataSnapshot.getValue(AddCategoriesModel.class);
                    list.add(model);
                    binding.recycleView.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


       binding.addCategories.setOnClickListener(view -> {
          startActivity(new Intent(MainActivity.this, AddCategories.class));
       });
    }

    @Override
    public void clickItem(AddCategoriesModel model) {
        String categoriesName=model.getCategoriesName();
        Intent intent=new Intent(MainActivity.this,TaskShowActivity.class);
        intent.putExtra("categoriesName",categoriesName);
        startActivity(intent);
    }
}