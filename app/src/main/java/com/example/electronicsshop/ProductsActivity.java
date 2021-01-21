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
import android.widget.Toast;

import com.example.electronicsshop.models.Category;
import com.example.electronicsshop.models.Product;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    adapterProducts mAdapter;
    DbHelper db;
    String username;
    String category_name;

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
            Toast.makeText(ProductsActivity.this, "Not a valid menu", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        db = new DbHelper(this);
        Intent intent = getIntent();
        //final Category category  = intent.getParcelableExtra("category");

        username = intent.getStringExtra("username");
        category_name = intent.getStringExtra("category");

        ArrayList<Product> products = new ArrayList<>();
        products = db.getProductsByCategory(category_name);

        //сетирање на RecyclerView контејнерот
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_product_list);

        // оваа карактеристика може да се користи ако се знае дека промените
        // во содржината нема да ја сменат layout големината на RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // ќе користиме LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // и default animator (без анимации)
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // сетирање на кориснички дефиниран адаптер myAdapter (посебна класа)
        mAdapter = new adapterProducts(products, R.layout.product_row, username, this);

        //прикачување на адаптерот на RecyclerView
        mRecyclerView.setAdapter(mAdapter);

    }
}