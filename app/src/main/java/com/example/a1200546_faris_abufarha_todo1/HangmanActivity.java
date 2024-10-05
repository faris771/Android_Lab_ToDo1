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



    private TextView fullNameTextView;
    private TextView idHangmanTextView;
    private TextView progressTextView;
    private ArrayList<TextView> hangManTextViewsArrayList;
    private ArrayList<EditText> guessTextViewsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hangman);


        fullNameTextView = getFullNameTextView();
        idHangmanTextView = getIdHangmanTextView();
        progressTextView = getProgressTextView();
        hangManTextViewsArrayList = getHangmanTextViews();
        guessTextViewsArrayList = getGuessTextViews();


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







    // Function to retrieve TextViews for full name, ID, and progress
    private TextView getFullNameTextView() {
        return findViewById(R.id.textView_fullName);
    }

    private TextView getIdHangmanTextView() {
        return findViewById(R.id.textView_hangmanID);
    }

    private TextView getProgressTextView() {
        return findViewById(R.id.textView_progress);
    }

    // Function to retrieve hangman body parts TextViews
    private ArrayList<TextView> getHangmanTextViews() {
        ArrayList<TextView> hangmanTextViews = new ArrayList<>();
        hangmanTextViews.add(findViewById(R.id.textView_hangmanHead));
        hangmanTextViews.add(findViewById(R.id.textView_hangmanBody));
        hangmanTextViews.add(findViewById(R.id.textView_hangmanRightArm));
        hangmanTextViews.add(findViewById(R.id.textView_hangmanLeftArm));
        hangmanTextViews.add(findViewById(R.id.textView_hangmanRightLeg));
        hangmanTextViews.add(findViewById(R.id.textView_hangmanLeftLeg));
        return hangmanTextViews;
    }

    // Function to retrieve guess EditTexts
    private ArrayList<EditText> getGuessTextViews() {
        ArrayList<EditText> guessTextViews = new ArrayList<>();
        guessTextViews.add(findViewById(R.id.editText_letter0));
        guessTextViews.add(findViewById(R.id.editText_letter1));
        guessTextViews.add(findViewById(R.id.editText_letter2));
        guessTextViews.add(findViewById(R.id.editText_letter3));
        guessTextViews.add(findViewById(R.id.editText_letter4));
        guessTextViews.add(findViewById(R.id.editText_letter5));
        guessTextViews.add(findViewById(R.id.editText_letter6));
        return guessTextViews;
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

        // Check if the submission length matches the word length
        if (submission.length() != WORD.length()) {
            progressTextView.setText("Please enter the full word.");
            return;
        }

        // If the player guessed the correct word
        if (WORD.equals(submission)) {
            progressTextView.setText(CONGRATULATIONS);
            Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
            finish(); // End the activity since the player won
        } else {
            tries++; // Increment the number of wrong tries
            showHangman(hangManTextViewsArrayList, tries); // Show the next part of the hangman

            // If the player has used all their tries
            if (tries >= MAXIMUM_TRIES) {
                progressTextView.setText(GAME_OVER);
                Toast.makeText(this, "You lost! The correct word was: " + WORD, Toast.LENGTH_SHORT).show();
                finish(); // End the activity since the player lost
            } else {
                // Update progress to show remaining tries
                progressTextView.setText("Wrong guess! Tries left: " + (MAXIMUM_TRIES - tries));
            }
        }
    }







    private void checkSubmission(ArrayList<EditText> guessTextViewsArrayList, ArrayList<TextView> hangManTextViewsArrayList, TextView progressTextView, Integer tries) {
        String submission = editTextToString(guessTextViewsArrayList);

        if (submission.length() != 1 || submission.equals("-------")) {
            progressTextView.setText("Please enter a valid guess!");
            return;
        }

        if (WORD.equals(submission)) {
            progressTextView.setText(CONGRATULATIONS);
            Toast.makeText(this, "You guessed the word! Congratulations!", Toast.LENGTH_SHORT).show();
            finish(); // End the program only if the user wins
        } else {
            progressTextView.setText("Try again!");
            tries++;

            // Show the next part of the hangman
            showHangman(hangManTextViewsArrayList, tries);

            // Check if the player has reached the maximum number of tries
            if (tries >= MAXIMUM_TRIES) {
                progressTextView.setText(GAME_OVER);
                Toast.makeText(this, "Game Over! The correct word was: " + WORD, Toast.LENGTH_SHORT).show();
                // Do not call finish() here; let the user restart the game without ending the app
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
