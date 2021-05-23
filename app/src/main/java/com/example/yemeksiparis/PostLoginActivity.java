package com.example.yemeksiparis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PostLoginActivity extends AppCompatActivity {

    ImageButton imgBut1;
    ImageButton imgBut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);

        imgBut1 = (ImageButton)findViewById(R.id.imgBut1);
        imgBut2 = (ImageButton)findViewById(R.id.imgBut2);

        imgBut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String restaurantName = "chefpizza";
                Intent intentRestaurant1= new Intent(PostLoginActivity.this ,RestaurantActivity.class);
                intentRestaurant1.putExtra("inputRestaurantName", restaurantName);
                startActivity(intentRestaurant1);
            }
        });

        imgBut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String restaurantName = "mrburger";
                Intent intentRestaurant2= new Intent(PostLoginActivity.this ,RestaurantActivity.class);
                intentRestaurant2.putExtra("inputRestaurantName", restaurantName);
                startActivity(intentRestaurant2);

            }
        });
    }
}