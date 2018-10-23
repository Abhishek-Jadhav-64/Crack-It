package com.gtn.abhis.crackit4;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_Page extends AppCompatActivity {

    //Not a login page. Home Page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

    }

    public void easypressed(View view)
    {
        Intent intent = new Intent(Login_Page.this,MainActivity.class).putExtra("mode", "easy");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void hardpressed(View view)
    {
        Intent intent = new Intent(Login_Page.this,MainActivity.class).putExtra("mode", "easy");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void about(View view)
    {
        Intent intent = new Intent(Login_Page.this,AboutMe.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void howtoplay(View view)
    {
        Intent intent = new Intent(Login_Page.this,HowToPlay.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void quitit(View view)
    {
        System.exit(1);
    }
}
