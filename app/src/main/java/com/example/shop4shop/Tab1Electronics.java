package com.example.shop4shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by LENOVO on 29-11-2017.
 */

public class Tab1Electronics extends Fragment {
    ListView listView ;
    ProductAdapter productAdapter;
    DatabaseHandler db;
    ArrayList<ProductModel> mProductList;
    int num;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        listDisplay();
    }
    private void initView(){
        listView=getView().findViewById(R.id.listView_fragment1);
        mProductList=new ArrayList<>();
        db=new DatabaseHandler(getActivity());
    }
    private void listDisplay(){
        String category="Electronics";
        mProductList=db.getListContents(category);
        productAdapter=new ProductAdapter(getActivity(),mProductList);
        listView.setAdapter(productAdapter);
    }
    public void onCartUpdateListener(long status, int position) {
        if(status>0){
            num=num+1;

            Toast.makeText(getActivity(),"Product added to cart",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(),"Product add failed",Toast.LENGTH_LONG).show();
        }
    }
}
