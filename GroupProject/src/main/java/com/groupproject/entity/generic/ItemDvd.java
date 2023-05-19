package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;

import java.util.ArrayList;

public class ItemDvd extends Item{
    //restore
    public ItemDvd(ArrayList<String> infoList){
        super(infoList);
    }

    //copy
    public ItemDvd(String id, String title, int category, int genre, int stock, double price) {
        super(id, title, category, genre, stock, price);
    }

    //get new
    public ItemDvd(String title, int category, int genre, int stock, int year, double price) {
        super(title, category, genre, stock, year, price);
    }
}