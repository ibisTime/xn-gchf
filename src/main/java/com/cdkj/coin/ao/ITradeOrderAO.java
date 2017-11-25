package com.cdkj.coin.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.TradeOrder;
import com.cdkj.coin.domain.UserStatistics;
import com.cdkj.coin.dto.res.XN625252Res;

public interface ITradeOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 我要购买开始聊天，创建代下单的订单
    public String contactBuy(String adsCode, String buyUser);

    // 我要出售开始聊天，创建代下单的订单
    public String contactSell(String adsCode, String sellUser);

    // 我要购买
    public String buy(String adsCode, String buyUser, BigDecimal tradePrice,
            BigDecimal count, BigDecimal tradeAmount);

    // 我要出售
    public String sell(String adsCode, String sellUser, BigDecimal tradePrice,
            BigDecimal count, BigDecimal tradeAmount);

    // 取消交易订单
    public void cancel(String code, String updater, String remark);

    // 买家标记打款
    public void markPay(String code, String updater, String remark);

    // 卖家释放
    public TradeOrder release(String code, String updater, String remark);

    // 评论
    public void comment(String code, String userId, String comment);

    // 申请仲裁
    public void applyArbitrate(String code, String applyUser, String reason,
            String attach);

    public Paginable<TradeOrder> queryTradeOrderPage(int start, int limit,
            TradeOrder condition);

    public List<TradeOrder> queryTradeOrderList(TradeOrder condition);

    public TradeOrder getTradeOrder(String code);

    public XN625252Res getTradeOrderCheckInfo(String code);

    // 定时器调用：扫描支付超时订单
    public void doCheckUnpayOrder();

    public UserStatistics userStatisticsInfo(String userId);

    // 获取用户的交易总量
    public BigDecimal getUserTotalTradeCount(String userId);

    // 删除订单，代下单和已取消的交易可删除
    public void dropTradeOrder(String code);

}
