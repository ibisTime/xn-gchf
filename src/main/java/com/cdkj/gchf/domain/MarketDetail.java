package com.cdkj.gchf.domain;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
 * Created by tianlei on 2017/十二月/05.
 */
public class MarketDetail extends ABaseDO {

    private String id;
    private String name;
    private String symbol;
    private String rank;
    private String price_usd;
    private String price_cny;
    private String price_btc;
    private String one_day_volume_usd;
    private String one_day_volume_cny;
    private String market_cap_usd;
    private String available_supply;
    private String total_supply;
    private String percent_change_1h;
    private String percent_change_24h;
    private String percent_change_7d;
    private String last_updated;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_cny() {
        return price_cny;
    }

    public void setPrice_cny(String price_cny) {
        this.price_cny = price_cny;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }

    public String getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(String market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public String getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(String available_supply) {
        this.available_supply = available_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }

    public String getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(String percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(String percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(String percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getOne_day_volume_usd() {
        return one_day_volume_usd;
    }

    public void setOne_day_volume_usd(String one_day_volume_usd) {
        this.one_day_volume_usd = one_day_volume_usd;
    }

    public String getOne_day_volume_cny() {
        return one_day_volume_cny;
    }

    public void setOne_day_volume_cny(String one_day_volume_cny) {
        this.one_day_volume_cny = one_day_volume_cny;
    }
}
