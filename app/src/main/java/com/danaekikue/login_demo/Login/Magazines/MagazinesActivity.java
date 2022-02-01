package com.danaekikue.login_demo.Login.Magazines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.danaekikue.login_demo.Login.Magazines.RecyclerView.MagazinesAdapter;
import com.danaekikue.login_demo.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagazinesActivity extends AppCompatActivity {

    public static final String URL = "https://3nt-demo-backend.azurewebsites.net/Access/Books";

    private TextView bar_tittle;

    private List<MagazineModel> magazines2020 = new ArrayList<>();
    private List<MagazineModel> magazines2019 = new ArrayList<>();
    private List<MagazineModel> magazinesOlder = new ArrayList<>();

    private RecyclerView recyclerView2020, recyclerView2019, recyclerViewOlder;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazines);

        recyclerView2020 = findViewById(R.id.recycleViewMagazines2020);
        recyclerView2019 = findViewById(R.id.recycleViewMagazines2019);
        recyclerViewOlder = findViewById(R.id.recycleViewMagazinesOlder);

        Intent intent = getIntent();
        String access_token = intent.getStringExtra("access_token");

        getRequest(access_token);

        bar_tittle = findViewById(R.id.top_bar_tittle);
        bar_tittle.setText(R.string.magazines_title);
    }

     private void getRequest( String access_token){
         // Request a string response from the provided URL.
         JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                 new Response.Listener<JSONArray>() {
                     @Override
                     public void onResponse(JSONArray response) {
                         for (int i = 0; i < response.length(); i++) {
                             try {
                                 JSONObject magazineObj = response.getJSONObject(i);

                                 MagazineModel magazine = new MagazineModel();

                                 magazine.setId(magazineObj.getInt("id"));
                                 magazine.setTitle(magazineObj.getString("title"));
                                 magazine.setImg_url(magazineObj.getString("img_url"));
                                 magazine.setDate_released(magazineObj.getString("date_released"));
                                 magazine.setPdf_url(magazineObj.getString("pdf_url"));

                                 String year = magazineObj.getString("date_released").substring(0,4);


                                if (year.equals("2020")){
                                    magazines2020.add(magazine);
                                }else if (year.equals("2019")){
                                    magazines2019.add(magazine);
                                }else{
                                    magazinesOlder.add(magazine);
                                }

                             }catch (JSONException e){
                                 e.printStackTrace();
                             }
                         }

                         setUpRecycler(magazines2020, magazines2019, magazinesOlder);
                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(MagazinesActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
             }
         }){
             @Override
             public Map<String, String> getHeaders() throws AuthFailureError {
                 HashMap<String, String> headers = new HashMap<String, String>();
                 headers.put("Authorization", access_token);
                 return headers;
             }
         };

         queue = Volley.newRequestQueue(this);
         // Add the request to the RequestQueue.
         queue.add(arrayRequest);
    }

    private void setUpRecycler(List<MagazineModel> magazines2020, List<MagazineModel> magazines2019, List<MagazineModel> magazinesOlder) {

        if (recyclerView2020!=null){
            MagazinesAdapter adapter2020 = new MagazinesAdapter(magazines2020);
            recyclerView2020.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView2020.setAdapter(adapter2020);
        }

        if (recyclerView2019!=null){
            MagazinesAdapter adapter2019 = new MagazinesAdapter(magazines2019);
            recyclerView2019.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView2019.setAdapter(adapter2019);
        }

        if (recyclerViewOlder!=null){
            MagazinesAdapter adapterOlder = new MagazinesAdapter(magazinesOlder);
            recyclerViewOlder.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerViewOlder.setAdapter(adapterOlder);
        }








    }

}