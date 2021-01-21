package com.example.electronicsshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electronicsshop.models.CartItem;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    DbHelper db;
    RecyclerView mRecyclerView;
    adapterMyItems mAdapter;
    Button shopping;
    Button payment;
    String username;
    int totalprice;
    TextView user;

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
        else {
            Toast.makeText(CartActivity.this, "Not a valid menu", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent incoming = getIntent();

        username = incoming.getStringExtra("username");

        db = new DbHelper(this);
        ArrayList<CartItem> myItems = new ArrayList<>();
        myItems = db.getMyItems(username);
        totalprice = db.getTotalPrice(username);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_my_items);
        shopping = (Button) findViewById(R.id.button_continue_shopping);
        payment = (Button) findViewById(R.id.button_payment);
        user = (TextView) findViewById(R.id.cart_welcome_name);

        user.setText(username);

        // оваа карактеристика може да се користи ако се знае дека промените
        // во содржината нема да ја сменат layout големината на RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // ќе користиме LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // и default animator (без анимации)
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // сетирање на кориснички дефиниран адаптер myAdapter (посебна класа)
        mAdapter = new adapterMyItems(myItems, username, R.layout.my_item_row,this);

        //прикачување на адаптерот на RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalprice < 1) {
                    Toast.makeText(CartActivity.this, "Your Cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent  = new Intent(getApplicationContext(), BuyActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("totalprice", totalprice);
                    startActivity(intent);
                }
            }
        });


    }
}