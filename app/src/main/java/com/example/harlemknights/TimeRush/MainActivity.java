package com.example.harlemknights.TimeRush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.harlemknights.TimeRush.InstructionActivity;
import com.example.harlemknights.TimeRush.PlayGameActivity;
import com.example.harlemknights.TimeRush.ReflexModeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    Button b1 = (Button) findViewById(R.id.button1);
    b1.setOnClickListener(new View.OnClickListener() {;

    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,PlayGameActivity.class)) ;
    }
});

Button InstructionButton = (Button) findViewById(R.id.button3);
        InstructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,InstructionActivity.class));
            }
        });


        Button ReflexButton = (Button) findViewById(R.id.ReflexButton);
        ReflexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ReflexModeActivity.class));
            }
        });



    }
}
