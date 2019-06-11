package com.ultron.sahilpratap.voicerecognisationsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView t;
    AlertDialog.Builder ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        t = findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"say something!");
                startActivityForResult(intent,0);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        List<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

        final String value = list.get(0);

        TextView txt = new TextView(this);
        t.setText(value);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ab = new AlertDialog.Builder(MainActivity.this);
                ab.setTitle("Meaning");
                ab.setIcon(R.mipmap.ic_launcher);
                ab.setMessage("Description of the word goes here");

                ab.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });


                ab.show();
            }
        });

        /*   t.setMovementMethod(LinkMovementMethod.getInstance());
        String str[] = value.split(" ");
        SpannableString ss = new SpannableString(value);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {

                Toast.makeText(MainActivity.this,""+value,Toast.LENGTH_LONG).show();

            }
        };

            ss.setSpan(clickableSpan, 0, value.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        t.setText(ss);


        TextView txt = new TextView(this);
        String[] devFull = value.split(" ");
        txt.append(devFull[0]);
        SpannableString[] link = new SpannableString[devFull.length-1];
        ClickableSpan[] cs = new ClickableSpan[devFull.length-1];
        String linkWord;
        String[] devDevFull = new String[2];

        for(int i=1; i<devFull.length; i++){
            //obtaining 'clear' link
            devDevFull = devFull[i].split(" ");
            link[i-1] = new SpannableString(devDevFull[0]);
            linkWord = devDevFull[0];
            cs[i-1] = new ClickableSpan(){
                private String w = linkWord;
                @Override
                public void onClick(View widget) {

                }
            };
            link[i-1].setSpan(cs[i-1], 0, linkWord.length(), 0);
            txt.append(link[i-1]);
            try{
                txt.append(devDevFull[1]);
            }
            catch(Exception e){}
        }

     */

        super.onActivityResult(requestCode, resultCode, data);
    }
}
