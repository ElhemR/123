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
import android.widget.TextView;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textResult = (TextView) findViewById(R.id.textResult);
        TextView textHS = (TextView) findViewById(R.id.beatHS);
        Bundle b = getIntent().getExtras();

        int score = b.getInt("score");
        SharedPreferences prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        CharSequence text="";
        int oldScore = prefs.getInt("key", 0);
        if(score > oldScore ){

            SharedPreferences.Editor edit = prefs.edit();
            edit.putInt("key", score);
            edit.commit();


            textHS.setVisibility(View.VISIBLE);

        }

        textResult.setText( score +" "+text.toString());

    }

    public void playagain(View o) {

        Intent intent = new Intent(this, QuestionActivity.class);

        startActivity(intent);

    }
}
