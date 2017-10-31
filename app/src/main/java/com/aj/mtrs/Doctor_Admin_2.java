package com.aj.mtrs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Doctor_Admin_2 extends AppCompatActivity {

    public static String myJSON;
    private static final String TAG_RESULTS="result";
    JSONArray peoples = null;
    public  static  int cnt,cnt1,flag=0;
    public String receivedValue;
    ListView sub_list;
    public static String nfc_id[],D_name[],mobileno[],email[],D_addr[],bloodgrp[],gender[],dob[],photoid[],d_id[],p_id[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__admin_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getDataa();
        sub_list=(ListView)findViewById(R.id.sub_list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    public void getDataa(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost(url.Url+"get_doctor_admin.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showLista();
                Toast.makeText(Doctor_Admin_2.this, "1", Toast.LENGTH_SHORT).show();

            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
    protected void showLista(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            cnt=0;
            cnt1=0;

            Toast.makeText(Doctor_Admin_2.this, "2 ", Toast.LENGTH_SHORT).show();
            for(int i=0;i<peoples.length();i++){
                //     JSONObject c = peoples.getJSONObject(i);



                cnt++;
            }

            //sssssssssssssssssssssss

            nfc_id = new String[cnt];
            D_name = new String[cnt];
            mobileno  = new String[cnt];
            email = new String[cnt];
            D_addr = new String[cnt];
            bloodgrp = new String[cnt];
            gender = new String[cnt];
            dob = new String[cnt];
            photoid = new String[cnt];
            d_id = new String[cnt];


            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);


                Toast.makeText(this, "vf"+peoples.length(), Toast.LENGTH_SHORT).show();

                nfc_id[cnt1]=c.getString("nfc_id");
                D_name[cnt1]=c.getString("D_name");
                mobileno [cnt1]=c.getString("mobileno");
                email[cnt1]=c.getString("email");
                D_addr[cnt1]=c.getString("D_addr");
                bloodgrp[cnt1]=c.getString("bloodgrp");
                gender[cnt1]=c.getString("gender");
                dob[cnt1]=c.getString("dob");
                photoid[cnt1]=c.getString("photoid");
                d_id[cnt1]=c.getString("d_id");

                Toast.makeText(this, "nfc_id "+nfc_id[cnt1], Toast.LENGTH_SHORT).show();
                cnt1++;
            }
            Toast.makeText(this, "v"+peoples.length(), Toast.LENGTH_SHORT).show();
          //  Lay_ad_Dr a=new Lay_ad_Dr(this,D_name);
          //  sub_list.setAdapter(a);

            if(cnt==0)
            {
                Toast.makeText(Doctor_Admin_2.this, "R No Products Available", Toast.LENGTH_LONG).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
