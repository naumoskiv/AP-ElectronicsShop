package com.example.electronicsshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electronicsshop.models.Product;
import com.example.electronicsshop.models.TV;

public class TvActivity extends AppCompatActivity {

    TextView tv_name;
    TextView tv_os;
    TextView tv_size;
    TextView tv_type;
    TextView tv_speakers;
    TextView tv_price;
    ImageView tv_photo;
    Button cart;

    String username;
    DbHelper db;
    Product product;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.item_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.item_phones) {
            Intent intent = new Intent(this, ProductsActivity.class);
            intent.putExtra("category", "Mobile Phones");
            intent.putExtra("username", username);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.item_tvs) {
            Intent intent = new Intent(this, ProductsActivity.class);
            intent.putExtra("category", "TVs");
            intent.putExtra("username", username);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.item_configurations) {
            Intent intent = new Intent(this, ProductsActivity.class);
            intent.putExtra("category", "PC Configurations");
            intent.putExtra("username", username);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.item_components) {
            Intent intent = new Intent(this, ProductsActivity.class);
            intent.putExtra("category", "PC Components");
            intent.putExtra("username", username);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.item_peripherals) {
            Intent intent = new Intent(this, ProductsActivity.class);
            intent.putExtra("category", "PC Peripherals");
            intent.putExtra("username", username);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.item_laptops) {
            Intent intent = new Intent(this, ProductsActivity.class);
            intent.putExtra("category", "Laptops and Notebooks");
            intent.putExtra("username", username);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        tv_name = (TextView) findViewById(R.id.tv_name_given);
        tv_os = (TextView) findViewById(R.id.tv_os_given);
        tv_size = (TextView) findViewById(R.id.tv_size_given);
        tv_type = (TextView) findViewById(R.id.tv_type_given);
        tv_speakers = (TextView) findViewById(R.id.tv_speakers_given);
        tv_price = (TextView) findViewById(R.id.tv_price_given);
        tv_photo = (ImageView) findViewById(R.id.tv_image);
        cart = (Button) findViewById(R.id.tv_button_cart);

        db = new DbHelper(this);
        Intent incoming = getIntent();
        username = incoming.getStringExtra("username");
        product = incoming.getParcelableExtra("product");

        assert product != null;
        String product_name = product.getProductName();

        final TV tv = db.getTvByName(product_name);

        tv_name.setText(tv.getTv_name());
        tv_os.setText(tv.getTv_os());
        tv_size.setText(String.valueOf(tv.getTv_screen_size()));
        tv_type.setText(tv.getTv_screen_type());
        tv_speakers.setText(tv.getTv_speakers());
        String price = "$" + String.valueOf(tv.getTv_price());
        tv_price.setText(price);
        int image_resource = tv.getTv_photo();
        tv_photo.setImageResource(image_resource);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getProduct_quantity() - db.getNumBought(product.getProductName()) < 1) {
                    Toast.makeText(TvActivity.this, "Currently not available", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addToCart(tv.getTv_name(), tv.getTv_price(), username);
                    Toast.makeText(TvActivity.this, "Successfully added to Cart", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(getApplicationContext(), CartActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }
        });
    }
}