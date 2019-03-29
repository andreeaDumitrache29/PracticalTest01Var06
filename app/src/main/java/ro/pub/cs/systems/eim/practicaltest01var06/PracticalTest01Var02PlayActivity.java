package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {

    private EditText guessEditText = null;
    private Button generateButton = null;
    private Button checkButton = null;
    private EditText scoreEditText = null;
    private Button backButton = null;

    private Random rand = new Random();

    private Integer number = -1;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.generate_button:
                    Integer randInt = rand.nextInt(4);
                    guessEditText.setText(randInt.toString());
                    break;
                case R.id.check_button:
                    if(Integer.valueOf(guessEditText.getText().toString()) == number) {
                        Integer score;
                        if(scoreEditText.getText().toString() != null)
                             score = Integer.parseInt(scoreEditText.getText().toString());
                        else
                            score = 0;
                        score++;
                        scoreEditText.setText(score.toString());
                        Log.d(Constants.PROCESSING_THREAD_TAG, number.toString());
                    }
                    break;
                case R.id.back_button:
                    setResult(RESULT_OK, null);
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        guessEditText = (EditText) findViewById(R.id.guess_edit_text);
        generateButton = (Button) findViewById(R.id.generate_button);
        checkButton = (Button) findViewById(R.id.check_button);
        scoreEditText = (EditText) findViewById(R.id.score_edit_text);
        backButton = (Button) findViewById(R.id.back_button);

        scoreEditText.setText("0");
        generateButton.setOnClickListener(buttonClickListener);
        checkButton.setOnClickListener(buttonClickListener);
        backButton.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NUMBER_OF_CLICKS)) {
            number = intent.getIntExtra(Constants.NUMBER_OF_CLICKS, -1);
            Log.d(Constants.PROCESSING_THREAD_TAG, number.toString() + " intent");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(Constants.LEFT_COUNT, scoreEditText.getText().toString());
        savedInstanceState.putString(Constants.RIGHT_COUNT, guessEditText.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
            scoreEditText.setText(savedInstanceState.getString(Constants.LEFT_COUNT));
        }
        if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
            guessEditText.setText(savedInstanceState.getString(Constants.RIGHT_COUNT));
        }
    }
}
