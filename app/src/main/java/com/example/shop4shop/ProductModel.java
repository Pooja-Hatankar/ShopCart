package com.example.shop4shop;

/**
 * Created by LENOVO on 25-11-2017.
 */

public class ProductModel {

    int _pId;
    String _pName;
    int _pPrice;
    String _pCategory;
    public ProductModel(){}
    public int get_pId() {
        return _pId;
    }
    public ProductModel(String pname,int price,String type){
        _pName=pname;
        _pPrice=price;
        _pCategory=type;
    }
    public void set_pId(int _pId) {
        this._pId = _pId;
    }

    public String get_pName() {
        return _pName;
    }

    public void set_pName(String _pName) {
        this._pName = _pName;
    }

    public int get_pPrice() {
        return _pPrice;
    }

    public void set_pPrice(int _pPrice) {
        this._pPrice = _pPrice;
    }

    public String get_pCategory() {
        return _pCategory;
    }

    public void set_pCategory(String _pCategory) {
        this._pCategory = _pCategory;
    }


}
