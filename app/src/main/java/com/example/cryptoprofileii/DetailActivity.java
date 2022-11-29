package com.example.cryptoprofileii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    private TextView detailActTv;
    private Button playVideoBtn;
    //5. Create and initialise variables for each TextView that holds data:
    private TextView mName;
    private TextView mSymbol;
    private  TextView mValue;
    private TextView mChange1h;
    private TextView mChange24h;
    private TextView mChange7d;
    private TextView mMarketcap;
    private TextView mVolume;
    private ImageView mSearch;

    //6. in MainActivity --> Add the coin symbol as an extra message in MainActivity to identify which coin information you want to display


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //8. Update the TextViews with the data of the Coin Object --> Get handle for view elements
        Log.d(TAG, "DetailActivity launched");
        mName = findViewById(R.id.tvName);
        mSymbol = findViewById(R.id.tvSymbol);
        mValue = findViewById(R.id.tvValue);
        mChange1h = findViewById(R.id.tvChange1);
        mChange24h = findViewById(R.id.tvChange24);
        mChange7d = findViewById(R.id.tvChange7d);
        mMarketcap = findViewById(R.id.tvMarketcap);
        mVolume = findViewById(R.id.tvVolume);
        mSearch = findViewById(R.id.ivSearch);

        Log.d(TAG, "DetailActivity view components linked to XML components");



        // set activity title to Detail Activity
        setTitle("Detail Activity");

        // call the intent from main activity
        Intent intent = getIntent();

        //need to read message from the intent by using the key "cryptoprofile"
        // the message from the intent that we are trying to read is being named msg
        String msg = intent.getStringExtra("Symbol");
        Coin coin = Coin.findCoin(msg);

        // using Log.d to log debug messages
        Log.d(TAG, "DetailActivity loading coin name: " + msg);

        if (coin != null) {
            Log.d(TAG, "Updating TectViews for DetailActivity");
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            mName.setText(coin.getName());
            mSymbol.setText(coin.getSymbol());
            mValue.setText(formatter.format(Double.valueOf(coin.getPriceUsd())));
            mChange1h.setText(coin.getPercentChange1h() + "%");
            mChange24h.setText(coin.getPercentChange24h() + "%");
            mChange7d.setText(coin.getPercentChange7d() + "%");
            mMarketcap.setText(formatter.format(Double.valueOf(coin.getMarketCapUsd())));
            mVolume.setText(formatter.format(coin.getVolume24()));
            mSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchCoin(coin.getSymbol());
                }
            });
            Log.d(TAG, "TextViews updated for DetailActivity");
        }



    }

    // Called when the user clicks on the play video button
    private void playVideo() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Helf3Ku8kho"));
        startActivity(intent);
    }

    //9. Make the search icon trigger an implicit intent to launch a Google search for the cryptocurrency name
    //9(cont.) Called when the user taps the search icon
    public void searchCoin(String symbol) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwiJuZT43NL7AhWG2HMBHRMGC2UQPAgI" + symbol));
        startActivity(intent);
    }

}