package com.wd.entity.json;

import java.util.*;

/**
 * Created by woody on 2017/7/16.
 */
public class City {
    private Integer id;
    private String name;
    private Date startDate;
    private List<Town> towns = new ArrayList<>();
    private Map<String,Town> titleMap = new HashMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }

    public Map<String, Town> getTitleMap() {
        return titleMap;
    }

    public void setTitleMap(Map<String, Town> titleMap) {
        this.titleMap = titleMap;
    }
}
