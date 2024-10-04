package com.example.a1200546_faris_abufarha_todo1;

import android.os.Bundle;
import android.widget.TextView;

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

    private static final String FULL_NAME = FIRST_NAME + " " + LAST_NAME ;


    private static final String CONGRATULATIONS = "Congratulations!" + FULL_NAME+ "You have guessed the word correctly!";
    private static final String GAME_OVER = "Sorry!" + FULL_NAME + "You Lost the game!";
    private static int MAXIMUM_TRIES = 6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hangman);

        TextView fullNameTextView = findViewById(R.id.textView_fullName);
        TextView idHangmanTextView = findViewById(R.id.textView_hangmanID);

        ArrayList<TextView> hangManTextViewsArrayList = new ArrayList<>(WORD.length());
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanHead));
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanBody));
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanRightArm));
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanLeftArm));
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanRightLeg));
        hangManTextViewsArrayList.add(findViewById(R.id.textView_hangmanLeftLeg));


        showNameID(fullNameTextView, idHangmanTextView);



//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });



    }

    private static void showNameID(TextView fullNameTextView, TextView idHangmanTextView) {
        fullNameTextView.setText(FULL_NAME);
        idHangmanTextView.setText(ID);


    }


    private static void showHangman(ArrayList<TextView> hangManTextViewsArrayList, int tries) {
        for (int i = 0; i < hangManTextViewsArrayList.size(); i++) {
            if (i < tries) {
                hangManTextViewsArrayList.get(i).setAlpha(1); // make the hangman visible

            } else {
                hangManTextViewsArrayList.get(i).setAlpha(0); // make the hangman invisible
            }
        }
    }

    private static void showResult(TextView resultTextView, boolean isWinner) {
        if (isWinner) {
            resultTextView.setText(CONGRATULATIONS);
        } else {
            resultTextView.setText(GAME_OVER);
        }
    }

    private static void restartGame(ArrayList<TextView> hangManTextViewsArrayList, TextView resultTextView, int tries) {
        for (TextView textView : hangManTextViewsArrayList) {
            textView.setAlpha(0);

        }

        tries = 0;
        resultTextView.setText("");
    }




}
//