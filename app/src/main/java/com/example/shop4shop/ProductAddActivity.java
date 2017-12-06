package com.example.shop4shop;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProductAddActivity extends AppCompatActivity {

    EditText pName, pPrice, pQty;
    Button addProduct;
    Context mContext;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        pName = findViewById(R.id.ed_pName);
        pPrice = findViewById(R.id.ed_pPrice);
        pQty = findViewById(R.id.ed_pQty);
        addProduct = findViewById(R.id.btn_add);
        mContext = ProductAddActivity.this;
        db = new DatabaseHandler(mContext);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String productName = pName.getText().toString(), price = pPrice.getText().toString(),
                        quantity = pQty.getText().toString();

                int productPrice = Integer.parseInt(price);
                int productQuantity = Integer.parseInt(quantity);

                if (productName.equals("") || price.equals("") || quantity.equals("")) {
                    Toast.makeText(mContext, "Fields should not be empty", Toast.LENGTH_LONG).show();
                } else {
                    ProductModel productModel = new ProductModel();
                    productModel.set_pname(productName);
                    productModel.set_pqty(productQuantity);
                    productModel.set_price(productPrice);

                    AddProduct(productModel);
                    pName.setText("");
                    pPrice.setText("");
                    pQty.setText("");
                }*/

            }
        });
    }

    public void AddProduct(ProductModel productModel) {
                /*boolean i=db.addProduct(productModel);
                if(i==true){
                    Toast.makeText(this,"Product added successfully",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this,"Product doenn't added ",Toast.LENGTH_LONG).show();
                }*/
    }


}

