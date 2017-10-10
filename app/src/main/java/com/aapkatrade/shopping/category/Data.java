package com.aapkatrade.shopping.category;

/**
 * Created by Netforce on 7/25/2016.
 */
public class Data
{
     String id,name,category_name,short_description,price,special_price,saving,imageurl;

    Data(String id,String name,String category_name, String short_description, String price,String special_price,String saving,String imageurl)
    {
        this.id=id;
        this.name = name;
        this.category_name = category_name;
        this.short_description = short_description;
        this.price=price;
        this.special_price= special_price;
        this.saving = saving;
        this.imageurl=imageurl;
    }

}
