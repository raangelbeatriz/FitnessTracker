package com.rangelbeatriz.fitnesstracker;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity {
    final Context context = this;
    private EditText editHeight;
    private EditText editWeight;
    private Button btnCalc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        editHeight = findViewById(R.id.edit_imc_height);
        editWeight = findViewById(R.id.edit_imc_weight);
        btnCalc = findViewById(R.id.btn_calc);
        btnCalc.setOnClickListener(v -> {
            if (!validate()){
                Toast.makeText(context, R.string.fields_message, Toast.LENGTH_LONG).show();
                return;
            }

            int height = Integer.parseInt(editHeight.getText().toString());
            int weight = Integer.parseInt(editWeight.getText().toString());

            double imc = calculate(height, weight);

            int imcResponse = imcResponse(imc);
            setAlertDialog(R.string.imc_response, imc, imcResponse);

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            //Hide based on which view(edit text) made the keyboard appear
            imm.hideSoftInputFromWindow(editHeight.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(editWeight.getWindowToken(), 0);
        });
    }

    @StringRes
    int imcResponse(double imc){
        if (imc < 15)
            return R.string.imc_severely_low_weight;
        else if(imc <16)
            return R.string.imc_very_low_weight;
        else if (imc < 18.5)
            return R.string.imc_low_weight;
        else if (imc <25)
            return R.string.imc_normal;
        else if (imc <30)
            return R.string.imc_high_weight;
        else if (imc <35)
            return R.string.imc_so_high_weight;
        else if (imc <40)
            return R.string.imc_severely_high_weight;
        else
            return R.string.imc_extreme_weight;
    }

    boolean validate(){
        return (!editWeight.getText().toString().startsWith("0") &&
                !editHeight.getText().toString().startsWith("0") &&
                !editWeight.getText().toString().isEmpty() &&
                !editHeight.getText().toString().isEmpty());
    }

    double calculate(int height, int weight){
        return weight / ( ((double) height / 100) * ((double) height / 100) );
    }

    void setAlertDialog(@StringRes int titleString, double imc, @StringRes int result){
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(getString(titleString, imc))
                .setMessage(result)
                .setPositiveButton(android.R.string.ok, (dialog1, which) -> {
                })
                .setNegativeButton(R.string.save, (dialog1, which) -> {
                    new Thread(() ->{
                        long calcId = SqlHelper.getInstance(context).addItem("imc", result);
                        runOnUiThread(() -> {
                            if (calcId > 0)
                                Toast.makeText(context, R.string.saved,Toast.LENGTH_SHORT).show();
                        });
                    }).start();

                })
                .create();
        dialog.show();
    }
}