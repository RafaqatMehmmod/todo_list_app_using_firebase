package com.example.todolist_app_fd.activities.addcategories;

import static com.example.todolist_app_fd.Constant.CATEGORIES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.todolist_app_fd.R;
import com.example.todolist_app_fd.activities.MainActivity;
import com.example.todolist_app_fd.databinding.ActivityAddCategoriesBinding;
import com.example.todolist_app_fd.firbase.Firebase_Auth_SDP;
import com.example.todolist_app_fd.model.AddCategoriesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AddCategories extends AppCompatActivity {

    ActivityAddCategoriesBinding binding;
    private Firebase_Auth_SDP obj;
    private String categoriesName,key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_categories);

        obj=Firebase_Auth_SDP.getInstance();


        binding.addCategories.setOnClickListener(view -> {
            categoriesName=binding.categoriesName.getText().toString();
            if (categoriesName.isEmpty())
            {
                Toast.makeText(this, "Write Name", Toast.LENGTH_SHORT).show();
            }
            else
            {
                sendData(categoriesName,key);
            }
        });
    }

    private void sendData(String categoriesName, String s_key) {
        s_key = obj.getDatabaseReference().push().getKey();
        String finalS_key = s_key;
        obj.getFirebaseDatabase().getReference().child(CATEGORIES).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AddCategoriesModel model=new AddCategoriesModel(categoriesName, finalS_key);

                obj.getFirebaseDatabase().getReference().child(CATEGORIES).child(categoriesName).setValue(model);
                startActivity(new Intent(AddCategories.this, MainActivity.class));
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}