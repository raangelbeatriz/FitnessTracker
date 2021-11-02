package com.rangelbeatriz.fitnesstracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    List<MainItem> mainItems;
    private OnItemClickListener listener;

    MainAdapter(List<MainItem> mainItems){
        this.mainItems = mainItems;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        MainItem currentItem = mainItems.get(position);
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return mainItems.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder{
        TextView textView = itemView.findViewById(R.id.item_text);
        ImageView imageView = itemView.findViewById(R.id.item_image);
        LinearLayout btnImc = (LinearLayout) itemView.findViewById(R.id.btn_imc);

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(MainItem item){
            btnImc.setOnClickListener(v -> listener.onClick(item.getId()));
            textView.setText(item.getTitle());
            imageView.setImageResource(item.getDrawableId());
            btnImc.setBackgroundColor(item.getColor());
        }
    }

}
