package com.mjc.school.controller.menu;

public enum MenuOptions {
    GET_ALL_NEWS("1", "Get all news."),
    GET_NEWS_BY_ID("2", "Get news by id."),
    CREATE_NEWS("3", "Create news."),
    UPDATE_NEWS("4", "Update news."),
    REMOVE_NEWS_BY_ID("5", "Remove news by id."),
    EXIT("0", "Exit.");

    private String optionCode;
    private String optionName;

    MenuOptions() {
    }

    MenuOptions(String optionCode, String optionName) {
        this.optionCode = optionCode;
        this.optionName = optionName;
    }

    public String getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
