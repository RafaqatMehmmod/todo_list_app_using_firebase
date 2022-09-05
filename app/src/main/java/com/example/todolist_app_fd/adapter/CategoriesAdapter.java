package com.example.todolist_app_fd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.todolist_app_fd.R;
import com.example.todolist_app_fd.databinding.ShowCategoriesListBinding;
import com.example.todolist_app_fd.interface_.ClickCategories;
import com.example.todolist_app_fd.model.AddCategoriesModel;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private Context context;
    public static ArrayList<AddCategoriesModel> list;
    private ClickCategories clickCategories;



    public CategoriesAdapter(Context context, ArrayList<AddCategoriesModel> list,ClickCategories clickCategories) {
        this.context = context;
        this.list = list;
        this.clickCategories=clickCategories;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ShowCategoriesListBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.show_categories_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AddCategoriesModel model = list.get(position);
        holder.binding.setItem(model);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ShowCategoriesListBinding binding;

        public ViewHolder(@NonNull ShowCategoriesListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.pdfCardView.setOnClickListener(view -> {
                clickCategories.clickItem(list.get(getAdapterPosition()));
            });

        }


    }
}
