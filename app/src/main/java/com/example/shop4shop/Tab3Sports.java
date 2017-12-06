package com.example.shop4shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by LENOVO on 29-11-2017.
 */

public class Tab3Sports extends Fragment {
    ListView listView3;
    ProductAdapter productAdapter;
    ArrayList<ProductModel> mArrayList;
    DatabaseHandler db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment3, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        listDisplay();

    }
    private void initView(){
        listView3=getView().findViewById(R.id.listview_fragment3);
        mArrayList=new ArrayList<>();
        db=new DatabaseHandler(getActivity());
    }
    private void listDisplay(){
        String category="SPORTS";
        mArrayList=db.getListContents(category);
        productAdapter=new ProductAdapter(getActivity(),mArrayList);
        listView3.setAdapter(productAdapter);
    }
}
