package com.rajendra.foodapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rajendra.foodapp.adapter.AsiaFoodAdapter;
import com.rajendra.foodapp.adapter.PopularFoodAdapter;
import com.rajendra.foodapp.model.AsiaFood;
import com.rajendra.foodapp.model.PopularFood;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView popularRecycler, asiaRecycler;
    PopularFoodAdapter popularFoodAdapter;
    AsiaFoodAdapter asiaFoodAdapter;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.bottom_menu, menu);
        MenuItem mnuLogin = menu.findItem(R.id.mnuLogin);
        MenuItem mnuLogout = menu.findItem(R.id.mnuLogout);
        firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            //show logout and hide login
            mnuLogin.setVisible(false);
            mnuLogout.setVisible(true);
        } else {
            mnuLogin.setVisible(true);
            mnuLogout.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuLogin:
                startActivity(new Intent(MainActivity.this, Login.class));
                break;
            case R.id.mnuLogout:
                //codes to logout
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<PopularFood> popularFoodList = new ArrayList<>();

        popularFoodList.add(new PopularFood("Float Cake Gateaux", "7000RWF", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Chiken Drumstick", "8900RWF", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Fish Tikka Stick", "$6000RWF", R.drawable.popularfood3));
        popularFoodList.add(new PopularFood("Float Cake Vietnam", "7000RWF", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Chiken Drumstick", "9300RWF", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Fish Tikka Stick", "5000RWF", R.drawable.popularfood3));

        setPopularRecycler(popularFoodList);


        List<AsiaFood> asiaFoodList = new ArrayList<>();
        asiaFoodList.add(new AsiaFood("Chicago Pizza", "20000RWF", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("Straberry Cake", "25000RWF", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        asiaFoodList.add(new AsiaFood("Chicago Pizza", "20000RWF", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("Straberry Cake", "25000RWF", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        asiaFoodList.add(new AsiaFood("Chicago Pizza", "20000RWF", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        asiaFoodList.add(new AsiaFood("Straberry Cake", "25000RWF", R.drawable.asiafood2, "4.2", "Friends Restaurant"));

        setAsiaRecycler(asiaFoodList);

    }


    private void setPopularRecycler(List<PopularFood> popularFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setAsiaRecycler(List<AsiaFood> asiaFoodList) {

        asiaRecycler = findViewById(R.id.asia_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        asiaRecycler.setLayoutManager(layoutManager);
        asiaFoodAdapter = new AsiaFoodAdapter(this, asiaFoodList);
        asiaRecycler.setAdapter(asiaFoodAdapter);

    }
}