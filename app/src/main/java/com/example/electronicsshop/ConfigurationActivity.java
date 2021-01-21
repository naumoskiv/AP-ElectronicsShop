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

import com.example.electronicsshop.models.PCConfiguration;
import com.example.electronicsshop.models.Product;

public class ConfigurationActivity extends AppCompatActivity {

    TextView configuration_name;
    TextView configuration_cpu;
    TextView configuration_gpu;
    TextView configuration_storage;
    TextView configuration_ram;
    TextView configuration_case;
    TextView configuration_mbu;
    TextView configuration_psu;
    TextView configuration_price;
    ImageView configuration_photo;
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
        setContentView(R.layout.activity_configuration);

        configuration_name = (TextView) findViewById(R.id.configuration_name_given);
        configuration_cpu = (TextView) findViewById(R.id.configuration_cpu_given);
        configuration_gpu = (TextView) findViewById(R.id.configuration_gpu_given);
        configuration_storage = (TextView) findViewById(R.id.configuration_storage_given);
        configuration_ram = (TextView) findViewById(R.id.configuration_ram_given);
        configuration_case = (TextView) findViewById(R.id.configuration_case_given);
        configuration_mbu = (TextView) findViewById(R.id.configuration_mobo_given);
        configuration_psu = (TextView) findViewById(R.id.configuration_psu_given);
        configuration_price = (TextView) findViewById(R.id.configuration_price_given);
        configuration_photo = (ImageView) findViewById(R.id.configuration_image);
        cart = (Button) findViewById(R.id.configuration_button_cart);

        db = new DbHelper(this);
        Intent incoming = getIntent();
        username = incoming.getStringExtra("username");
        product = incoming.getParcelableExtra("product");

        assert product != null;
        String product_name = product.getProductName();

        final PCConfiguration configuration = db.getConfigurationByName(product_name);


        configuration_name.setText(configuration.getConfiguration_name());
        configuration_cpu.setText(configuration.getConfiguration_cpu());
        configuration_gpu.setText(String.valueOf(configuration.getConfiguration_gpu()));
        configuration_storage.setText(configuration.getConfiguration_storage());
        configuration_ram.setText(configuration.getConfiguration_ram());
        configuration_case.setText(configuration.getConfiguration_case());
        configuration_mbu.setText(configuration.getConfiguration_motherboard());
        configuration_psu.setText(configuration.getConfiguration_psu());
        String price = "$" + String.valueOf(configuration.getConfiguration_price());
        configuration_price.setText(price);
        configuration_photo.setImageResource(configuration.getConfiguration_photo());

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getProduct_quantity() - db.getNumBought(product.getProductName()) < 1) {
                    Toast.makeText(ConfigurationActivity.this, "Currently not available", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addToCart(configuration.getConfiguration_name(), configuration.getConfiguration_price(), username);
                    Toast.makeText(ConfigurationActivity.this, "Successfully added to Cart", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(getApplicationContext(), CartActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }
        });
    }
}