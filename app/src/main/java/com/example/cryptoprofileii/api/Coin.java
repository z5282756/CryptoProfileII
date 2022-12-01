package com.example.cryptoprofileii.api;

//1.implement Coin java class with  fields, constructors and getter/setter methods (from Moodle)

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Coin {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nameid")
    @Expose
    private String nameid;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("price_usd")
    @Expose
    private String priceUsd;
    @SerializedName("percent_change_24h")
    @Expose
    private String percentChange24h;
    @SerializedName("percent_change_1h")
    @Expose
    private String percentChange1h;
    @SerializedName("percent_change_7d")
    @Expose
    private String percentChange7d;
    @SerializedName("price_btc")
    @Expose
    private String priceBtc;
    @SerializedName("market_cap_usd")
    @Expose
    private String marketCapUsd;
    @SerializedName("volume24")
    @Expose
    private Double volume24;
    @SerializedName("volume24a")
    @Expose
    private Double volume24a;
    @SerializedName("csupply")
    @Expose
    private String csupply;
    @SerializedName("tsupply")
    @Expose
    private String tsupply;
    @SerializedName("msupply")
    @Expose
    private String msupply;

    public Coin(String name, String symbol, String priceUsd, String change1h, String change24h, String change7d, String marketcap, double volume) {
        this.name = name;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
        this.percentChange1h = change1h;
        this.percentChange24h = change24h;
        this.percentChange7d = change7d;
        this.marketCapUsd = marketcap;
        this.volume24 = volume;
    }
    public Coin(String coinName, String priceUsd, String change1h) {
        this.name = name;
        this.priceUsd = priceUsd;
        this.percentChange1h = change1h;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameid() {
        return nameid;
    }

    public void setNameid(String nameid) {
        this.nameid = nameid;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public String getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(String priceBtc) {
        this.priceBtc = priceBtc;
    }

    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(String marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Double getVolume24() {
        return volume24;
    }

    public void setVolume24(Double volume24) {
        this.volume24 = volume24;
    }

    public Double getVolume24a() {
        return volume24a;
    }

    public void setVolume24a(Double volume24a) {
        this.volume24a = volume24a;
    }

    public String getCsupply() {
        return csupply;
    }

    public void setCsupply(String csupply) {
        this.csupply = csupply;
    }

    public String getTsupply() {
        return tsupply;
    }

    public void setTsupply(String tsupply) {
        this.tsupply = tsupply;
    }

    public String getMsupply() {
        return msupply;
    }

    public void setMsupply(String msupply) {
        this.msupply = msupply;
    }

    public static ArrayList<Coin> getCoins() {
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(new Coin("Bitcoin", "BTC", "18943.02", "-5.30", "0.06", "6.25", "734812139048.78", 19044303069.795288));
        coins.add(new Coin("Ethereum", "ETH", "1319.54", "-5.94", "0.00", "14.58", "18116094926.74", 11453091518.956093));
        coins.add(new Coin("XRP", "XRP", "0.489782", "-6.10", "0.36", "8.03", "9975961452.76", 1857161424.9047709));
        coins.add(new Coin("Bitcoin Cash", "BCH", "313.48", "-5.25", "0.31", "25.16", "6061849292.55", 4034762667.1342015));
        coins.add(new Coin("Bitcoin SV", "BCHSV", "84.44", "6.09", "0.98", "71.68", "5003375789.67", 2920688747.7987976));
        coins.add(new Coin("Tether", "USDT", "0.996530", "0.09", "-0.04", "0.29", "4051244046.05",32969047733.32528));
        coins.add(new Coin("Litecoin", "LTC", "110.04", "-6.42", "0.35", "12.84", "3665038765.74", 3433599488.5887113));
        coins.add(new Coin("EOS", "EOS", "2.15", "-7.21", "-0.11", "12.92", "3324669063.56", 3353780327.053705));
        coins.add(new Coin("Binance Coin", "BNB", "373.06", "-5.04", "0.24", "12.51", "2675482775.95", 233309183.3948947));
        coins.add(new Coin("Stellar", "XLM", "0.190500", "-2.09", "1.78", "25.85", "1232939271.42", 502557303.372596));
        return coins;
    }

    //2.Create a method to return the correct coin from the test data ArrayList based on a received coin name
//    public static Coin findCoin(String symbol) {
//        // Implement Gson library to convert JSON string to Java object
//        Gson gson = new Gson();
//        CoinLoreResponse response = gson.fromJson(CoinLoreResponse.jsonString, CoinLoreResponse.class);
//        List<Coin> coins = response.getData();
//
//        for(final Coin coin : coins) {
//            if(coin.getSymbol().toLowerCase().equals(symbol.toLowerCase())) {
//                return coin;
//            }
//        }
//        return null;
//    }

    //3.Design user interface for detail activity (tut wk3 slide 6)

    //4.Display coin data in DetailActivity --> first, Create and initialise variables for each TextView in DetailActivity class


}
