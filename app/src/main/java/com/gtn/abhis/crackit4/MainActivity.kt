package com.gtn.abhis.crackit4

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class MainActivity : AppCompatActivity() {
    var maxTries = 15
    var randomNum = intArrayOf(0, 1, 2, 3, 4)
    var iView = arrayOfNulls<ImageView>(5)
    var tries = 0

    //EditText num1,num2,num3,num4,num5;
    var num = IntArray(5)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("start", "Starting program")
        generator()
        //this.numChanger();
        Log.i("generator", "random no. is decided")
        val mode = intent.getStringExtra("mode")
        if (mode == "easy") maxTries = 15 else if (mode == "hard") maxTries = 10
        val num1 = findViewById<TextInputEditText>(R.id.num1)
        val num2 = findViewById<TextInputEditText>(R.id.num2)
        val num3 = findViewById<TextInputEditText>(R.id.num3)
        val num4 = findViewById<TextInputEditText>(R.id.num4)
        val num5 = findViewById<TextInputEditText>(R.id.num5)

        //EditText mExpirationYear,mCvvNumber;
        num1.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // TODO Auto-generated method stub
                if (num1.text.toString().trim { it <= ' ' }.length == 1) //size as per your requirement
                {
                    num2.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })
        num2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // TODO Auto-generated method stub
                if (num2.text.toString().trim { it <= ' ' }.length == 1) //size as per your requirement
                {
                    num3.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })
        num3.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // TODO Auto-generated method stub
                if (num3.text.toString().trim { it <= ' ' }.length == 1) //size as per your requirement
                {
                    num4.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })
        num4.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // TODO Auto-generated method stub
                if (num4.text.toString().trim { it <= ' ' }.length == 1) //size as per your requirement
                {
                    num5.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })
    }

    fun clear() {
        val num1 = findViewById<TextInputEditText>(R.id.num1)
        val num2 = findViewById<TextInputEditText>(R.id.num2)
        val num3 = findViewById<TextInputEditText>(R.id.num3)
        val num4 = findViewById<TextInputEditText>(R.id.num4)
        val num5 = findViewById<TextInputEditText>(R.id.num5)
        num1.setText("")
        num2.setText("")
        num3.setText("")
        num4.setText("")
        num5.setText("")
        num1.requestFocus()
    }

    fun imageChange(a: Char, i: Int) {
        iView[0] = findViewById<View>(R.id.iview1) as ImageView
        iView[1] = findViewById<View>(R.id.iview2) as ImageView
        iView[2] = findViewById<View>(R.id.iview3) as ImageView
        iView[3] = findViewById<View>(R.id.iview4) as ImageView
        iView[4] = findViewById<View>(R.id.iview5) as ImageView
        if (a == 'R') {
            iView[i]!!.setImageResource(R.drawable.tick)
            iView[i]!!.tag = R.drawable.tick
        } else if (a == 'S') {
            iView[i]!!.setImageResource(R.drawable.circle)
            iView[i]!!.tag = R.drawable.circle
        } else if (a == 'X') {
            iView[i]!!.setImageResource(R.drawable.xcross)
            iView[i]!!.tag = R.drawable.xcross
        } else if (a == 'D') {
            for (j in 0..4) iView[j]!!.setImageResource(R.drawable.ic_launcher_background)
        } else Log.i("IC", "image change error")
    }

    fun buttonPress(view: View?) {

        //System.out.println("on Submit error");
        imageChange('D', 0)
        Log.i("submit", "submit execute")
        val input = data
        if (input[0] == -1) {
            Toast.makeText(this@MainActivity, "Invalid Number", Toast.LENGTH_SHORT).show()
            this.clear()
        } else {
            crack(input)
        }
    }

    /* public void addContent() {

          ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listViewContent);

          listView = (ListView) findViewById(R.id.gameListView);
          listView.setAdapter(arrayAdapter);

      } */
    //This method is used to retrieve the data from num text
    val data: IntArray
        get() {
            val num1 = findViewById<TextInputEditText>(R.id.num1)
            val num2 = findViewById<TextInputEditText>(R.id.num2)
            val num3 = findViewById<TextInputEditText>(R.id.num3)
            val num4 = findViewById<TextInputEditText>(R.id.num4)
            val num5 = findViewById<TextInputEditText>(R.id.num5)
            if (num1.text.toString() == null || num2.text.toString() == null || num3.text.toString() == null || num4.text.toString() == null || num5.text.toString() == null) {
                num[0] = -1
            } else if (isSpecial(num1) || isSpecial(num2) || isSpecial(num2) || isSpecial(num3) || isSpecial(num4) || isSpecial(num5)) {
                num[0] = -1
            } else {
                num[0] = num1.text.toString().toInt()
                num[1] = num2.text.toString().toInt()
                num[2] = num3.text.toString().toInt()
                num[3] = num4.text.toString().toInt()
                num[4] = num5.text.toString().toInt()
            }
            Log.i("input101", Integer.toString(num[0]))
            return num
        }

    fun isSpecial(t: TextInputEditText): Boolean {
        val a: Int
        try {
            a = t.text.toString().toInt() //use your variable or object in place of obj
            println("$a is a integer number.")
        } catch (e: NumberFormatException) {
            return true
        }
        return false
    }

    fun generator() {
        //Random no. is generated here
        for (i in 0..4) {
            randomNum[i] = ThreadLocalRandom.current().nextInt(0, 9)
        }
    }

    fun check(input: Int): Boolean {
        for (i in 0..4) {
            if (input == randomNum[i]) {
                return true
            }
        }
        return false
    }

    //This method is called when submit is clicked
    fun crack(input: IntArray) {
        var win = false
        //int input[] = new int[5];
        var count = 0
        val j = 0
        val inp = Array(5) { CharArray(2) }

        //while (true) {
        //Scanner t = new Scanner(System.in);

        //Accepting input
        //int n = t.nextInt();
        println("Guees")

        //  while(j<5)
        //  {
        // addContent();

        //listViewContent.add(Integer.toString(input[0]) + Integer.toString(input[1]) + Integer.toString(input[2]) + Integer.toString(input[3]) + Integer.toString(input[4]));
        for (i in 0..4) {
            if (input[i] == randomNum[i]) {
                count++
                inp[i][0] = '0'.also { input[i] = it.toInt() }
                inp[i][1] = 'R'
                imageChange('R', i)
                // System.out.print("R");
                Log.i("R", "R")
            } else if (check(input[i])) {
                inp[i][0] = '0'.also { input[i] = it.toInt() }
                inp[i][1] = 'S'
                Log.i("S", "S")
                imageChange('S', i)

                //System.out.print("S");
            } else if (!check(input[i])) {
                //System.out.print("X");
                inp[i][0] = '0'.also { input[i] = it.toInt() }
                inp[i][1] = 'X'
                imageChange('X', i)
                Log.i("X", "X")
            }
            if (count == 5) {
                win = true
                break
            } else {
                continue
            }
        }
        //System.out.println();
        // }
        iView[0] = findViewById<View>(R.id.iview1) as ImageView
        iView[1] = findViewById<View>(R.id.iview2) as ImageView
        iView[2] = findViewById<View>(R.id.iview3) as ImageView
        iView[3] = findViewById<View>(R.id.iview4) as ImageView
        iView[4] = findViewById<View>(R.id.iview5) as ImageView
        val num1 = findViewById<TextInputEditText>(R.id.num1)
        val num2 = findViewById<TextInputEditText>(R.id.num2)
        val num3 = findViewById<TextInputEditText>(R.id.num3)
        val num4 = findViewById<TextInputEditText>(R.id.num4)
        val num5 = findViewById<TextInputEditText>(R.id.num5)
        addToArray.add(item(num1.text.toString(), num2.text.toString(), num3.text.toString(), num4.text.toString(), num5.text.toString(), iView[0]!!.tag as Int, iView[1]!!.tag as Int, iView[2]!!.tag as Int, iView[3]!!.tag as Int, iView[4]!!.tag as Int))
        Log.i("crack1", num1.text.toString())
        val adapter: customAdapter = customAdapter()
        val gamelistView = findViewById<ListView>(R.id.gameListView)
        gamelistView.adapter = adapter
        tries++
        this.clear()
        Log.i("Tries Left", Integer.toString(maxTries - tries))
        Toast.makeText(this@MainActivity, Integer.toString(maxTries - tries), Toast.LENGTH_SHORT).show()
        if (win) {
            // System.out.println("You guessed the correct answer");
            Log.i("Win", "Winner Winner Chicken Dinner")
            Log.i("tries", "tries required for cracking the code " + Integer.toString(tries))
            Toast.makeText(this@MainActivity, "You Win", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, End_Screen::class.java).putExtra("who", Integer.toString(tries))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            // System.out.println("");
        } else if (tries >= maxTries) {

            // System.out.println("you failed");
            Log.i("maxTries", "You Lost")
            Toast.makeText(this@MainActivity, "You Lost", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, End_Screen::class.java).putExtra("who", "lost")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            return
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
    private val addToArray: MutableList<item> = ArrayList()

    private inner class customAdapter : ArrayAdapter<item?>(this@MainActivity, R.layout.item, addToArray as List<item?>) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.item, parent, false)
            }
            val currentItem = addToArray[position]

            //ConstraintLayout layout = findViewById(R.id.rl_container);
            val iview1 = convertView!!.findViewById<View>(R.id.imageV1) as ImageView
            val iview2 = convertView.findViewById<View>(R.id.imageV2) as ImageView
            val iview3 = convertView.findViewById<View>(R.id.imageV3) as ImageView
            val iview4 = convertView.findViewById<View>(R.id.imageV4) as ImageView
            val iview5 = convertView.findViewById<View>(R.id.imageV5) as ImageView
            val num1 = convertView.findViewById<TextView>(R.id.textV1)
            val num2 = convertView.findViewById<TextView>(R.id.textV2)
            val num3 = convertView.findViewById<TextView>(R.id.textV3)
            val num4 = convertView.findViewById<TextView>(R.id.textV4)
            val num5 = convertView.findViewById<TextView>(R.id.textV5)
            num1.text = currentItem.num1
            num2.text = currentItem.num2
            num3.text = currentItem.num3
            num4.text = currentItem.num4
            num5.text = currentItem.num5
            Log.i("adapter101", currentItem.num1.toString())
            iview1.setImageResource(currentItem.iview1)
            iview2.setImageResource(currentItem.iview2)
            iview3.setImageResource(currentItem.iview3)
            iview4.setImageResource(currentItem.iview4)
            iview5.setImageResource(currentItem.iview5)

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
            layout.addView(num5); */return convertView
        }
    }

    override fun onBackPressed() {
        finish()
        val intent = Intent(this@MainActivity, LoginPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}