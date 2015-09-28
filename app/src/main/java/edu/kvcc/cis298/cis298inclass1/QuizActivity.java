package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    private Button mNextButton;
    private  TextView mQuestionTextView;
                            // typically this array would be created and stored elsewhere (later!)
                    // this calls Question constructor many times & creates array of Question objects
                    // could do elsewhere..will eventually
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
   };

    private  int mCurrentIndex = 0;

    // chpt 3 - adding TAG reference for logging Activities as they occur:
    private static final String TAG = "QuizActivity";   // final = CONSTANTS & Static string to use for the override methods

    // b/c in 2 places (onCreate and OnClickListener - make a private updateQuestion method to
    //
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();  // g
        mQuestionTextView.setText(question);
    }

private void  checkAnswer(boolean userPressedTrue) {
    boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

    int messageResId = 0;

    if (userPressedTrue == answerIsTrue) {
        messageResId = R.string.correct_toast;
    }
    else
    {
        messageResId = R.string.incorrect_toast;
    }
 Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");          // ch 3  LOG display w/ onCreate()
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
   //     int question = mQuestionBank[mCurrentIndex].getTextResId();
   //     mQuestionTextView.setText(question);


        mTrueButton = (Button) findViewById(R.id.true_button);
       mTrueButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
   //            Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();

               checkAnswer(true);
           }
       });


        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() // we write the things to override   View class has OnClickListener and returns an instance of OnClickListener
                                                                    // this allows us to not create our own....
            //View.OnClickListener() creates a instnace of on click listener...with an instance of itself
            //   and then override the onClick method within the OnClickListener
        {
            @Override
            public void onClick(View v) {   // this doesn't do anything yet... so passing in googles class and overriding what you want
           // now to do something:
  //              Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();

                checkAnswer(false);
            }
        } );


        // gets refernce to the button and then sets a View.OnClickListener on it... the listener will
        // increment the index and update the TextView;s text.
        mNextButton = (Button)  findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
           public  void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
  //              int question = mQuestionBank[mCurrentIndex].getTextResId();
  //              mQuestionTextView.setText(question);
                updateQuestion();               // called at end of onClick(
            }
                   });
// ********************* this is the challengee form end of chpt 2 ********works!
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        } );

            updateQuestion();               // called at end of OnCreate(    ;
    }

    // ***************** LOG METHODS ADDED (ch 3 pg 59) ******************
    //          ORDER IS NOT VERY IMPORTANT BUT CALLING SUPER BEFORE LOG IS VERY IMPORTANT


    // below are main

     @Override
    public  void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");   // TAG is constant field defined at top of code
    }

    @Override                      // USED TO ENSURE THAT  CLASS HAS THE METHOD YOU ARE OVERRIDING
    public  void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");      // d for debug  (e for erros) etc...
    }

    @Override
    public  void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");   // if Log not happy - ALT + ENTER will auto import the log class
    }

    @Override
    public  void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public  void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    // ************  END OF LOG STATEMENTS *******************

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
