package com.cdkj.gchf.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;

public class Market extends ABaseDO implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String coin;

    private String referCurrency;

    private String origin;

    private BigDecimal lastPrice;

    private BigDecimal bid;

    private BigDecimal ask;

    private BigDecimal mid;

    private BigDecimal low;

    private BigDecimal high;

    private String volume;

    private Date updateDatetime;

    private List<String> coinList;

    public List<String> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<String> coinList) {
        this.coinList = coinList;
    }

    private static final long serialVersionUID = 1L;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin == null ? null : coin.trim();
    }

    public String getReferCurrency() {
        return referCurrency;
    }

    public void setReferCurrency(String referCurrency) {
        this.referCurrency = referCurrency == null ? null : referCurrency.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume == null ? null : volume.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}