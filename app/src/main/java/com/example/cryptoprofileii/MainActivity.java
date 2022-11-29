package com.example.cryptoprofileii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button launchDetailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity launched");

        launchDetailBtn = findViewById(R.id.launchDetailBtn);
        launchDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Button has been clicked");
                launchDetailActivity();
            }
        });

        setTitle("INFS3634 CryptoProfile II");

    }
    // called when user clicks on Launch Detail Activity button:
    public void launchDetailActivity() {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("cryptoprofile", "Watch a video about CryptoCurrency!");

        //6(cont.). Add the coin symbol as an extra message in MainActivity to identify which coin information you want to display
        intent.putExtra("name", "Ethereum");
        //7. DetailActivity --> Read the extra message from explicit intent above and create the correct Coin object using your search method

        startActivity(intent);





    }



}