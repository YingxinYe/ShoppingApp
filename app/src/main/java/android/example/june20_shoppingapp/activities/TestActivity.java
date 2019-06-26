package android.example.june20_shoppingapp.activities;

import android.example.june20_shoppingapp.R;
import android.example.june20_shoppingapp.adapters.AdapterTest;
import android.example.june20_shoppingapp.models.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterTest adapterTest;
    ArrayList<Image> list = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(this, 2);
        adapterTest = new AdapterTest(this, list);
        recyclerView.setLayoutManager(layoutManager);
        fetchData();

    }

    private void fetchData(){
        String url = "https://jsonplaceholder.typicode.com/photos";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i=0;i<response.length();i++){
                                JSONObject jsonObject = (JSONObject) response.getJSONObject(i);
                                Image image = new Image();
                                image.setImage(jsonObject.getString("url"));
                                image.setTitle(jsonObject.getString("title"));
                                list.add(image);
                            }
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setAdapter(adapterTest);
                            //adapterTest.setData(list);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("test", "error");
                        Log.d("test", "error: "+ error.getMessage());
                        Toast.makeText(TestActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(request);

    }
}
