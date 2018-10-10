package com.example.abhis.crackit4;

import android.app.Application;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    int maxTries = 15;
    int randomNum[] = {0,1,2,3,4};

    ImageView iView[] = new ImageView[5];

    int tries = 0;

    //EditText num1,num2,num3,num4,num5;
    int[] num = new int[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("start" , "Starting program");

        this.generator();
        //this.numChanger();

        Log.i("generator" , "random no. is decided");

        String mode = getIntent().getStringExtra("mode");

        if(mode.equals("easy"))
            maxTries = 15;
        else if(mode.equals("hard"))
            maxTries = 10;

        final TextInputEditText num1 =findViewById(R.id.num1);
        final TextInputEditText num2 =findViewById(R.id.num2);
        final TextInputEditText num3 =findViewById(R.id.num3);
        final TextInputEditText num4 =findViewById(R.id.num4);
        final TextInputEditText num5 =findViewById(R.id.num5);

        //EditText mExpirationYear,mCvvNumber;
        num1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (num1.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    num2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        num2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (num2.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    num3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        num3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (num3.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    num4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        num4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (num4.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    num5.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
    }

    public void clear()
    {

        TextInputEditText num1 =findViewById(R.id.num1);
        TextInputEditText num2 =findViewById(R.id.num2);
        TextInputEditText num3 =findViewById(R.id.num3);
        TextInputEditText num4 =findViewById(R.id.num4);
        TextInputEditText num5 =findViewById(R.id.num5);

        num1.setText("");
        num2.setText("");
        num3.setText("");
        num4.setText("");
        num5.setText("");

        num1.requestFocus();

    }

    public void imageChange(char a, int i) {
        iView[0] = (ImageView) findViewById(R.id.iview1);
        iView[1] = (ImageView) findViewById(R.id.iview2);
        iView[2] = (ImageView) findViewById(R.id.iview3);
        iView[3] = (ImageView) findViewById(R.id.iview4);
        iView[4] = (ImageView) findViewById(R.id.iview5);

        if (a == 'R') {
            iView[i].setImageResource(R.drawable.tick);
            iView[i].setTag(R.drawable.tick);
        } else if (a == 'S') {
            iView[i].setImageResource(R.drawable.circle);
            iView[i].setTag(R.drawable.circle);
        } else if (a == 'X') {
            iView[i].setImageResource(R.drawable.xcross);
            iView[i].setTag(R.drawable.xcross);
        } else if(a == 'D')
        {
            for (int j = 0 ; j < 5 ; j++)
                iView[j].setImageResource(R.drawable.ic_launcher_background);

        } else
            Log.i("IC", "image change error");
    }

    public void buttonPress(View view) {

        //System.out.println("on Submit error");

        this.imageChange('D' , 0);

        Log.i("submit" , "submit execute");

        int input[] = getData();

        this.crack(input);
    }


  /* public void addContent() {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listViewContent);

        listView = (ListView) findViewById(R.id.gameListView);
        listView.setAdapter(arrayAdapter);

    } */


    //This method is used to retrieve the data from num text

    public int[] getData() {

        TextInputEditText num1 =  findViewById(R.id.num1);
        TextInputEditText num2 =  findViewById(R.id.num2);
        TextInputEditText num3 =  findViewById(R.id.num3);
        TextInputEditText num4 =  findViewById(R.id.num4);
        TextInputEditText num5 =  findViewById(R.id.num5);


        num[0] = Integer.parseInt(num1.getText().toString());
        num[1] = Integer.parseInt(num2.getText().toString());
        num[2] = Integer.parseInt(num3.getText().toString());
        num[3] = Integer.parseInt(num4.getText().toString());
        num[4] = Integer.parseInt(num5.getText().toString());

        Log.i("input101",Integer.toString(num[0]));

        return num;
    }

    public void generator() {
        //Random no. is generated here
        for (int i = 0; i < 5; i++) {
            randomNum[i] = ThreadLocalRandom.current().nextInt(0, 9);
        }
    }

    public boolean check(int input) {
        for (int i = 0; i < 5; i++) {
            if (input == randomNum[i]) {
                return true;
            }
        }
        return false;
    }


    //This method is called when submit is clicked

    public void crack(int input[]) {
        boolean win = false;
        //int input[] = new int[5];

        int count = 0;
        int j = 0;


        char[][] inp = new char[5][2];

        //while (true) {
            //Scanner t = new Scanner(System.in);

            //Accepting input
            //int n = t.nextInt();

            System.out.println("Guees");

            //  while(j<5)
            //  {
           // addContent();

           //listViewContent.add(Integer.toString(input[0]) + Integer.toString(input[1]) + Integer.toString(input[2]) + Integer.toString(input[3]) + Integer.toString(input[4]));

            for (int i = 0; i < 5; i++)
            {
                if (input[i] == randomNum[i]) {
                    count++;

                    inp[i][0] = (char)(input[i] = '0');
                    inp[i][1] = 'R';

                    this.imageChange('R', i);
                    // System.out.print("R");
                    Log.i("R", "R");

                }
                else if (check(input[i])) {

                    inp[i][0] = (char)(input[i] = '0');
                    inp[i][1] = 'S';

                    Log.i("S", "S");

                    this.imageChange('S', i);

                    //System.out.print("S");

                } else if (!check(input[i])) {
                    //System.out.print("X");

                    inp[i][0] = (char)(input[i] = '0');
                    inp[i][1] = 'X';

                    this.imageChange('X', i);

                    Log.i("X", "X");
                }
                if (count == 5) {
                    win = true;
                    break;
                }
                else {
                    continue;
                }

            }
            //System.out.println();
            // }

            iView[0] = (ImageView) findViewById(R.id.iview1);
            iView[1] = (ImageView) findViewById(R.id.iview2);
            iView[2] = (ImageView) findViewById(R.id.iview3);
            iView[3] = (ImageView) findViewById(R.id.iview4);
            iView[4] = (ImageView) findViewById(R.id.iview5);

            TextInputEditText num1 =  findViewById(R.id.num1);
            TextInputEditText num2 =  findViewById(R.id.num2);
            TextInputEditText num3 =  findViewById(R.id.num3);
            TextInputEditText num4 =  findViewById(R.id.num4);
            TextInputEditText num5 =  findViewById(R.id.num5);

            addToArray.add(new item( num1.getText().toString(), num2.getText().toString(), num3.getText().toString(), num4.getText().toString(), num5 .getText().toString(), (int)iView[0].getTag(), (int)iView[1].getTag(), (int)iView[2].getTag(), (int)iView[3].getTag(), (int)iView[4].getTag()));

            Log.i("crack1" , num1.getText().toString());


            ArrayAdapter<item> adapter = new customAdapter();

            ListView gamelistView = findViewById(R.id.gameListView);

            gamelistView.setAdapter(adapter);

            tries++;

            this.clear();

            Log.i("Tries Left", Integer.toString(maxTries - tries));

            Toast.makeText(MainActivity.this, Integer.toString(maxTries - tries), Toast.LENGTH_SHORT).show();

            if (win) {
                // System.out.println("You guessed the correct answer");

                Log.i("Win", "Winner Winner Chicken Dinner");

                Log.i("tries", "tries required for cracking the code " + Integer.toString(tries));

                Toast.makeText(MainActivity.this, "You Win",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,End_Screen.class).putExtra("who",Integer.toString(tries));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // System.out.println("");
            } else if (tries >= maxTries) {

               // System.out.println("you failed");
                Log.i("maxTries", "You Lost");

                Toast.makeText(MainActivity.this, "You Lost",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,End_Screen.class).putExtra("who","lost");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return;
            }


       // }

    }



   /* public void addToList(char inp[][])
    {
        String input;

        ListView listView;
        ArrayList<String> listViewContent = new ArrayList<>();

        //listViewContent.add(Integer.toString(input[0]) + Integer.toString(input[1]) + Integer.toString(input[2]) + Integer.toString(input[3]) + Integer.toString(input[4]));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listViewContent);

        listView = (ListView) findViewById(R.id.gameListView);
        listView.setAdapter(arrayAdapter);

        for(int i = 0 ; i < 5 ; i++)
        {
            if(inp[i][1] == 'R')
            {
                listViewContent.add(Integer.toString(inp[i][0]));
            }
            else if(inp[i][1] == 'S')
            {

            }
            else if(inp[i][1] == 'X')
            {

            }
        }

    } */

    private List<item> addToArray = new ArrayList<>();

    private class customAdapter extends ArrayAdapter<item> {

        public customAdapter()
        {
            super(MainActivity.this, R.layout.item, addToArray);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if(convertView == null)
            {
                convertView = getLayoutInflater().inflate(R.layout.item, parent, false);
            }

            item currentItem = addToArray.get(position);

            //ConstraintLayout layout = findViewById(R.id.rl_container);

            ImageView iview1 = (ImageView) convertView.findViewById(R.id.imageV1);
            ImageView iview2 = (ImageView) convertView.findViewById(R.id.imageV2);
            ImageView iview3 = (ImageView) convertView.findViewById(R.id.imageV3);
            ImageView iview4 = (ImageView) convertView.findViewById(R.id.imageV4);
            ImageView iview5 = (ImageView) convertView.findViewById(R.id.imageV5);

            TextView num1 =  convertView.findViewById(R.id.textV1);
            TextView num2 =  convertView.findViewById(R.id.textV2);
            TextView num3 =  convertView.findViewById(R.id.textV3);
            TextView num4 =  convertView.findViewById(R.id.textV4);
            TextView num5 =  convertView.findViewById(R.id.textV5);

            num1.setText(currentItem.getNum1());
            num2.setText(currentItem.getNum2());
            num3.setText(currentItem.getNum3());
            num4.setText(currentItem.getNum4());
            num5.setText(currentItem.getNum5());

            Log.i("adapter101", currentItem.getNum1().toString());

            iview1.setImageResource(currentItem.getIview1());
            iview2.setImageResource(currentItem.getIview2());
            iview3.setImageResource(currentItem.getIview3());
            iview4.setImageResource(currentItem.getIview4());
            iview5.setImageResource(currentItem.getIview5());

            /*if(num1.getParent()!=null){
                ((ViewGroup)num1.getParent()).removeView(num1);
            }
            layout.addView(num1);

            if(num2.getParent()!=null){
                ((ViewGroup)num2.getParent()).removeView(num2);
            }
            layout.addView(num2);

            if(num3.getParent()!=null){
                ((ViewGroup)num3.getParent()).removeView(num3);
            }
            layout.addView(num3);

            if(num4.getParent()!=null){
                ((ViewGroup)num4.getParent()).removeView(num4);
            }
            layout.addView(num4);

            if(num5.getParent()!=null){
                ((ViewGroup)num5.getParent()).removeView(num5);
            }
            layout.addView(num5); */


            return convertView;
        }
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(MainActivity.this, Login_Page.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

