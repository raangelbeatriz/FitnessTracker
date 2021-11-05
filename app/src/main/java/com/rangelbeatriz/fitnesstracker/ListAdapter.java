package com.rangelbeatriz.fitnesstracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    List<Register> register;

    ListAdapter (List<Register> register){
        this.register = register;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListViewHolder holder, int position) {
        Register item = register.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return register.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Register item){
            TextView imc_response = itemView.findViewById(R.id.item_imc_response);
            TextView imc_date = itemView.findViewById(R.id.item_imc_date);
            TextView imc_value = itemView.findViewById(R.id.item_imc_value);

            imc_response.setText(Double.toString(item.response));
            imc_date.setText(item.createdDate);
            imc_value.setText(item.type);

        }
    }
}
