package com.randy.springboot.learn.dto;

public class SearchDto {
    
    private String searchKey;

    private String secondSearchKey;

    public String getSecondSearchKey() {
        return secondSearchKey;
    }

    public void setSecondSearchKey(String secondSearchKey) {
        this.secondSearchKey = secondSearchKey;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    
}
