package com.basov.tempconverter;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class TempConverterActivity extends AppCompatActivity {

    private String fDegreeString = "";
    private String degreeResultString = "";
    private EditText degreeEditText;
    private TextView resultTextView;
    private Button convertButton;
    private SharedPreferences savedValues;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);


        degreeEditText = (EditText) findViewById(R.id.degreeEditText);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        convertButton = (Button) findViewById(R.id.convertButton);
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        degreeEditText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //uncomment if convert Button is not in use
               // convertDegrees();
                return false;
            }
        });

        degreeEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                convertDegrees();
                return false;
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertDegrees();
            }
        });
    }

    public void convertDegrees(){
        fDegreeString = degreeEditText.getText().toString();
        degreeResultString = "";
        if(fDegreeString.equals("") == false)
        {
            Float f = Float.parseFloat(fDegreeString);
            Float c = (f - 32) * 5 / 9;

            degreeResultString = c.toString();

        }
        resultTextView.setText(degreeResultString);
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {

        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("fDegreeString", fDegreeString);
        editor.putString("degreeResultString", degreeResultString);
        editor.commit();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        fDegreeString = savedValues.getString("fDegreeString", "");
        degreeEditText.setText(fDegreeString);

        degreeResultString = savedValues.getString("degreeResultString", "");
        resultTextView.setText(degreeResultString);

        // ensure
        convertDegrees();
    }
}
