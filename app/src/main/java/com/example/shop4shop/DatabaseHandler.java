package com.example.shop4shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 25-11-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ShopDatabase";
    //User Table----------------
    public static final String USER_TABLE = "UserDetails";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    //Product Table-----------------
    public static final String PRODUCT_TABLE = "ProductDetails";
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_NAME = "product";
    public static final String PRODUCT_TYPE = "category";
    public static final String PRODUCT_PRICE = "price";

    //Cart Table
    private static final String CART_TABLE = "CartDetails";
    private static final String CART_ID = "cartId";
    private static final String CART_PRODUCT_ID = "CartProductId";
    private static final String CART_USER_ID = "CartUserId";
    private static final String CART_QUANTITY = "Quantity";
    CartModel cartModel;
    ProductModel productModel;
    Context mContext;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //User TAble---------------
        String user_TABLE = "CREATE TABLE " + USER_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT NOT NULL," + USERNAME + " TEXT NOT NULL UNIQUE," + PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(user_TABLE);

        //Product Table--------------
        String product_TABLE = "CREATE TABLE " + PRODUCT_TABLE + "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PRODUCT_NAME + " TEXT," + PRODUCT_TYPE + " TEXT," + PRODUCT_PRICE + " INT)";
        sqLiteDatabase.execSQL(product_TABLE);

        //Cart TAble
        String cart_Table = "CREATE TABLE " + CART_TABLE + "(" + CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CART_PRODUCT_ID +
                " INTEGER , " + CART_USER_ID + " INTEGER , " + CART_QUANTITY + " INTEGER )";
        sqLiteDatabase.execSQL(cart_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        onCreate(sqLiteDatabase);
    }

    //user add to database-------------------------
    public boolean addData(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, userModel.get_name());
        contentValues.put(USERNAME, userModel.get_username());
        contentValues.put(PASSWORD, userModel.get_password());
        long result = db.insert(USER_TABLE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    //validate user-------------
    public String search(String uname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + USERNAME + "," + PASSWORD + " FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String u, p = "Not found";

        if (cursor.moveToFirst()) {
            do {
                u = cursor.getString(0);
                if (u.equals(uname)) {
                    p = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return p;
    }

    //add product to database-----------
    public void addProduct(List<ProductModel> productList) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (ProductModel productModel : productList) {
            contentValues.put(PRODUCT_NAME, productModel.get_pName());
            contentValues.put(PRODUCT_TYPE, productModel.get_pCategory());
            contentValues.put(PRODUCT_PRICE, productModel.get_pPrice());

            long status = sqLiteDatabase.insert(PRODUCT_TABLE, null, contentValues);
            Log.d("TAG", "Status " + status);
        }

    }

    //View all products----------------
    public ArrayList<ProductModel> getListContents(String category) {


        ArrayList<ProductModel> productModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        String query = "select * from " + PRODUCT_TABLE + " where " + PRODUCT_TYPE + " like '%" + category + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Log.d("TAG", "Query output" + cursor);
        while (cursor != null && cursor.moveToNext()) {
            productModel = new ProductModel();
            // ProductModel productModel=new ProductModel();
            productModel.set_pId(cursor.getColumnIndex(PRODUCT_ID));
            productModel.set_pName(cursor.getString(cursor.getColumnIndex(PRODUCT_NAME)));
            productModel.set_pPrice(cursor.getInt(cursor.getColumnIndex(PRODUCT_PRICE)));
            productModel.set_pCategory(cursor.getString(cursor.getColumnIndex(PRODUCT_TYPE)));
            productModels.add(productModel);


        }
        cursor.close();
        sqLiteDatabase.close();
        return productModels;

    }

    //add product to cart
    public boolean addToCart(CartModel cartModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CART_PRODUCT_ID, cartModel.get_idProduct());
        contentValues.put(CART_USER_ID, cartModel.get_idUSer());
        contentValues.put(CART_QUANTITY, cartModel.get_qtyCart());
        long result = sqLiteDatabase.insert(CART_TABLE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //to get the user id
    public int getUserId(String uname) {
        String[] columns = {ID};
        int uid = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String selection = USERNAME + "= ?";
        String[] selectionArgs = {uname};
        Cursor cursor = sqLiteDatabase.query(USER_TABLE, columns, selection, selectionArgs, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            uid = cursor.getInt(cursor.getColumnIndex(ID));
        }
        cursor.close();
        return uid;

    }

    //view added products to cart
    public ArrayList<CartModel> getCartContents(int userId) {


        ArrayList<CartModel> cartModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        CartModel cartModel = new CartModel();

        String query = "select * from " + CART_TABLE +" join "+PRODUCT_TABLE+" on "+CART_TABLE+"."+CART_PRODUCT_ID+" = "+
        PRODUCT_TABLE+"."+PRODUCT_ID+" join "+USER_TABLE+" on "+USER_TABLE+"."+ID+" = "+CART_TABLE+"."+CART_USER_ID+" where " + ID + " = "+ userId;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Log.d("TAG", "Query output" + cursor);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cartModel.set_qtyCart(cursor.getColumnIndex(CART_QUANTITY));

                ProductModel productModel1 = new ProductModel();
                productModel1.set_pName(cursor.getString(cursor.getColumnIndex(PRODUCT_NAME)));
                productModel1.set_pPrice(cursor.getColumnIndex(PRODUCT_PRICE));
                cartModel.setProductModel(productModel1);

                cartModels.add(cartModel);

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return cartModels;

    }

}