package elhem.app.rebhielhem.quizapp;

/**
 * Created by rebhielhem on 9/5/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
Button start,help;
    TextView high; boolean shouldExecuteOnResume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);



        start = (Button) findViewById(R.id.play);
       help = (Button) findViewById(R.id.howtoplay);

        high = (TextView) findViewById(R.id.highScore);

        SharedPreferences prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);

        int oldScore = prefs.getInt("key", 0);
        high.setText(""+oldScore);


        shouldExecuteOnResume = false;
        //     SharedPreferences.Editor edit = prefs.edit();
        //       edit.putInt("lang", j);
        //edit.commit();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        QuestionActivity.class);


                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();


            }


        });



      help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        HowToActivity.class);


                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();


            }


        });

    }

@Override
        public void onResume(){
            super.onResume();
            if(QuestionActivity.paused){
              setContentView(R.layout.activity_abandon);
                Button playagain= (Button) findViewById(R.id.playagain);
                playagain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentj = new Intent(MainActivity.this,
                                QuestionActivity.class);
                        startActivity(intentj);

                    }
                });


            }
        }


}














