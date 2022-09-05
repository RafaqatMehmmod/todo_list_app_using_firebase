package com.example.todolist_app_fd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist_app_fd.R;
import com.example.todolist_app_fd.databinding.ShowCategoriesListBinding;
import com.example.todolist_app_fd.databinding.ShowTaskListBinding;
import com.example.todolist_app_fd.interface_.CheckBox;
import com.example.todolist_app_fd.interface_.ClickCategories;
import com.example.todolist_app_fd.model.AddCategoriesModel;
import com.example.todolist_app_fd.model.AddTaskModel;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context context;
    public static ArrayList<AddTaskModel> list;
    private CheckBox checkBox;
    boolean b;



    public TaskAdapter(Context context, ArrayList<AddTaskModel> list,CheckBox checkBox) {
        this.context = context;
        this.list = list;
        this.checkBox=checkBox;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ShowTaskListBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.show_task_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AddTaskModel model = list.get(position);
        holder.binding.setItem(model);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ShowTaskListBinding binding;

        public ViewHolder(@NonNull ShowTaskListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


            binding.selection.setOnClickListener(view ->{

                    checkBox.selectBox(binding.selection.isChecked(), list.get(getAdapterPosition()),binding.view);

            });

        }


    }
}
