package com.example.beauticianapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import static com.example.beauticianapplication.Utils.*;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    EditText userNameEditText;
    EditText passwordEditText;
    CardView loginButton;
    TextView wrongCredentialsTextView;

    int backButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        wrongCredentialsTextView = findViewById(R.id.wrongCredentialsLabel);

        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

//                userName = "makbule1987.mgkan@gmail.com";
//                password = "12345678";

//                userName = "eylemunlu97@gmail.com";
//                password = "asdadsdadasdasdasd";
                PostApi caller = new PostApi();
                String jsonRequest = "{\n" +
                        "  \"mail\": \"" +userName+"\",\n" +
                        "  \"password\": \"" +password+"\"\n" +
                        "}";

                try {
                    String response = caller.post("https://j15r9zo8a0.execute-api.eu-central-1.amazonaws.com/test/deneme", jsonRequest);

                    if(response.contentEquals("false")) {
                        if(wrongCredentialsTextView.getVisibility() == View.INVISIBLE){
                            wrongCredentialsTextView.setVisibility(View.VISIBLE);
                        }
                        if(wrongCredentialsTextView.getCurrentTextColor() == Color.BLACK){
                            wrongCredentialsTextView.setTextColor(Color.RED);
                        }else{
                            wrongCredentialsTextView.setTextColor(Color.BLACK);
                        }
                    }else{
                        DataHolder holder = DataHolder.getInstance();

                        ArrayList<String> rs = parseMyJson(response);

                        holder.setUserID(Integer.valueOf(rs.get(0)));
                        holder.setUserFullName(rs.get(1) + " " + rs.get(2));
                        holder.setPpLink(rs.get(3));

                        Intent registerIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(registerIntent);
                    }
                }catch (Exception e){
                    System.out.println(e);
                }


            }
        });
    }
}