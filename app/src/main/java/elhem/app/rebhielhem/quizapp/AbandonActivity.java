package elhem.app.rebhielhem.quizapp;

/**
 * Created by rebhielhem on 9/5/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class AbandonActivity extends Activity {
    public static boolean replay=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abandon);

        Button play = (Button) findViewById(R.id.playagain);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });



    }

    public void playAgain() {

        Intent intent = new Intent(this, QuestionActivity.class);

        startActivity(intent);

    }





}
