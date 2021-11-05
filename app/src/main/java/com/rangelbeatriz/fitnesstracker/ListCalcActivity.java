package com.rangelbeatriz.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListCalcActivity extends AppCompatActivity {
    Context context = this;
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_calc);
        rv = findViewById(R.id.rv_list);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String type = extras.getString("type");

            List<Register> registers = new ArrayList<>();
            ListAdapter adapter = new ListAdapter(registers);
            rv.setLayoutManager(new LinearLayoutManager(context));
            rv.setAdapter(adapter);
            new Thread(() -> {
                List<Register> res = SqlHelper.getInstance(context).getRegisterBy(type);
                runOnUiThread(() ->{
                    registers.addAll(res);
                    adapter.notifyDataSetChanged();
                    Log.d("List", registers.toString());
                });
            }).start();
        }
    }
}