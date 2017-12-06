package com.example.shop4shop;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by LENOVO on 28-11-2017.
 */
class ViewHolder{
    TextView pName,pPrice;
    Button pButton;
}

public class ProductAdapter extends BaseAdapter {
   private ArrayList<ProductModel> modelArrayList;
   private Context mContext;
   SharedPreferences sharedPreferences;
   SharedPreferences.Editor editor;
    DatabaseHandler db;
    OnSelectionListener onSelectionListener;

    int uid;
    ProductAdapter(Context context,ArrayList<ProductModel> data)
    {
        mContext=context;
        modelArrayList=data;
        db=new DatabaseHandler(mContext);
        this.sharedPreferences=context.getSharedPreferences("MyPref",0);
        this.editor=sharedPreferences.edit();

    }




    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return modelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){

            view= LayoutInflater.from(mContext).inflate(R.layout.list_view_product_add,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.pName=view.findViewById(R.id.tv_product);
            viewHolder.pPrice=view.findViewById(R.id.tv_cost);
            viewHolder.pButton=view.findViewById(R.id.btn_addtoCart);
            view.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) view.getTag();
        }
        final ProductModel productModel= (ProductModel) getItem(i);
        if (productModel!=null) {
            viewHolder.pName.setText(productModel.get_pName());
            viewHolder.pPrice.setText(""+productModel.get_pPrice());
            viewHolder.pButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductModel productModel1=(ProductModel) getItem(i);
                    CartModel cartModel=new CartModel();
                    int produtId=productModel1.get_pId();
                    Log.d("PID","product id..."+produtId);

                    int qty=1;
                    int uid=sharedPreferences.getInt("UID",0);
                    cartModel.set_idProduct(produtId);
                    cartModel.set_qtyCart(qty);
                    cartModel.set_idUSer(uid);
                 boolean b= db.addToCart(cartModel);
                    Log.d("Status:","cart status "+b);



                }
            });
        }
        return view;
    }
    public void setOnCartListener(OnSelectionListener onSelectionListener){
        this.onSelectionListener=onSelectionListener;
    }
}
