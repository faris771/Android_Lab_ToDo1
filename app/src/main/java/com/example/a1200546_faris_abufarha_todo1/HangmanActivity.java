package com.example.a1200546_faris_abufarha_todo1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HangmanActivity extends AppCompatActivity {

    private static final String FIRST_NAME = MainActivity.firstName;
    private static final String LAST_NAME = MainActivity.lastName;
    private static final String ID = MainActivity.id;
    private static final String WORD = "birzeit";

    private static final String FULL_NAME = FIRST_NAME + " " + LAST_NAME;

    private static final String CONGRATULATIONS = "Congratulations! " + FULL_NAME + ", You have guessed the word correctly!";
    private static final String GAME_OVER = "Sorry! " + FULL_NAME + ", You lost the game!";
    private static final int MAXIMUM_TRIES = 6;

    private int tries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hangman);

        TextView fullNameTextView = findViewById(R.id.textView_fullName);
        TextView idHangmanTextView = findViewById(R.id.textView_hangmanID);
        TextView progressTextView = findViewById(R.id.textView_progress);

        ArrayList<TextView> hangManTextViewsArrayList = new ArrayList<>();
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanHead));
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanBody));
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanRightArm));
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanLeftArm));
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanRightLeg));
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanLeftLeg));

        ArrayList<EditText> guessTextViewsArrayList = new ArrayList<>();
        guessTextViewsArrayList.add(findViewById(R.id.editText_letter0));
        guessTextViewsArrayList.add(findViewById(R.id.editText_letter1));
        guessTextViewsArrayList.add(findViewById(R.id.editText_letter2));
        guessTextViewsArrayList.add(findViewById(R.id.editText_letter3));
        guessTextViewsArrayList.add(findViewById(R.id.editText_letter4));
        guessTextViewsArrayList.add(findViewById(R.id.editText_letter5));
        guessTextViewsArrayList.add(findViewById(R.id.editText_letter6));

        startGame(hangManTextViewsArrayList);
        showNameID(fullNameTextView, idHangmanTextView);
        showHangman(hangManTextViewsArrayList, tries);

        Button submitButton = findViewById(R.id.button_submitGuess);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSubmission(guessTextViewsArrayList, hangManTextViewsArrayList, progressTextView);
            }
        });

        Button restartButton = findViewById(R.id.button_restartGame);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame(hangManTextViewsArrayList);
            }
        });
    }









    private static void showNameID(TextView fullNameTextView, TextView idHangmanTextView) {
        fullNameTextView.setText(FULL_NAME);
        idHangmanTextView.setText(ID);
    }

    private static void showHangman(ArrayList<TextView> hangManTextViewsArrayList, int tries) {
        for (int i = 0; i < hangManTextViewsArrayList.size(); i++) {
            if (i < tries) {
                hangManTextViewsArrayList.get(i).setAlpha(1); // make the hangman part visible
            } else {
                hangManTextViewsArrayList.get(i).setAlpha(0); // make the hangman part invisible
            }
        }
    }

    private void startGame(ArrayList<TextView> hangManTextViewsArrayList) {
        tries = 0;
        showHangman(hangManTextViewsArrayList, tries);
        Toast.makeText(this, "Guess the word!", Toast.LENGTH_SHORT).show();
    }

    private void restartGame(ArrayList<TextView> hangManTextViewsArrayList) {
        tries = 0;
        showHangman(hangManTextViewsArrayList, tries);

        for (TextView textView : hangManTextViewsArrayList) {
            textView.setAlpha(0);
        }

        Toast.makeText(this, "The game has been restarted", Toast.LENGTH_SHORT).show();
    }
    private void checkSubmission(ArrayList<EditText> guessTextViewsArrayList, ArrayList<TextView> hangManTextViewsArrayList, TextView progressTextView) {
        String submission = editTextToString(guessTextViewsArrayList).toLowerCase();

        if (submission.length() != WORD.length()) {
            progressTextView.setText("Please enter the full word.");
            return;
        }

        if (WORD.equals(submission)) {
            progressTextView.setText(CONGRATULATIONS);
            Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        } else {
            tries++;

            showHangman(hangManTextViewsArrayList, tries);

            if (tries >= MAXIMUM_TRIES) {
                progressTextView.setText(GAME_OVER);
                Toast.makeText(this, "You lost!", Toast.LENGTH_SHORT).show();
            } else {
                progressTextView.setText("Wrong guess! Tries left: " + (MAXIMUM_TRIES - tries));
            }
        }
    }


    private String editTextToString(ArrayList<EditText> guessTextViewsArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (EditText editText : guessTextViewsArrayList) {
            stringBuilder.append(editText.getText().toString());
        }
        return stringBuilder.toString();
    }
}
