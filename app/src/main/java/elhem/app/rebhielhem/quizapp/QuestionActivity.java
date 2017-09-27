package elhem.app.rebhielhem.quizapp;

/**
 * Created by rebhielhem on 9/5/17.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class QuestionActivity extends Activity implements OnSeekBarChangeListener {
    private static final String TAG= QuestionActivity.class.getName();
    private static ArrayList<Activity> activities=new ArrayList<Activity>();
    List<Question> quesList;
    int score = 0;
    int number=0;
    int qid = 0;
    public static Boolean paused=false;
    private static final int TOTAL_AMOUNT = 10;
    private int[] mAllProgress = { 0, 0, 0};
    Question currentQ;

    Boolean sound=true;
    String rankOne,rankTwo,rankThree ;
    TextView txtQuestion, times, scored,remainCoins;
   Button valid,bOpt1,bOpt2,bOpt3,bOption1,bOption2,bOption3,skip,buttonQues,buttonRemainCoins;
    MediaPlayer mediaPlayer,mediaPlayer2,mediaPlayer3,mediaPlayer4;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activities.add(this);
        setContentView(R.layout.activity_main);



       mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.musicbg);
        mediaPlayer2.isLooping();
       mediaPlayer2.start();

          QuizHelper db = new QuizHelper (this);
          quesList = db.getAllQuestions();



        txtQuestion = (TextView) findViewById(R.id.txtQuestion);

        final SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);

        final SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);


        final SeekBar seekBar3 = (SeekBar) findViewById(R.id.seekBar3);

        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);

       valid = (Button) findViewById(R.id.valid);
        skip=(Button) findViewById(R.id.skip);
        bOpt1=(Button) findViewById(R.id.buttonOp1);
        bOpt2=(Button) findViewById(R.id.buttonOp2);
        bOpt3=(Button) findViewById(R.id.buttonOp3);
        bOption1=(Button) findViewById(R.id.buttonOption1);
        bOption2=(Button) findViewById(R.id.buttonOption2);
        bOption3=(Button) findViewById(R.id.buttonOption3);
        buttonQues=(Button) findViewById(R.id.buttonQues);
buttonRemainCoins=(Button) findViewById(R.id.buttonRemainCoins);



        scored = (TextView) findViewById(R.id.score);



        setQuestionView();


        final ProgressBar mProgressBar;
        final CountDownTimer mCountDownTimer;


        mProgressBar=(ProgressBar) findViewById(R.id.progressbar);

        mCountDownTimer=new CountDownTimer(60000,1000) {
            int i=0;
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);

                i++;




                mProgressBar.setProgress((int)i*100/(60000/1000));

            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(QuestionActivity.this,
                        ResultActivity.class);
                Bundle b = new Bundle();
                b.putInt("score", score); // Your score
                intent.putExtras(b); // Put your score to your next
                startActivity(intent);

                finish();
                i++;
                mProgressBar.setProgress(100);
            }
        };
        mCountDownTimer.start();
        mediaPlayer4 = MediaPlayer.create(getApplicationContext(), R.raw.skipq);
        skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int curr;
               curr=Integer.parseInt(scored.getText().toString());
                curr=curr-5;
                scored.setText(curr+"");


                if(sound)

                mediaPlayer4.start();
                getNextQuestion();


            }
        });
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.right);
        mediaPlayer3 = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
     valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQ.setAsked(true);


                if (Integer.parseInt(buttonRemainCoins.getText().toString()) != 0) {

                    Context context = getApplicationContext();
                    CharSequence text = "You have to spend all your ten certicoins!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                } else {

                    Boolean correctRk1 = false;
                    Boolean correctRk2 = false;
                    Boolean correctRk3 = false;
                    int score1 = 0;
                    int score2 = 0;
                    int score3 = 0;

                    if (bOpt1.getText().toString().equals("1") && bOption1.getText().toString().equals(rankOne))
                        correctRk1 = true;






                    else if (bOpt2.getText().toString().equals("1") && bOption2.getText().toString().equals(rankOne))
                        correctRk1 = true;

                     else if (bOpt3.getText().toString().equals("1") && bOption3.getText().toString().equals(rankOne))
                        correctRk1 = true;



                    if (bOpt1.getText().toString().equals("2") && bOption1.getText().toString().equals(rankTwo))
                        correctRk2 = true;

                     else if (bOpt2.getText().toString().equals("2") && bOption2.getText().toString().equals(rankTwo))
                        correctRk2 = true;

                     else if (bOpt3.getText().toString().equals("2") && bOption3.getText().toString().equals(rankTwo))
                        correctRk2 = true;




                    if (bOpt1.getText().toString().equals("3") && bOption1.getText().toString().equals(rankThree))
                        correctRk3 = true;

                     else if (bOpt2.getText().toString().equals("3") && bOption2.getText().toString().equals(rankThree))
                        correctRk3 = true;

                    else if (bOpt3.getText().toString().equals("3") && bOption3.getText().toString().equals(rankThree))
                        correctRk3 = true;




                    if (correctRk1 && correctRk2 && correctRk3) {


    switch(bOpt1.getText().toString()){

        case "1": score1 =seekBar1.getProgress();
            break;
        case "2": score1 =2*seekBar1.getProgress();
            break;
        case "3": score1=3*seekBar1.getProgress();
            break;


    }
                        switch(bOpt2.getText().toString()){

                            case "1": score2=seekBar2.getProgress();
                                break;
                            case "2": score2=2*seekBar2.getProgress();
                                break;
                            case "3": score2=3*seekBar2.getProgress();
                                break;


                        }
                        switch(bOpt3.getText().toString()){

                            case "1": score3=seekBar3.getProgress();
                                break;
                            case "2": score3=2*seekBar3.getProgress();
                                break;
                            case "3": score3=3*seekBar3.getProgress();
                                break;


                        }

                        score = score+score1+score2 +score3;
                        scored.setText("" + score);

                      if(sound)


                        mediaPlayer.start();
                        getNextQuestion();


                    }
                    else{
if(sound)


                        mediaPlayer3.start();
                        getNextQuestion();


                    }

                    seekBar1.setProgress(0);
                    seekBar2.setProgress(0);
                    seekBar3.setProgress(0);
                    bOpt1.setText("1");
                    bOpt2.setText("2");
                    bOpt3.setText("3");
                    buttonRemainCoins.setText(10+"");
                   // remainCoins.setText(10 + "");
                    //valid.setVisibility(View.INVISIBLE);


                }
            }
        });

       bOpt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String old=bOpt1.getText().toString();
                int oldv=Integer.parseInt(old);
                String old2=bOpt2.getText().toString();
                int oldv2=Integer.parseInt(old2);
                String old3=bOpt3.getText().toString();
                int oldv3=Integer.parseInt(old3);
                if(oldv==1){
                    if(oldv2!=2 && oldv3!=2) bOpt1.setText(String.valueOf(2));
                    else if(oldv2!=3 && oldv3!=3) bOpt1.setText(String.valueOf(3));
                    else {

                        bOpt1.setText(String.valueOf(2));
                        if(oldv2==2){

                            bOpt2.setText(String.valueOf(1));
                            bOpt3.setText(String.valueOf(3));

                        }
                        else if(oldv3==2) {

                            bOpt2.setText(String.valueOf(3));
                            bOpt3.setText(String.valueOf(1));
                        }
                    }
                }
                if(oldv==2){
                    if(oldv2!=3 && oldv3!=3) bOpt1.setText(String.valueOf(3));
                    else if(oldv2!=1 && oldv3!=1) bOpt1.setText(String.valueOf(1));
                    else {
                        bOpt1.setText(String.valueOf(3));
                        if(oldv2==3){
                        bOpt2.setText(String.valueOf(2));
                        bOpt3.setText(String.valueOf(1));}

                        else if(oldv3==3){
                            bOpt2.setText(String.valueOf(1));
                            bOpt3.setText(String.valueOf(2));

                        }

                    }
                }
                if(oldv==3){
                    if(oldv2!=1 && oldv3!=1) bOpt1.setText(String.valueOf(1));
                    else {
                        bOpt1.setText(String.valueOf(1));
                        if(oldv2==1){
                        bOpt2.setText(String.valueOf(3));
                        bOpt3.setText(String.valueOf(2));}
                        else if(oldv3==1){
                            bOpt2.setText(String.valueOf(2));
                            bOpt3.setText(String.valueOf(3));

                        }


                    }
                }
            }
        });



        bOpt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String old=bOpt1.getText().toString();
                int oldv=Integer.parseInt(old);
                String old2=bOpt2.getText().toString();
                int oldv2=Integer.parseInt(old2);
                String old3=bOpt3.getText().toString();
                int oldv3=Integer.parseInt(old3);
                if(oldv2==1){
                    if(oldv!=2 && oldv3!=2) bOpt2.setText(String.valueOf(2));
                    else if(oldv!=3 && oldv3!=3) bOpt2.setText(String.valueOf(3));
                    else {


                        bOpt2.setText(String.valueOf(2));

                       if (oldv==2)      { bOpt1.setText(String.valueOf(1));
                           bOpt3.setText(String.valueOf(3));
                       }
                       else if(oldv3==2){
                           bOpt1.setText(String.valueOf(3));
                           bOpt3.setText(String.valueOf(1));


                       }


                    }
                }

                if(oldv2==2){
                    if(oldv!=3 && oldv3!=3) bOpt2.setText(String.valueOf(3));
                    else if(oldv!=1 && oldv3!=1) bOpt2.setText(String.valueOf(1));
                    else {

                        bOpt2.setText(String.valueOf(3));
                        if(oldv==3){
                            bOpt1.setText(String.valueOf(2));
                            bOpt3.setText(String.valueOf(1));


                        }
                        else if(oldv3==3){
                            bOpt1.setText(String.valueOf(1));
                            bOpt3.setText(String.valueOf(2));


                        }


                    }
                }
                if(oldv2==3){
                    if(oldv!=1 && oldv3!=1) bOpt2.setText(String.valueOf(1));
                    else {

                        bOpt2.setText(String.valueOf(1));
                        if(oldv==1){
                            bOpt1.setText(String.valueOf(3));
                            bOpt3.setText(String.valueOf(2));

                        }
                        else if (oldv3==1){

                            bOpt1.setText(String.valueOf(2));
                            bOpt3.setText(String.valueOf(3));
                        }

                    }
                }
            }
        });

        bOpt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String old=bOpt1.getText().toString();
                int oldv=Integer.parseInt(old);
                String old2=bOpt2.getText().toString();
                int oldv2=Integer.parseInt(old2);
                String old3=bOpt3.getText().toString();
                int oldv3=Integer.parseInt(old3);
                if(oldv3==1){
                    if(oldv!=2 && oldv2!=2) bOpt3.setText(String.valueOf(2));
                    else if(oldv!=3 && oldv2!=3) bOpt3.setText(String.valueOf(3));
                    else {
                        bOpt3.setText(String.valueOf(2));
                        if(oldv==2){
                        bOpt1.setText(String.valueOf(1));
                        bOpt2.setText(String.valueOf(3));}
                        else if(oldv2==2){
                            bOpt1.setText(String.valueOf(3));
                            bOpt2.setText(String.valueOf(1));

                        }


                    }
                }

                if(oldv3==2){
                    if(oldv!=3 && oldv2!=3) bOpt3.setText(String.valueOf(3));
                    else if(oldv!=1 && oldv2!=1) bOpt3.setText(String.valueOf(1));
                    else {
                        bOpt3.setText(String.valueOf(3));
                        if(oldv==3){
                        bOpt1.setText(String.valueOf(2));
                        bOpt2.setText(String.valueOf(1));}
                        else if(oldv2==3){
                            bOpt1.setText(String.valueOf(1));
                            bOpt2.setText(String.valueOf(2));

                        }


                    }
                }
                if(oldv3==3){
                    if(oldv!=1 && oldv2!=1) bOpt3.setText(String.valueOf(1));
                    else {
                        bOpt3.setText(String.valueOf(1));
                        if(oldv==1){

                        bOpt1.setText(String.valueOf(3));
                        bOpt2.setText(String.valueOf(2));}

                        else if(oldv2==1){
                            bOpt1.setText(String.valueOf(2));
                            bOpt2.setText(String.valueOf(3));


                        }


                    }
                }

            }
        });







    }

public void onResume(){
    super.onResume();


}


    public void onPause() {
       super.onPause();
        paused=true;
        mediaPlayer2.reset();
        mediaPlayer2.release();

        finish();


    }
    public void onStop() {
        super.onStop();


        finish();


    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        activities.remove(this);
    }

    public static void finishAll()
    {
        for(Activity activity:activities)
            activity.finish();
    }




    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(QuestionActivity.this,
                AbandonActivity.class);


        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    public void getNextQuestion() {


        if(qid<30) {
            currentQ = quesList.get(qid);
            setQuestionView();


        }
        else{
            qid=0;
            currentQ = quesList.get(qid);
            setQuestionView();



        }







    }



    private void setQuestionView() {
        SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);

        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);

        SeekBar seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar1.setProgress(0);
        seekBar2.setProgress(0);
        seekBar3.setProgress(0);
        bOpt1.setText("1");
        bOpt2.setText("2");
        bOpt3.setText("3");

while(quesList.get(qid).getAsked()){
    qid++;
}

        currentQ = quesList.get(qid);

        rankOne=currentQ.getRANK1();
        rankTwo=currentQ.getRANK2();
        rankThree=currentQ.getRANK3();
        buttonQues.setText(currentQ.getQUESTION());


        bOption1.setText(currentQ.getOPTA());
        bOption2.setText(currentQ.getOPTB());
        bOption3.setText(currentQ.getOPTC());

        qid++;

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {

        int which = whichIsIt(seekBar.getId());
int remainCoin;
        SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);

        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);

        SeekBar seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        remainCoin=Integer.parseInt(buttonRemainCoins.getText().toString());

      remainCoin=10-seekBar1.getProgress()-seekBar2.getProgress()-seekBar3.getProgress();
      buttonRemainCoins.setText(remainCoin+"");





        // the stored progress for this SeekBar
        int storedProgress = mAllProgress[which];
        // we basically have two cases, the user either goes to the left or to
        // the right with the thumb. If he goes to the right we must check to
        // see how much he's allowed to go in that direction(based on the other
        // SeekBar values) and stop him if he the available progress was used. If
        // he goes to the left use that progress as going back
        // and freeing the track isn't a problem.
        if (progress > storedProgress) {
            // how much is currently available based on all SeekBar progress
            int remaining = remaining();

            // if there's no progress remaining then simply set the progress at
            // the stored progress(so the user can't move the thumb further)
            if (remaining == 0) {
                seekBar.setProgress(storedProgress);








            /*    switch (which){
                    case 0:
                        TextView seekBarValue1 = (TextView) findViewById(R.id.seekBar1value);
                        seekBarValue1.setText(String.valueOf(seekBar1.getProgress()));
                    case 1:     TextView seekBarValue2 = (TextView) findViewById(R.id.seekBar2value);
                        seekBarValue2.setText(String.valueOf(seekBar2.getProgress()));
                    case 2:     TextView seekBarValue3= (TextView) findViewById(R.id.seekBar3value);
                        seekBarValue3.setText(String.valueOf(seekBar3.getProgress()));

                }


*/


                return;
            } else {
                // we still have some progress available so check that available
                // progress and let the user move the thumb as long as the
                // progress is at most as the sum between the stored progress
                // and the maximum still available progress
                if (storedProgress + remaining >= progress) {
                    mAllProgress[which] = progress;
                } else {
                    // the current progress is bigger then the available
                    // progress so restrict the value
                    mAllProgress[which] = storedProgress + remaining;
                }
            }
        } else if (progress <= storedProgress) {
            // the user goes left so simply save the new progress(space will be
            // available to other SeekBars)
            mAllProgress[which] = progress;
        }
    }



    private final int remaining() {
        int remaining = TOTAL_AMOUNT;
        for (int i = 0; i < 3; i++) {
            remaining -= mAllProgress[i];
        }
        if (remaining >= 10) {
            remaining = 10;
        } else if (remaining <= 0) {
            remaining = 0;

        }
        return remaining;
    }
    private int whichIsIt(int id) {
        switch (id) {
            case R.id.seekBar1:
                return 0; // first position in mAllProgress
            case R.id.seekBar2:
                return 1;
            case R.id.seekBar3:
                return 2;
            default:
                throw new IllegalStateException(
                        "There should be a Seekbar with this id(" + id + ")!");
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar arg0)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar arg0)
    {

// TODO Auto-generated method stub
    }
public void mute(){
    mediaPlayer2.setVolume(0,0);


}

    public void unmute(){
        mediaPlayer2.setVolume(1,1);

    }

    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            mute();
            sound=false;
        } else {
           unmute();
            sound=true;
        }
    }

}
