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
import android.widget.TextView;
import android.widget.Toast;

import com.example.electronicsshop.models.Category;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    adapterCategories mAdapter;
    DbHelper db;
    String username;
    //TextView sliki;

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
            Toast.makeText(HomeActivity.this, "Not a valid menu", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new DbHelper(this);
        Intent incoming = getIntent();
        username = incoming.getStringExtra("username");

        ArrayList<Category> categories = new ArrayList<>();
        categories = db.getCategories();

/*        sliki = (TextView) findViewById(R.id.sliki);
        String vrednosti = String.valueOf(R.drawable.aoc) + " "
                + String.valueOf(R.drawable.apex7) + " "
                +String.valueOf(R.drawable.b550) + " "
                +String.valueOf(R.drawable.bqpsu) + " "
                +String.valueOf(R.drawable.cloud2) + " "
                +String.valueOf(R.drawable.dell) + " "
                +String.valueOf(R.drawable.gamerelite) + " "
                +String.valueOf(R.drawable.gamerpro) + " "
                +String.valueOf(R.drawable.gigabytemonitor) + " "
                +String.valueOf(R.drawable.gprox) + " "
                +String.valueOf(R.drawable.h510) + " "
                +String.valueOf(R.drawable.h510elite) + " "
                +String.valueOf(R.drawable.haste) + " "
                +String.valueOf(R.drawable.hp) + " "
                +String.valueOf(R.drawable.intel10600k) + " "
                +String.valueOf(R.drawable.iphone12) + " "
                +String.valueOf(R.drawable.iphone12mini) + " "
                +String.valueOf(R.drawable.iphone12pro) + " "
                +String.valueOf(R.drawable.iphone12promax) + " "
                +String.valueOf(R.drawable.iphonese) + " "
                +String.valueOf(R.drawable.kingston) + " "
                +String.valueOf(R.drawable.lg32) + " "
                +String.valueOf(R.drawable.lgmonitor) + " "
                +String.valueOf(R.drawable.macbookair) + " "
                +String.valueOf(R.drawable.macbookpro) + " "
                +String.valueOf(R.drawable.mi11) + " "
                +String.valueOf(R.drawable.modeld) + " "
                +String.valueOf(R.drawable.modeldminus) + " "
                +String.valueOf(R.drawable.modelo) + " "
                +String.valueOf(R.drawable.modelominus) + " "
                +String.valueOf(R.drawable.modelowireless) + " "
                +String.valueOf(R.drawable.originscore) + " "
                +String.valueOf(R.drawable.pcbasic) + " "
                +String.valueOf(R.drawable.pcbasic) + " "
                +String.valueOf(R.drawable.predatorrgb) + " "
                +String.valueOf(R.drawable.rtx3060) + " "
                +String.valueOf(R.drawable.rtx3060ti) + " "
                +String.valueOf(R.drawable.rx5500) + " "
                +String.valueOf(R.drawable.ryzen3600) + " "
                +String.valueOf(R.drawable.ryzen5600x) + " "
                +String.valueOf(R.drawable.ryzen5800x) + " "
                +String.valueOf(R.drawable.s21) + " "
                +String.valueOf(R.drawable.s21plus) + " "
                +String.valueOf(R.drawable.s21ultra) + " "
                +String.valueOf(R.drawable.samsung43) + " "
                +String.valueOf(R.drawable.samsung65) + " "
                +String.valueOf(R.drawable.samsungssd) + " "
                +String.valueOf(R.drawable.toshiba) + " "
                +String.valueOf(R.drawable.tridentz) + " "
                +String.valueOf(R.drawable.zephyrus);
        sliki.setText(vrednosti);*/


                //сетирање на RecyclerView контејнерот
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);

        // оваа карактеристика може да се користи ако се знае дека промените
        // во содржината нема да ја сменат layout големината на RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // ќе користиме LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // и default animator (без анимации)
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // сетирање на кориснички дефиниран адаптер myAdapter (посебна класа)
        mAdapter = new adapterCategories(categories, R.layout.category_row, username, this);

        //прикачување на адаптерот на RecyclerView
        mRecyclerView.setAdapter(mAdapter);

    }


}