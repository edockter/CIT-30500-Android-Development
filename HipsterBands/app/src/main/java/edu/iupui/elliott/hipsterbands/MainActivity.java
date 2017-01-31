package edu.iupui.elliott.hipsterbands;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*

    This app will ask the user for his/her name and will then calculate a value based on the input.

    In this case, the app will produce a hipster band name.

    The band name is calculated by finding values in set arrays based on the FIRST letter of the user's first name and the THIRD letter of the user's last name.

     */

    private EditText mFirstNameTextView;
    private EditText mLastNameTextView;
    private Button mGenerateButton;
    private TextView mBandNameTextView;
    private BandName mCurrentBandName;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstNameTextView = (EditText) findViewById(R.id.firstName_edittext);
        mLastNameTextView  = (EditText) findViewById(R.id.lastname_edittext);
        mBandNameTextView  = (TextView) findViewById(R.id.bandname_textview);
        mGenerateButton = (Button) findViewById(R.id.generate_button);

        mGenerateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                mCurrentBandName = new BandName(mFirstNameTextView.getText().toString(), mLastNameTextView.getText().toString());
                mBandNameTextView.setText(mCurrentBandName.getBandName());
            }
        });

    }
}
