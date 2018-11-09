package com.example.tsnt.view.house_price_view;

import java.util.List;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-11-09 16:16
 * @Description:
 */

public class HousePriceEntity {
    private List<TrendEntity> trends;

    public List<TrendEntity> getTrend() {
        return trends;
    }

    public void setTrend(List<TrendEntity> trend) {
        this.trends = trend;
    }
}
