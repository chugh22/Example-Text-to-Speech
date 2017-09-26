package com.example.anirudh.speechrecognition;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tv ;
    Button btn ;
    public static final int SPEECH_REQUEST_CODE = 8878 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button) ;
        tv = (TextView) findViewById(R.id.textView) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechToText();
            }
        });

    }

    public void speechToText(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH) ;
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL , RecognizerIntent.LANGUAGE_MODEL_FREE_FORM) ;
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE , Locale.getDefault()) ;
        i.putExtra(RecognizerIntent.EXTRA_PROMPT , "Say Something ......") ;
        try{
            startActivityForResult(i ,SPEECH_REQUEST_CODE);
        }catch (ActivityNotFoundException anfe){
            Toast.makeText(this , "Activity not found exception" ,Toast.LENGTH_SHORT ).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SPEECH_REQUEST_CODE) {
            if(resultCode == RESULT_OK){
                ArrayList<String> result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                tv.setText(result.get(0));
            }
        }

    }


}
