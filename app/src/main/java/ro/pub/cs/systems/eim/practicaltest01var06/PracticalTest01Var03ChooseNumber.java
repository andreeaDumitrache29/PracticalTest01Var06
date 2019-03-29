package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var03ChooseNumber extends AppCompatActivity {

    private Button playButton = null;
    private EditText inputEditText = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.play_button:
                    if(inputEditText.getText().toString() != null && inputEditText.getText().toString() != "") {
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
                        Log.d(Constants.PROCESSING_THREAD_TAG, inputEditText.getText().toString());
                        intent.putExtra(Constants.NUMBER_OF_CLICKS, Integer.valueOf(inputEditText.getText().toString()));
                        startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);

                        Intent intent2 = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
                        getApplicationContext().startService(intent2);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_choose_number);

        playButton = (Button) findViewById(R.id.play_button);
        inputEditText = (EditText) findViewById(R.id.input_edit_text);

        playButton.setOnClickListener(buttonClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(Constants.LEFT_COUNT, inputEditText.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
            inputEditText.setText(savedInstanceState.getString(Constants.LEFT_COUNT));
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var06Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
