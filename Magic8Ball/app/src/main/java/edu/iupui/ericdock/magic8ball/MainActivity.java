package edu.iupui.ericdock.magic8ball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mResponseButton;
    private TextView mResponseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SET UP 8-BALL RESPONSES
        Response.sAllResponses = new Response[] {new Response("It is certain"),
                new Response("It is decidedly so"),
                new Response("Without a doubt"),
                new Response("Yes, definitely"),
                new Response("You may rely on it"),
                new Response("As I see it, yes"),
                new Response("Most likely"),
                new Response("Outlook good"),
                new Response("Yes"),
                new Response("Signs point to yes"),
                new Response("Reply hazy try again"),
                new Response("Ask again later"),
                new Response("Better not tell you now"),
                new Response("Cannot predict now"),
                new Response("Concentrate and ask again"),
                new Response("Don't count on it"),
                new Response("My reply is no"),
                new Response("My sources say no"),
                new Response("Outlook not so good"),
                new Response("Very doubtful")};

        // LAYOUT WIDGETS
        mResponseTextView = (TextView) findViewById(R.id.response_text_view);

        mResponseButton = (Button) findViewById(R.id.response_button);
        mResponseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // GET RANDOM RESPONSE
                int responseIndex = findRandomResponse();

                // DISPLAY RESPONSE
                mResponseTextView.setText(Response.sAllResponses[responseIndex].getResponseString());
            }
        });

    }

    // GET A RANDOM RESPONSE
    private int findRandomResponse() {
        return new Random().nextInt(Response.sAllResponses.length - 1);
    }

    // GETTERS/SETTERS
    public Button getResponseButton() {
        return mResponseButton;
    }

    public void setResponseButton(Button mResponseButton) {
        this.mResponseButton = mResponseButton;
    }

    public TextView getResponseTextView() {
        return mResponseTextView;
    }

    public void setResponseTextView(TextView mResponseTextView) {
        this.mResponseTextView = mResponseTextView;
    }
}
