package com.test.android.fastmath;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int [] answerArray={0,0,0,0};
    int correctPosition,a,b,score=0,trials=0;
    boolean gameStatus=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            Random rg = new Random();
            a = rg.nextInt(190) + 10;
            b = rg.nextInt(190) + 10;
            correctPosition = rg.nextInt(4);
            answerArray[correctPosition] = a + b;
            for (int i = 0; i < 4; i++) {
                if (i != correctPosition) {
                    int ran = rg.nextInt(390) + 10;
                    while (ran == a + b)
                        ran = rg.nextInt();
                    answerArray[i] = ran;
                }
            }
            TextView question = (TextView) findViewById(R.id.question);
            question.setText(String.valueOf(a) + " + " + String.valueOf(b));
            Button b1 = (Button) findViewById(R.id.button);
            Button b2 = (Button) findViewById(R.id.button2);
            Button b3 = (Button) findViewById(R.id.button3);
            Button b4 = (Button) findViewById(R.id.button4);
            b1.setText(String.valueOf(answerArray[0]));
            b2.setText(String.valueOf(answerArray[1]));
            b3.setText(String.valueOf(answerArray[2]));
            b4.setText(String.valueOf(answerArray[3]));
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

    }
    public void submitAnswer(View view) {
        if (gameStatus == true) {
            Button button = (Button) view;
            TextView correct=(TextView)findViewById(R.id.correctText);
            int tag = Integer.parseInt(String.valueOf(button.getTag()));
            if (answerArray[tag - 1] == (a + b)) {
                score++;
                correct.setText("Correct");
            }
            else
                correct.setText("Incorrect");
            trials++;
            TextView scoreBoard = (TextView) findViewById(R.id.currentScore);
            scoreBoard.setText(String.valueOf(score) + "/" + String.valueOf(trials));
            Random rg = new Random();
            a = rg.nextInt(190) + 11;
            b = rg.nextInt(190) + 11;
            correctPosition = rg.nextInt(4);
            answerArray[correctPosition] = a + b;
            for (int i = 0; i < 4; i++) {
                if (i != correctPosition) {
                    int ran = rg.nextInt(390) + 10;
                    while (ran == a + b)
                        ran = rg.nextInt();
                    answerArray[i] = ran;
                }
            }
            TextView question = (TextView) findViewById(R.id.question);
            question.setText(String.valueOf(a) + " + " + String.valueOf(b));
            Button b1 = (Button) findViewById(R.id.button);
            Button b2 = (Button) findViewById(R.id.button2);
            Button b3 = (Button) findViewById(R.id.button3);
            Button b4 = (Button) findViewById(R.id.button4);
            b1.setText(String.valueOf(answerArray[0]));
            b2.setText(String.valueOf(answerArray[1]));
            b3.setText(String.valueOf(answerArray[2]));
            b4.setText(String.valueOf(answerArray[3]));
        }
    }
    CountDownTimer countDownTimer;
    public void startButton(View view)
    {
        Button button=(Button)findViewById(R.id.startButton);
        button.setVisibility(View.INVISIBLE);
        final TextView correct=(TextView)findViewById(R.id.correctText);
        final RelativeLayout questionBoard=(RelativeLayout)findViewById(R.id.childLayout);
        final TextView clock=(TextView)findViewById(R.id.clock);
        final TextView scoreBoard=(TextView)findViewById(R.id.currentScore);
        clock.setVisibility(View.VISIBLE);
        scoreBoard.setVisibility(View.VISIBLE);
        questionBoard.setVisibility(View.VISIBLE);
        gameStatus=true;
        final MediaPlayer tick=MediaPlayer.create(this,R.raw.tick);
        final MediaPlayer finsih=MediaPlayer.create(this,R.raw.airhorn);
        TextView gameOver=(TextView)findViewById(R.id.gameOver);
        final TextView result=(TextView)findViewById(R.id.resultScore);
        final RelativeLayout resultBoard=(RelativeLayout)findViewById(R.id.resultBoard);
        countDownTimer=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int sec=(int)millisUntilFinished/1000;
                clock.setText("00:"+String.format("%02d",sec));
                tick.start();
            }

            @Override
            public void onFinish() {
                finsih.start();
                clock.setText("00:00");
                resultBoard.setVisibility(View.VISIBLE);
                result.setText(String.valueOf(score) + "/" + String.valueOf(trials));
                questionBoard.setVisibility(View.INVISIBLE);
                scoreBoard.setVisibility(View.INVISIBLE);
                clock.setVisibility(View.INVISIBLE);
                correct.setVisibility(View.INVISIBLE);
                gameStatus=false;
            }
        };
        countDownTimer.start();
    }
    public void playAgain(View view)
    {
        gameStatus=true;
        score=0;
        trials=0;
        RelativeLayout resultBoard=(RelativeLayout)findViewById(R.id.resultBoard);
        resultBoard.setVisibility(View.INVISIBLE);
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.childLayout);
        relativeLayout.setVisibility(View.VISIBLE);
        TextView scoreBoard=(TextView)findViewById(R.id.currentScore);
        scoreBoard.setVisibility(View.VISIBLE);
        TextView correct=(TextView)findViewById(R.id.correctText);
        correct.setVisibility(View.VISIBLE);
        correct.setText("");
        TextView clock=(TextView)findViewById(R.id.clock);
        clock.setVisibility(View.VISIBLE);
        scoreBoard.setText("0/0");
        countDownTimer.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
