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
import android.widget.SearchView;

import com.example.cryptoprofileii.api.Coin;
import com.example.cryptoprofileii.api.CoinLoreResponse;
import com.example.cryptoprofileii.api.CoinService;
import com.example.cryptoprofileii.recyclerview_adapter.CoinAdapter;
import com.example.cryptoprofileii.recyclerview_adapter.RecyclerViewInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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


//        // // Implement Gson library to convert JSON string to Java object
//        Gson gson = new Gson();
//        CoinLoreResponse response = gson.fromJson(CoinLoreResponse.jsonString, CoinLoreResponse.class);
//        coinList = response.getData();

        // Create an adapter instance with an empty ArrayList of Coin objects
        adapter = new CoinAdapter(new ArrayList<>(), this);


//        // instantiate the adapter and pass on the list of coins:
//        adapter = new CoinAdapter(coinList, this);

        // Implement Retrofit to make API call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.coinlore.net") // Set the base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create object for the service interface
        CoinService service = retrofit.create(CoinService.class);
        Call<CoinLoreResponse> responseCall = service.getCoins();
        responseCall.enqueue(new Callback<CoinLoreResponse>() {
            @Override
            public void onResponse(Call<CoinLoreResponse> call, Response<CoinLoreResponse> response) {
                Log.d(TAG, "API call successful!");
                List<Coin> coins = response.body().getData();
                // Supply data to the adapter to be displayed
                adapter.setData((ArrayList)coins);
                adapter.sort(CoinAdapter.SORT_METHOD_VALUE);
            }

            @Override
            public void onFailure(Call<CoinLoreResponse> call, Throwable t) {
                Log.d(TAG, "API call failure.");
            }
        });

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