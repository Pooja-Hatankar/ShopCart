package com.example.shop4shop;

/**
 * Created by LENOVO on 30-11-2017.
 */

public class CartModel {
    int _idCart;
    int _idProduct;
    int _idUSer;
    int _qtyCart;
    ProductModel productModel;

    public CartModel(){}
    public CartModel(int productId,int userId,int quantityCart){
        _idProduct=productId;
        _idUSer=userId;
        _qtyCart=quantityCart;
    }

    public int get_idCart() {
        return _idCart;
    }

    public void set_idCart(int _idCart) {
        this._idCart = _idCart;
    }

    public int get_idProduct() {
        return _idProduct;
    }

    public void set_idProduct(int _idProduct) {
        this._idProduct = _idProduct;
    }

    public int get_idUSer() {
        return _idUSer;
    }

    public void set_idUSer(int _idUSer) {
        this._idUSer = _idUSer;
    }

    public int get_qtyCart() {
        return _qtyCart;
    }

    public void set_qtyCart(int _qtyCart) {
        this._qtyCart = _qtyCart;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }
}
