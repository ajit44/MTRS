package com.aj.mtrs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

public class Admin_doctor extends AppCompatActivity {
    ListView sub_list;


    int show = 0;
    int textlength = 0;

    public static String myJSON, send_ditails;
    private static final String TAG_RESULTS = "result";
    JSONArray peoples = null;
    public static int cnt, cnt1;

    private ProgressDialog progress;
    final Context context = this;
    EditText inputSearch;
    public static String nfc_id[],D_name[],mobileno[],email[],D_addr[],bloodgrp[],gender[],dob[],photoid[],d_id[],p_id[];




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ad__dr_ditails);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            sub_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


                    TextView textView = (TextView) v.findViewById(R.id.name);
                    send_ditails= textView.getText().toString();


                    Intent s = new Intent(getApplicationContext(),ad_Dr_ditails.class);
                    startActivity(s);
                }
            });





            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

    //#######################################################################################3 grt data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost(url.Url+"check_contact.php");

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
                try {

                    myJSON=result;
                    showList();

                }catch (Exception f){

                    //  Toast.makeText(ContactsView.this, "error recive f on poast", Toast.LENGTH_SHORT).show();
                }

            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            cnt=0;
            cnt1=0;


            for(int i=0;i<peoples.length();i++){
                cnt++;
            }
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
            p_id = new String[cnt];





            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);



                nfc_id[cnt1]=c.getString("mobile");
                D_name[cnt1]=c.getString("mobile");
                mobileno [cnt1]=c.getString("mobile");
                email[cnt1]=c.getString("mobile");
                D_addr[cnt1]=c.getString("mobile");
                bloodgrp[cnt1]=c.getString("mobile");
                gender[cnt1]=c.getString("mobile");
                dob[cnt1]=c.getString("mobile");
                photoid[cnt1]=c.getString("mobile");
                d_id[cnt1]=c.getString("mobile");
                p_id[cnt1]=c.getString("mobile");

                cnt1++;
            }


            //  Toast.makeText(this, ""+peoples.length(), Toast.LENGTH_SHORT).show();
            Lay_ad_Dr a = new Lay_ad_Dr(this,D_name,mobileno,email,D_addr,bloodgrp,gender,dob,photoid,d_id,p_id);
            sub_list.setAdapter(a);

            if(cnt==0)
            {

                Toast.makeText(Admin_doctor.this, "No Products Available", Toast.LENGTH_LONG).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

