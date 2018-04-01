package com.example.nazim.quizapp1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class Score extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent tti =  getIntent();
        String s1 = tti.getStringExtra("tques");
        String s2= tti.getStringExtra("tattampt");
        String s3= tti.getStringExtra("tcorrect");
        String s4= tti.getStringExtra("tincorrect");
        String s5= tti.getStringExtra("tscore");
        String s6= tti.getStringExtra("tpercentage");

        TextView s1txt = (TextView) findViewById(R.id.text3);

        s1txt.setText("Total Questions:" + String.valueOf(s1));
        TextView s2txt=(TextView)findViewById(R.id.textView9);
        s2txt.setText("Attampt Questions:" + String.valueOf(s2));
        TextView s3txt=(TextView)findViewById(R.id.textView10);
        s3txt.setText("Correct Ans:" + String.valueOf(s3));
        TextView s4txt=(TextView)findViewById(R.id.textView11);
        s4txt.setText("Incorrect Ans:" + String.valueOf(s4));
        TextView s5txt=(TextView)findViewById(R.id.textView12);
        s5txt.setText("Score:" + String.valueOf(s5));
        TextView s6txt=(TextView)findViewById(R.id.textView13);
        s6txt.setText("Percentage:" + String.valueOf(s6));
        startActivity(tti);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent it = new Intent(Score.this, MainActivity.class);
                startActivity(it);
                finish();
                Log.i("new game", "i");
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Score Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
