package com.rangelbeatriz.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    private RecyclerView rv;
    List<MainItem> mainItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainItems = new ArrayList<>();


        MainAdapter mainAdapter = new MainAdapter(mainItems);
        mainItems.add(new MainItem(1, R.drawable.ic_baseline_wb_sunny_24, R.string.imc, R.color.colorPrimary));
        mainItems.add(new MainItem(2, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.string.tmb, R.color.black));

        rv = findViewById(R.id.rv_main);
        rv.setLayoutManager(new GridLayoutManager(context, 2)); //Como o layout vai se comportar
        rv.setAdapter(mainAdapter);

        mainAdapter.setListener(id -> {
            Intent intent;
            switch (id){
                case 1:
                    intent = new Intent(context, ImcActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(context, TmbActivity.class);
                    startActivity(intent);
                    break;
            }

        });

    }

}