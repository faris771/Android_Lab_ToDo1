package com.example.a1200546_faris_abufarha_todo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static String firstName;
    public static String lastName;
    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText firstNameEditText = findViewById(R.id.editText_firstName);
        EditText lastNameEditText = findViewById(R.id.editText_lastName);
        EditText idEditText = findViewById(R.id.editText_ID);

        Button submitFormButton = findViewById(R.id.button_submitForm);

        submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName = firstNameEditText.getText().toString().trim();
                lastName = lastNameEditText.getText().toString().trim();
                id = idEditText.getText().toString().trim();

                // Check if any field is empty
                if (firstName.isEmpty() || lastName.isEmpty() || id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                // Check if the ID is a valid integer
                else {
                    try {
                        Integer.parseInt(id); // Try to parse the ID as an integer

                        // If valid, proceed to the HangmanActivity
                        Intent intent = new Intent(MainActivity.this, HangmanActivity.class);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        // If ID is not a valid integer, show an error message
                        Toast.makeText(MainActivity.this, "ID must be a valid integer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
