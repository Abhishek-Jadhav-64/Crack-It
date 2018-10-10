package com.example.abhis.crackit4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class End_Screen extends AppCompatActivity {

    TextView who;
    TextView guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end__screen);

        who = findViewById(R.id.endtext);
        guess = findViewById(R.id.guess);

        String result = getIntent().getStringExtra("who");

        if(result.equals("lost"))
        {
            who.setText("Better Luck Next Time");
        }
        else
        {
            who.setText("You Won!!!");
            guess.setText("No of guess required: " + result);
        }
    }

    public void tohome(View view)
    {
        Intent intent = new Intent(End_Screen.this,Login_Page.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
