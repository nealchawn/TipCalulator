package com.example.zzz.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculator extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipcalculator);

        final EditText totalNoTip = (EditText)findViewById(R.id.totalTxt);

        final TextView tipText = (TextView)findViewById(R.id.txtTip);
        final SeekBar tipBar = (SeekBar)findViewById(R.id.seekBarTip);

        final EditText peopleCount = (EditText)findViewById(R.id.peopleTxt);
        final Button calculate = (Button)findViewById(R.id.calcBtn);
        final TextView result = (TextView)findViewById(R.id.txtOut);
        //initialize to 15% default
        tipBar.setProgress(15);
        tipText.setText("Tip Percentage: "+tipBar.getProgress()+"%");

        tipBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"Change Tracking",Toast.LENGTH_SHORT).show();
                tipText.setText("Tip Percentage: "+progress+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(),"Start Tracking",Toast.LENGTH_SHORT).show();
                //results.setText("test 1");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                //Toast.makeText(getApplicationContext(),"Stop Tracking",Toast.LENGTH_SHORT).show();
                //seekBar.get
                //results.setText("test 2");
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String results = "";

                float total_wo_tip = Float.parseFloat(totalNoTip.getText().toString());
                results = "Total Bill:   $"+String.format("%.2f", total_wo_tip)+"\n";
                int tip_rate = tipBar.getProgress();
                float tip_amount = total_wo_tip *(float)tip_rate/100;
                results += "Tip Amount:   $"+String.format("%.2f", tip_amount)+"\n";
                float total_w_tip = total_wo_tip + tip_amount;
                results += "Tip w/Tip:   $"+String.format("%.2f", total_w_tip)+"\n";
                int total_people = Integer.parseInt(peopleCount.getText().toString())+1;
                float split = total_w_tip/(float)total_people;
                results += "Split:   $"+String.format("%.2f", split)+"\n";
                result.setText(results);
            }
        });

    }
}
