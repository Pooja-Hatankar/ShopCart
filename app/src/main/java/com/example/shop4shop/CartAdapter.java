package com.example.shop4shop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LENOVO on 05-12-2017.
 */
class ViewHolder1{
    TextView pName,pPrice,cartQuantity;
}
public class CartAdapter extends BaseAdapter {
    ArrayList<CartModel> cartModelArrayList;
    Context mContext;
    DatabaseHandler db;



    public CartAdapter(ArrayList<CartModel> cartModelArrayList, Context mContext) {
        this.cartModelArrayList = cartModelArrayList;
        this.mContext = mContext;
        this.db = db;
    }

    @Override
    public int getCount() {
        return cartModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return cartModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder1 viewHolder;
        if(view==null){

            view= LayoutInflater.from(mContext).inflate(R.layout.list_view_cart,viewGroup,false);
            viewHolder=new ViewHolder1();
            viewHolder.pName=view.findViewById(R.id.tv_product);
            viewHolder.pPrice=view.findViewById(R.id.prize_tv_cart_page);
            viewHolder.cartQuantity=view.findViewById(R.id.tv_count);
            view.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder1) view.getTag();
        }

        CartModel cartModel= (CartModel) getItem(i);
        if (cartModel!=null) {
            viewHolder.pName.setText(cartModel.getProductModel().get_pName());
            Log.d("Pname","pname..."+cartModel.getProductModel().get_pName());
            viewHolder.pPrice.setText(cartModel.getProductModel().get_pPrice());
            Log.d("Price","price..."+cartModel.getProductModel().get_pPrice());
            viewHolder.cartQuantity.setText(cartModel.get_qtyCart());
            Log.d("Qty","qty..."+cartModel.getProductModel().get_pCategory());
    }return view;
    }
}
