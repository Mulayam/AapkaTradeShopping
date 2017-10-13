package com.aapkatrade.shopping.dashboard.navigation;

/**
 * Created by PPC16 on 10/13/2017.
 */

public class Category_data {

    String id, name;

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

    Category_data(String id , String name)
    {
        this.id= id;
        this.name = name;
    }



}
