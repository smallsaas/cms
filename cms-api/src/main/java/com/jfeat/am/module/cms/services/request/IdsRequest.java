package com.jfeat.am.module.cms.services.request;


import java.util.List;


/**
 * Created by Code Generator on 2018-07-11
 */
public class IdsRequest {
        private List<Long> ids;
        private List<Long> stockIds;
        private String stockType;
        private Integer pageSize;
        private Integer pageNum;
        private Integer starValue;

        public List<Long> getStockIds() {
                return stockIds;
        }

        public void setStockIds(List<Long> stockIds) {
                this.stockIds = stockIds;
        }

        public Integer getStarValue() {
                return starValue;
        }

        public void setStarValue(Integer starValue) {
                this.starValue = starValue;
        }

        public String getStockType() {
                return stockType;
        }

        public void setStockType(String stockType) {
                this.stockType = stockType;
        }

        public Integer getPageSize() {
                return pageSize;
        }

        public void setPageSize(Integer pageSize) {
                this.pageSize = pageSize;
        }

        public Integer getPageNum() {
                return pageNum;
        }

        public void setPageNum(Integer pageNum) {
                this.pageNum = pageNum;
        }

        public List<Long> getIds() {
                return ids;
        }
        public void setIds(List<Long> ids) {
                this.ids = ids;
        }

}
