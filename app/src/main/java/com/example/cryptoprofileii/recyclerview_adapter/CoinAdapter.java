package com.example.cryptoprofileii.recyclerview_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoprofileii.R;
import com.example.cryptoprofileii.api.Coin;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


// Wk4(1) create new Java class called CoinAdapter + extend RecyclerView.Adapter class
// --> public class CoinAdapter extends RecyclerView.Adapter {...} + implement methods


public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.MyViewHolder> implements Filterable {

    // Wk4(4):
    private List<Coin> mCoins, mCoinsFiltered;
    private RecyclerViewInterface recyclerViewInterface;

    public static final int SORT_METHOD_NAME = 1;
    public static final int SORT_METHOD_VALUE = 2;

    // CoinAdapter constructor method:
    public CoinAdapter(List<Coin> coins, RecyclerViewInterface rvInterface) {
        mCoins = coins;
        mCoinsFiltered = coins;
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
        Coin coin = mCoinsFiltered.get(position);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.mName.setText(coin.getName());
        holder.mValue.setText(formatter.format(Double.valueOf(coin.getPriceUsd())));
        holder.mChange.setText(coin.getPercentChange1h() + " %");
        holder.itemView.setTag((coin.getId()));


    }

    @Override
    public int getItemCount() {
        // return the number of items in the recycler view
        return mCoinsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mCoinsFiltered = mCoins;
                } else {
                    ArrayList<Coin> filteredList = new ArrayList<>();
                    for (Coin coin : mCoins) {
                        if (coin.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(coin);
                        }
                    }
                    mCoinsFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mCoinsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mCoinsFiltered = (ArrayList<Coin>) filterResults.values;
                notifyDataSetChanged();


            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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

    // Use the Java Collections.sort() and Comparator methods to sort the list
    public void sort(final int sortMethod) {
        if (mCoinsFiltered.size() > 0) {
            Collections.sort(mCoinsFiltered, new Comparator<Coin>() {
                @Override
                public int compare(Coin c1, Coin c2) {
                    if (sortMethod == SORT_METHOD_NAME) {
                        // Make the comparison case insensitive
                        return c1.getName().toLowerCase().compareTo(c2.getName().toLowerCase());
                    } else if (sortMethod == SORT_METHOD_VALUE) {
                        return Double.valueOf(c1.getPriceUsd()).compareTo(Double.valueOf(c2.getPriceUsd()));
                    }
                    // by default sort the list by coin name:
                    return c1.getName().compareTo(c2.getName());
                }
            });
        }
        notifyDataSetChanged();
    }

    // Set the supplied data to the adapter
    public void setData(ArrayList<Coin> data) {
        mCoins.clear();
        mCoins.addAll(data);
        notifyDataSetChanged();
    }

}
