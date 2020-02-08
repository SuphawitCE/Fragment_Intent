package com.example.fragment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntentActivity extends AppCompatActivity {

    // id editText2
    EditText edt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int ii = intent.getIntExtra("int", 0);
        String ss = intent.getStringExtra("str");
        String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); // เอาข้อมูลจาก MainActivity

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText("message = " + msg
        + " \n int d = " + String.valueOf(ii)
        + " \n String ss = " + ss );

        intent.putExtra("str", "Value from Intent 2");
        Button btn1;
        edt1 = (EditText) findViewById(R.id.editText2); //
        btn1 = (Button) findViewById(R.id.button); //
    }

    public void backtomain(View view)
    {
        String msg = edt1.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("int", 100);
        intent.putExtra("str", msg); // "From Intent2"
        setResult(1,intent);
        finish();
    }
}
