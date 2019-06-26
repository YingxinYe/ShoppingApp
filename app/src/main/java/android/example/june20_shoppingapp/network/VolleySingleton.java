package android.example.june20_shoppingapp.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton sInstance =null;
    private RequestQueue mRequestQueue;

    private VolleySingleton(){
       // mRequestQueue= Volley.newRequestQueue()
    }

    public static VolleySingleton getInstance(){
        if(sInstance==null){
            sInstance=new VolleySingleton();
        }
        return sInstance;
    }

}
