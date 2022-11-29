package com.example.cryptoprofileii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


//Wk4(2) --> implements RecyclerViewInterface
public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    private static final String TAG = "MainActivity";
    //Wk4(3):
    private RecyclerView mRecyclerView;
    private List<Coin> coinList = new ArrayList<Coin>();
    private CoinAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity launched");


        setTitle("INFS3634 CryptoProfile II");

        // get the handle to the recycler view:
        mRecyclerView = findViewById(R.id.rvList);

        // instantiate a linear recycler view layout manager:
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        coinList = Coin.getCoins();

        // instantiate the adapter and pass on the list of coins:
        adapter = new CoinAdapter(coinList, this);

        // connect the adapter to the recycler view:
        mRecyclerView.setAdapter(adapter);

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

    @Override
    //Instantiate the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "Line 70: Query = "+s);

                adapter.getFilter().filter(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "Line 70: Quer = "+newText);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    // react to user interaction with the menu:
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortName:
                adapter.sort(CoinAdapter.SORT_METHOD_NAME);
                return true;
            case R.id.sortValue:
                adapter.sort(CoinAdapter.SORT_METHOD_VALUE);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }



}