package com.cdkj.gchf.common;

import java.math.BigDecimal;

import com.cdkj.gchf.core.CalculationUtil;

public class AmountUtil {
    public static Long mul(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return a.multiply(b).longValue();
    }

    public static BigDecimal mul(BigDecimal amount, double number) {
        BigDecimal a = amount;
        BigDecimal b = new BigDecimal(Double.toString(number));
        return a.multiply(b);
    }

    public static double div(Double amount, Long number) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(number));
        return a.divide(b).doubleValue();
    }

    // 保留两位小数，末尾数不管是几，前一位都加1
    public static Long eraseLiUp(Long amount) {
        String amountString = CalculationUtil.diviUp(amount);
        return Long.valueOf(CalculationUtil.multUp(amountString));
    }

    // 保留两位小数，末尾数不管是几，前一位都加1
    public static Long eraseLiDown(Long amount) {
        String amountString = CalculationUtil.diviDown(amount);
        return Long.valueOf(CalculationUtil.multDown(amountString));
    }

    public static void main(String[] args) {
        System.out.println(AmountUtil.mul(1000L, 2.0));
    }
}
