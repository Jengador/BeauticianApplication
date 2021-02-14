package com.example.beauticianapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ChatScreen extends AppCompatActivity {

    ImageView userProfileImageView;
    TextView userNameEditText;

    @Override
    public void onBackPressed() {
        Intent registerIntent = new Intent(ChatScreen.this, MainActivity.class);
        startActivity(registerIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_hr_manager);

        DataHolder dataHolder = DataHolder.getInstance();

        userNameEditText = findViewById(R.id.userFullNameTextView);
        userProfileImageView = findViewById(R.id.userAvatarImageView);

        userNameEditText.setText(dataHolder.getUserFullName());
        Picasso.get().load(dataHolder.getPpLink()).into(userProfileImageView);
    }

}