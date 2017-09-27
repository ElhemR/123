package elhem.app.rebhielhem.quizapp;

/**
 * Created by rebhielhem on 9/5/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class HowToActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howto_activity);
        Button goback = (Button) findViewById(R.id.goback);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HowToActivity.this,
                       MainActivity.class);


                startActivity(intent);

                finish();
            }
        });
    }


}
