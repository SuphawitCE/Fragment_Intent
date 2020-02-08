package com.example.fragment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.fragment_1.MESSAGE"; // intent
    private Button fgButton;
    private boolean isFragmentDisplayed = false;

    // Saved instance state key.
    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fgButton = findViewById(R.id.button1);
        // If returning from a configuration change, get the
        // Fragment state and set the button text.
        if(savedInstanceState != null)
        {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            if(isFragmentDisplayed)
            {
                // If the fragment is displayed, changed button to "close".
                fgButton.setText(R.string.close);
            }
        }

        // Set the click listener for the button.
        fgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(! isFragmentDisplayed)   displayFragment();
                else    closeFragment();
            }
        });


    }

    public void displayFragment()
    {
        // Instantiate the fragment.
        BasicFragment simpleFragment = BasicFragment.newInstance();

        // Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Add the SimpleFragment.
        fragmentTransaction.add(R.id.fragment, simpleFragment).addToBackStack(null).commit();

        // Update the Button text.
        fgButton.setText(R.string.close);
        //Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true;
    }

    public void closeFragment()
    {
        // Get the FragmentManager.
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Check to see if the fragment is already showing.
        BasicFragment basicFragment = (BasicFragment) fragmentManager.findFragmentById(R.id.fragment);

        if(basicFragment != null)
        {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(basicFragment).commit();
        }

        //Update the Button text.
        fgButton.setText(R.string.open);
        // Set boolean flag to indicate fragment is closed.
        isFragmentDisplayed = false;

    }

    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        // Save the state of the fragment (true = open, falose = close).

        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);

    }

    public void sendMessage(View view) {

        // Input
        int a = 10;
        int b = 0;
        int c = 0;
        int arr[] = {a, b, c};
        String s = "From Main";

        Intent intent = new Intent(this, IntentActivity.class); // swap to

        // Send Data

        EditText editText = (EditText) findViewById(R.id.editText); //Casting Operator to EditText
        String msg = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, msg);


        //for(int i = 0;i < arr.length;i++)    intent.putExtra("int", arr[i]);

        intent.putExtra("str", s);
        intent.putExtra("int", a);
        /*
        intent.putExtra("DataInt", a);
        intent.putExtra("DataInt", b);
        intent.putExtra("Dataint", c); */
        //intent.putExtra("DataIntArray", arr);

        int reqCode = 1;
        startActivityForResult(intent,reqCode);
    }

    // Call back method to get the message from other activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        EditText editText = (EditText) findViewById(R.id.editText);
        super.onActivityResult(requestCode,resultCode,intent);

        if(requestCode == 1)
        {
            EditText textView = findViewById(R.id.editText);
            int ii = intent.getIntExtra("int",0);
            String ss = intent.getStringExtra("str");
            textView.setText(ss);
        }


    }



}
