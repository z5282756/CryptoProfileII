package com.example.cryptoprofileii;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

// Wk4(1) create new Java class called CoinAdapter + extend RecyclerView.Adapter class
// --> public class CoinAdapter extends RecyclerView.Adapter {...} + implement methods


public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.MyViewHolder> {
    // Wk4(4):
    private List<Coin> mCoins;
    private RecyclerViewInterface recyclerViewInterface;

    // CoinAdapter constructor method:
    public CoinAdapter(List<Coin> coins, RecyclerViewInterface rvInterface) {
        mCoins = coins;
        recyclerViewInterface = rvInterface;
    }

    // this is where you inflate the layout (giving a look to our rows)
    @NonNull
    @Override
    public CoinAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate layout:
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinAdapter.MyViewHolder holder, int position) {
        // assign value to each row in the recyclerview
        // based on the position of the recycler view item
        Coin coin = mCoins.get(position);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.mName.setText(coin.getName());
        holder.mValue.setText(formatter.format(Double.valueOf(coin.getPriceUsd())));
        holder.mChange.setText(coin.getPercentChange1h() + " %");
        holder.itemView.setTag((coin.getSymbol()));


    }

    @Override
    public int getItemCount() {
        // return the number of items in the recycler view
        return mCoins.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // find handle to the views items from list_row.xml layout
        TextView mName, mValue, mChange;
        ImageView mImage;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            mImage = itemView.findViewById(R.id.ivCryptoImage);
            mName = itemView.findViewById(R.id.tvNameInd);
            mValue = itemView.findViewById(R.id.tvValueInd);
            mChange = itemView.findViewById(R.id.tvChange);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        recyclerViewInterface.onItemClick((String) itemView.getTag());
                    }
                }
            });
        }
    }



}
