package com.aapkatrade.shopping.json;

/**
 * Created by asdf on 7/19/2016.
 */
public class Book
{


        //Variables that are in our json

    //Variables that are in our json
    private int ID;
    private String post_title;
    private String post_content;
    private String category;


    //Getters and setters
    public int getBookId()
    {
        return ID;
    }

    public void setBookId(int ID)
    {
        this.ID = ID;
    }

    public String getName()
    {
        return post_title;
    }

    public void setName(String post_title)
    {
        this.post_title = post_title;
    }

    public String getPrice()
    {
        return post_content;
    }

    public void setPrice(String post_content)
    {
        this.post_content = post_content;
    }

    public String getInStock()
    {
        return category;
    }

    public void setInStock(String category)
    {
        this.category = category;
    }

}
