package com.example.yemeksiparis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantActivity extends AppCompatActivity {

    ListView listview;
    TextView tvCart;
    Button btnCheckout;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent = getIntent();
        String input = intent.getStringExtra("inputRestaurantName");

        listview = (ListView) findViewById(R.id.listview);
        tvCart = (TextView) findViewById(R.id.tvCart);
        btnCheckout = (Button) findViewById(R.id.btnCheckout);
        btnClear = (Button) findViewById(R.id.btnClear);

        //FOR VISUAL
        ArrayList<String> alVisRes1 = new ArrayList<>();
        alVisRes1.add("Pepperoni Pizza      25.00TL");
        alVisRes1.add("BBQ Chicken Pizza    29.00TL");
        alVisRes1.add("Gourmet Pizza        36.99TL");
        ArrayList<String> alVisRes2 = new ArrayList<>();
        alVisRes2.add("Mr Burger            26.50TL");
        alVisRes2.add("Junior Burger        22.50TL");
        alVisRes2.add("Double Mr Burger     45.50TL");
        alVisRes2.add("Mr Chicken Burger    24.50TL");

        //INTERNAL LIST
        ArrayList<MenuItem> menuRes1 = new ArrayList<>();
        menuRes1.add(new MenuItem("Pepperoni Pizza", 25.00));
        menuRes1.add(new MenuItem("BBQ Chicken Pizza", 29.00));
        menuRes1.add(new MenuItem("Gourmet Pizza", 36.99));
        ArrayList<MenuItem> menuRes2 = new ArrayList<>();
        menuRes2.add(new MenuItem("Mr Burger", 26.50));
        menuRes2.add(new MenuItem("Double Mr Burger", 22.50));
        menuRes2.add(new MenuItem("Gourmet Pizza", 45.50));
        menuRes2.add(new MenuItem("Mr Chicken Burger", 24.50));

        //CART
        Cart cart = new Cart();

        if (input.equals("chefpizza")) {
            ArrayAdapter arrayadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alVisRes1);
            listview.setAdapter(arrayadapter);
        } else if (input.equals("mrburger")) {
            ArrayAdapter arrayadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alVisRes2);
            listview.setAdapter(arrayadapter);
        } else {
            Toast.makeText(this, "ERROR, no restaurant selected!", Toast.LENGTH_SHORT).show();
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position is what you're looking for
                if (input.equals("chefpizza")) {
                    cart.addToCart(menuRes1.get(position));
                    tvCart.setText("Sepet Tutarı: " + cart.calculateTotal());
                } else if (input.equals("mrburger")) {
                    cart.addToCart(menuRes2.get(position));
                    tvCart.setText("Sepet Tutarı: " + cart.calculateTotal());
                } else {
                    Toast.makeText(RestaurantActivity.this, "ERROR, no restaurant selected!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.clearCart();
                tvCart.setText("Sepet Tutarı: " + cart.calculateTotal());
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double cartTotal = cart.calculateTotal();
                if (cartTotal > 0.0) {
                    Intent intentCheckout = new Intent(RestaurantActivity.this,CheckoutActivity.class);
                    intentCheckout.putExtra("inputCartTotal", cartTotal);
                    startActivity(intentCheckout);
                } else {
                    Toast.makeText(RestaurantActivity.this, "Sepetiniz boş!", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        
    }
}