package com.example.cryptoprofileii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//Wk4(2) --> implements RecyclerViewInterface
public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    private static final String TAG = "MainActivity";
    private Button launchDetailBtn;
    //Wk4(3):
    private RecyclerView mrecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity launched");


        setTitle("INFS3634 CryptoProfile II");

        // get the handle to the recycler view:
        mrecyclerView = findViewById(R.id.rvList);

        // instantiate a linear recycler view layout manager:
        layoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(layoutManager);

        // instantiate the adapter and pass on the list of coins:
        adapter = new CoinAdapter(Coin.getCoins(), this);

        // connect the adapter to the recycler view:
        mrecyclerView.setAdapter(adapter);

    }
    // called when user clicks on Launch Detail Activity button:
    public void launchDetailActivity(String symbol) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("Symbol", symbol);
        startActivity(intent);

    }

    @Override
    public void onItemClick(String symbol) {
        launchDetailActivity(symbol);
    }



}