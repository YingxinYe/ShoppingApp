package android.example.june20_shoppingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.example.june20_shoppingapp.models.Cart;
import android.example.june20_shoppingapp.models.Product;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "shop_database";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "cart_table";

    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";
    private final static String COLUMN_QUANTITY = "quantity";
    private final static String COLUMN_PRICE = "price";
    private final static String COLUMN_IMAGE = "image";

    private final static String TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " VARCHAR," +
            COLUMN_QUANTITY + " INTEGER," + COLUMN_PRICE + " INTEGER," + COLUMN_IMAGE + " VARCHAR );";

    SQLiteDatabase database;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(TABLE_QUERY);
        } catch (SQLException exception) {
            Log.e("MyTag", exception.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(drop);
        onCreate(db);
    }

    public boolean isProductAlreadyInCart(Product product) {
        ArrayList<Cart> temp = readAlldata();
        for (int i = 0; i < temp.size(); i++) {
            String Already_exsit_product_name = temp.get(i).getCartItemName();
            if (product.getName().equals(Already_exsit_product_name) ) {
                return true;
            }
        }
        return false;
    }

    public void addProduct(Product product) {
        if (isProductAlreadyInCart(product)) {
            Log.i("MyTag",product.getName()+"already exist");
            updateProduct(product);

        } else {
            Log.i("MyTag",product.getName()+" not exist. Add now");
            ContentValues values = new ContentValues();

            values.put(COLUMN_NAME, product.getName());
            values.put(COLUMN_IMAGE, product.getImage());
            values.put(COLUMN_QUANTITY, 1);
            values.put(COLUMN_PRICE, product.getPrice());

            database.insert(TABLE_NAME, null, values);
        }

    }

    public void updateProduct(Product product) {
        // increment the quantity
        // update statment and increase the quantity by 1
        String product_need_update_name = product.getName();
        int current_quantity = 0;

        ArrayList<Cart> temp = readAlldata();
        for (int i = 0; i < temp.size(); i++) {
            if (product_need_update_name.equals(temp.get(i).getCartItemName()) ) {
                current_quantity = temp.get(i).getQuantity();
            }
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, current_quantity + 1);
        database.update(TABLE_NAME, values, COLUMN_NAME + "=?", new String[]{product_need_update_name});
    }

    public void add_quantity(Cart cart) {
        String product_need_update_name = cart.getCartItemName();
        int current_quantity = 0;

        ArrayList<Cart> temp = readAlldata();
        for (int i = 0; i < temp.size(); i++) {
            if (product_need_update_name.equals(temp.get(i).getCartItemName()) ) {
                current_quantity = temp.get(i).getQuantity();
            }
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, current_quantity + 1);
        database.update(TABLE_NAME, values, COLUMN_NAME + "=?", new String[]{product_need_update_name});
    }

    public void minus_quantity(Cart cart){
        String product_need_update_name = cart.getCartItemName();
        int current_quantity = 0;

        ArrayList<Cart> temp = readAlldata();
        for (int i = 0; i < temp.size(); i++) {
            if (product_need_update_name.equals(temp.get(i).getCartItemName()) ) {
                current_quantity = temp.get(i).getQuantity();
            }
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, current_quantity - 1);
        database.update(TABLE_NAME, values, COLUMN_NAME + "=?", new String[]{product_need_update_name});
    }

    public void deleteProduct(Cart cart) {
        Log.i("MyTag","You enter delete method");
        String need_to_delete_item_name = cart.getCartItemName();
        database.delete(TABLE_NAME, COLUMN_NAME + "=?", new String[]{need_to_delete_item_name });
    }

//    public void deleteProduct(Product product) {
//        String name = product.getName();
//        String unit = product.getUnit();
//        int price = product.getPrice();
//        database.delete(TABLE_NAME, COLUMN_NAME + "=?", new String[]{name});
//    }

    public ArrayList<Cart> readAlldata() {
        ArrayList<Cart> mlist = new ArrayList<>();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_QUANTITY, COLUMN_PRICE, COLUMN_IMAGE};
        Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Cart cart = new Cart();
                cart.setCartItemName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                cart.setCartItemImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                cart.setCartItemId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                cart.setProductPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE))));
                cart.setQuantity(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUANTITY))));
                mlist.add(cart);
                //Log.i("Product",cart.getCartItemName()+" "+cart.getCartItemId());
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mlist;
    }

    public void dropTableProduct() {
        String drop = "DROP TABLE IF EXISTS " + TABLE_NAME;
        database.execSQL(drop);
        onCreate(database);
    }
}
