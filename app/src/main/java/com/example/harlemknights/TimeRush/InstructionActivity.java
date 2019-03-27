package com.example.harlemknights.TimeRush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        TextView instruct = (TextView) findViewById(R.id.instructionText);


    }
}
