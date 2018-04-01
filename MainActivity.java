package com.example.nazim.quizapp1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class MainActivity extends AppCompatActivity {
    int timers = 120;
    int attampt = 0;
    int qnumMetod=0;
    public static int correct =0;
    public static int cv=0;
    public static int totalQuestion=10;
    public static int incorrect=0;
    public static double percentage=0;
    public static int score=0;

    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for timer

        final  TextView textView = (TextView) findViewById(R.id.timer);
        Thread t = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timers--;
                                textView.setText(String.valueOf(timers));
                                if(timers==119){
                                    deleteTable();
                                    correct=0;
                                    cv=2;
                                    qnumMetod++;
                                    myQuestion(qnumMetod,cv);

                                }

                                if (timers == 00) {
                                    information();
                                }
                            }
                        });

                    } catch (InterruptedException e) {

                    }
                }
            }
        };
        t.start();


        Button bnext = (Button) findViewById(R.id.next);
        Button bop1 = (Button) findViewById(R.id.button1);
        Button bop2 = (Button) findViewById(R.id.button2);
        Button bop3 = (Button) findViewById(R.id.button3);
        Button bop4 = (Button) findViewById(R.id.button4);
        //for button next

        bnext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cv=0;
                qnumMetod++;
                myQuestion(qnumMetod,cv);

                Log.i("bnext", "i");
            }
        });


        //for button 1
        bop1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("bop1", "i");
                Attampt();
                cv=1;
                qnumMetod++;
                myQuestion(qnumMetod,cv);

                Log.i("bop1", "i");
            }
        });
        //for button 2
        bop2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Attampt();
                cv=2;
                qnumMetod++;
                myQuestion(qnumMetod,cv);

                Log.i("bop2", "i");
            }
        });
        //for button 3
        bop3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Attampt();
                cv=3;
                qnumMetod++;
                myQuestion(qnumMetod,cv);

                Log.i("bop3", "i");
            }
        });

        //for button 4
        bop4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Attampt();
                cv=4;
                qnumMetod++;
                myQuestion(qnumMetod,cv);

                Log.i("bop4", "i");
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //-----------------------------------For Question Attampt Method   Start
    public void Attampt() {
        if(attampt<=10) {
            final TextView attamptView = (TextView) findViewById(R.id.attamptq);
            attampt++;
            attamptView.setText("Attampt Question:" + String.valueOf(attampt));
        }else {
            information();
        }
    }

    //------------------------------------For Information Transfer Main Activity to Score start
    public void information(){
        incorrect=attampt-correct;
        percentage=(double) (100/totalQuestion*correct);
        score =correct*2;

        Intent tti = new Intent(this.getApplicationContext(), Score.class);
        tti.putExtra("tques",String.valueOf(totalQuestion));
        tti.putExtra("tattampt", String.valueOf(attampt));
        tti.putExtra("tcorrect", String.valueOf(correct));
        tti.putExtra("tincorrect", String.valueOf(incorrect));
        tti.putExtra("tscore", String.valueOf(score));
        tti.putExtra("tpercentage", String.valueOf(percentage));
        Log.i("correct", String.valueOf(attampt));
        Score(correct,incorrect,String.valueOf(score),(float)percentage,totalQuestion);
        startActivity(tti);
        finish();
    }
    //------------------------------------------------------for Score Table start
    public void Score(int correct,int incorrect,String score,Float percentage ,int totalQuestion) {
        try{
            SQLiteDatabase db= this.openOrCreateDatabase("db1",MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS score(correct INT,incorrect INT,score VARCHAR,percentage FLOAT,totalQuestion INT)");
            db.execSQL("INSERT INTO  score VALUES ("+correct+","+incorrect+",'"+score+"',"+percentage+","+totalQuestion+")");

        }catch (Exception e){}
        Log.i("Score", "i");
    }
    //------------------------------------------------------for  Score Table end
    //-------------------------------------------------------for  Dlete Data into Score
      public void deleteTable(){
          SQLiteDatabase db= this.openOrCreateDatabase("db1",MODE_PRIVATE,null);
          db.execSQL("DELETE FROM score");
      }
    //--------------------------------------------------------for Delete Data into End
    //-----------------------------------------------MY DATA Base start For Question
    public void myQuestion(int qnum,int k){

        try{


            SQLiteDatabase db= this.openOrCreateDatabase("db1",MODE_PRIVATE,null);

            db.execSQL("CREATE TABLE IF NOT EXISTS myQuestion(id int,qName VARCHAR,op1 VARCHAR,op2 VARCHAR,op3 VARCHAR,op4 VARCHAR,cop INT)");
            db.execSQL("INSERT INTO  myQuestion VALUES(1, 'Q1', 'A1', 'B1', 'C1', 'D1', 2)");
            db.execSQL("INSERT INTO  myQuestion VALUES(2, 'Q2', 'A2', 'B2', 'C2', 'D2', 2)");
            db.execSQL("INSERT INTO  myQuestion VALUES(3, 'Q3', 'A3', 'B3', 'C3', 'D3', 3)");
            db.execSQL("INSERT INTO  myQuestion VALUES(4, 'Q4', 'A4', 'B4', 'C4', 'D4', 4)");
            db.execSQL("INSERT INTO  myQuestion VALUES(5, 'Q5', 'A5', 'B5', 'C5', 'D5', 1)");
            db.execSQL("INSERT INTO  myQuestion VALUES(6, 'Q6', 'A6', 'B6', 'C6', 'D6', 2)");
            db.execSQL("INSERT INTO  myQuestion VALUES(7, 'Q7', 'A7', 'B7', 'C7', 'D7', 3)");
            db.execSQL("INSERT INTO  myQuestion VALUES(8, 'Q8', 'A8', 'B8', 'C8', 'D8', 4)");
            db.execSQL("INSERT INTO  myQuestion VALUES(9, 'Q9', 'A9', 'B9', 'C9', 'D9', 1)");
            db.execSQL("INSERT INTO  myQuestion VALUES(10,'Q10', 'A10', 'B10', 'C10', 'D10', 2)");

            Cursor csr=db.rawQuery("SELECT * FROM myQuestion",null);
            int iid=csr.getColumnIndex("id");
            int iqName=csr.getColumnIndex("qName");
            int iop1=csr.getColumnIndex("op1");
            int iop2=csr.getColumnIndex("op2");
            int iop3=csr.getColumnIndex("op3");
            int iop4=csr.getColumnIndex("op4");
            int icop=csr.getColumnIndex("cop");
            csr.moveToFirst();
            while (csr!=null){
                if(qnum<=10) {
                    if(qnum==Integer.parseInt(csr.getString(iid))) {
                      /*  //------------------------------for output Console Start
                        Log.i("ID", String.valueOf(Integer.parseInt(csr.getString(iid))));
                        Log.i("qName", csr.getString(iqName));
                        Log.i("op1", csr.getString(iop1));
                        Log.i("op2", csr.getString(iop2));
                        Log.i("op3", csr.getString(iop3));
                        Log.i("op4", csr.getString(iop4));
                        //----------------------------------for output console end
                        */
                        final TextView questionView = (TextView) findViewById(R.id.qno);
                        questionView.setText("Question No:" + String.valueOf(Integer.parseInt(csr.getString(iid))));
                        final TextView qView = (TextView) findViewById(R.id.question);
                        qView.setText(csr.getString(iqName));
                        final Button bbop1 = (Button) findViewById(R.id.button1);
                        bbop1.setText(csr.getString(iop1));
                        final Button bbop2 = (Button) findViewById(R.id.button2);
                        bbop2.setText(csr.getString(iop2));
                        final Button bbop3 = (Button) findViewById(R.id.button3);
                        bbop3.setText(csr.getString(iop3));
                        final Button bbop4 = (Button) findViewById(R.id.button4);
                        bbop4.setText( csr.getString(iop4));
                        break;

                    } //end if inner

                } else{
                    //------------------------------------------correct start
                    if(qnum>0){
                        while (csr!=null){

                            if((qnum-1)==Integer.parseInt(csr.getString(iid))) {
                                if(k==Integer.parseInt(csr.getString(icop))) {
                                    correct++;
                                    Log.i("correct", String.valueOf(correct));
                                    break;
                                }

                            } //end if inner


                            csr.moveToNext();
                        }  //while end
                    }
                    //---------------------------------------------correct end

                     //for send information
                    information();
                    Log.i("ELSE BLOCK", "IF");
                    break;
                }
                csr.moveToNext();
            }  //while end
            //------------------------------------------correct start
            if(qnum>0){
                while (csr!=null){

                    if((qnum-1)==Integer.parseInt(csr.getString(iid))) {
                        if(k==Integer.parseInt(csr.getString(icop))) {
                            correct++;
                            Log.i("correct", String.valueOf(correct));
                            break;
                        }

                    } //end if inner


                    csr.moveToNext();
                }  //while end
            }
            //---------------------------------------------correct end

        }catch (Exception e){}

    }
    //------------------------------------------------end Data Base Question

    //------------------------------------For Information Transfer Main Activity to Score endd
    //--------------------------------------MyQuestion Start
    public void mmyQuestion(int qnum,int k) {
        // declaring and initializing Question
        String[][] arr = {
                {"1", "Q1", "A1", "B1", "C1", "D1", "1"},
                {"2", "Q2", "A2", "B2", "C2", "D2", "2"},
                {"3", "Q3", "A3", "B3", "C3", "D3", "3"},
                {"4", "Q4", "A4", "B4", "C4", "D4", "4"},
                {"5", "Q5", "A5", "B5", "C5", "D5", "1"},
                {"6", "Q6", "A6", "B6", "C6", "D6", "2"},
                {"7", "Q7", "A7", "B7", "C7", "D7", "3"},
                {"8", "Q8", "A8", "B8", "C8", "D8", "4"},
                {"9", "Q9", "A9", "B9", "C9", "D9", "1"},
                {"10", "Q10", "A10", "B10", "C10", "D10", "2"}};



        if(qnum<=10){
            // printing 2D array
            for (int i = 0; i <10; i++) {
                for (int j = 0; j < 6; j++) {
                    if (qnum==Integer.parseInt(arr[i][0])) {
                        Log.i("array", String.valueOf(arr[i][1]));
                        Log.i("array", String.valueOf(arr[i][2]));
                        final TextView questionView = (TextView) findViewById(R.id.qno);
                        questionView.setText("Question No:" + String.valueOf(arr[i][0]));
                        final TextView qView = (TextView) findViewById(R.id.question);
                        qView.setText(arr[i][1]);
                        final Button bbop1 = (Button) findViewById(R.id.button1);
                        bbop1.setText(arr[i][2]);
                        final Button bbop2 = (Button) findViewById(R.id.button2);
                        bbop2.setText(arr[i][3]);
                        final Button bbop3 = (Button) findViewById(R.id.button3);
                        bbop3.setText(arr[i][4]);
                        final Button bbop4 = (Button) findViewById(R.id.button4);
                        bbop4.setText(arr[i][5]);
                        break;
                    }
                }

            }
        } else
        {
            Log.i("h", "jk");
            //-----------------------------------------------------
            if(qnum>0){
                for (int i = 0;i <10; i++) {
                    for (int j = 0; j < 6; j++) {
                        if ((qnum-1)==Integer.parseInt(arr[i][0])){
                            if(k==Integer.parseInt(arr[i][6])) {
                                correct++;
                                Log.i("correct", String.valueOf(correct));
                                break;
                            }
                        }

                    }}}
            //-------------------------------------------------------
            information();
        }
        //-------------------------------------------for correct
        if(qnum>0){
            for (int i = 0;i <10; i++) {
                for (int j = 0; j < 6; j++) {
                    if ((qnum-1)==Integer.parseInt(arr[i][0])){
                        if(k==Integer.parseInt(arr[i][6])) {
                            correct++;
                            Log.i("correct", String.valueOf(correct));
                            break;
                        }
                    }

                }}}
        //-------------------------------------------end point
    }
//--------------------------------------------------------------------myquestion end
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
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
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }
//------------------------------------------MyQuestion End

}
