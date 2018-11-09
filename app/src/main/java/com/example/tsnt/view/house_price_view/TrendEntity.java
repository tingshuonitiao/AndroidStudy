package com.example.tsnt.view.house_price_view;

import java.util.List;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-11-09 15:58
 * @Description:
 */

public class TrendEntity {
    private int type;
    private String name;
    private List<DetailEntity> detail;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DetailEntity> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailEntity> detail) {
        this.detail = detail;
    }

    public static class DetailEntity {
        private String time_str;
        private Long number;

        public String getTime_str() {
            return time_str == null ? "" : time_str;
        }

        public void setTime_str(String time_str) {
            this.time_str = time_str;
        }

        public Long getNumber() {
            return number == null ? -1 : number;
        }

        public void setNumber(Long number) {
            this.number = number;
        }
    }
}
