package com.example.paymentapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {
    RecyclerView exlist, totlist;
    private RecyclerView.Adapter Adapter, newadapter;
    private List<listitem> listItem;
    private List<totlistItem> totalListItem;
    String listurl = "https://raw.githubusercontent.com/imvj018/newjson/main/testexp1.json";
    String id, name, amount, date, time, exdate, note;
    ArrayList<String> initname, initcost, totname, finName, finCost, finDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exlist = findViewById(R.id.list);
        totlist = findViewById(R.id.list1);
        exlist.setHasFixedSize(true);
        exlist.setLayoutManager(new LinearLayoutManager(this));
        totlist.setHasFixedSize(true);
        totlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true));

        listItem = new ArrayList<>();
        totalListItem = new ArrayList<>();
        initname = new ArrayList<>();
        initcost = new ArrayList<>();
        totname = new ArrayList<>();
        finName = new ArrayList<>();
        finCost = new ArrayList<>();
        finDiff = new ArrayList<>();
        getdata();
        getTotData();
    }

    private void getTotData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                listurl,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("body");
                            int finalcost = 0;
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);

                                id = object.getString("id");
                                name = object.getString("username");
                                amount = object.getString("amount");
                                date = object.getString("date");
                                time = object.getString("time");
                                exdate = object.getString("ex_date");
                                note = object.getString("note");
                                finalcost = finalcost + Integer.parseInt(amount);
                                initname.add(name);
                                initcost.add(amount);
                            }
                            System.out.println("Total Amount : " + String.valueOf(finalcost));
                            totname = initname;
                            System.out.println(totname);
                            Stream<String> stream = initname.stream();
                            stream = stream.distinct();
                            initname = (ArrayList<String>) stream.collect(Collectors.toList());
                            float per_cost, diff_amt;
                            per_cost = Float.parseFloat(String.valueOf(finalcost)) / Float.parseFloat(String.valueOf(initname.size()));
                            System.out.println(per_cost);
                            for (int name_count = 0; name_count < initname.size(); name_count++) {
                                int updqty = 0;
                                for (int new_name_count = 0; new_name_count < totname.size(); new_name_count++) {
                                    if (initname.get(name_count).equals(totname.get(new_name_count))) {
                                        updqty = updqty + Integer.parseInt(initcost.get(new_name_count));
                                    }

                                }
                                diff_amt =  Float.parseFloat(String.valueOf(updqty)) - per_cost;
                                finName.add(initname.get(name_count));
                                finCost.add(String.valueOf(updqty));
                                finDiff.add(String.valueOf(diff_amt));
                                totlistItem itemlist = new totlistItem(
                                        initname.get(name_count),
                                        String.valueOf(updqty)
                                );
                                totalListItem.add(itemlist);
                                System.out.println(finName.get(name_count) +  " " + finCost.get(name_count) + " " + finDiff.get(name_count));

                            }

                            newadapter = new totAdapter(totalListItem, getApplicationContext());
                            totlist.setAdapter(newadapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getdata() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                listurl,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("body");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                listitem item = new listitem(
                                        object.getString("id"),
                                        object.getString("username"),
                                        object.getString("amount"),
                                        object.getString("date"),
                                        object.getString("time"),
                                        object.getString("ex_date"),
                                        object.getString("note")
                                );
                                listItem.add(item);
                            }
                            Adapter = new adapter(listItem, getApplicationContext());
                            exlist.setAdapter(Adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}