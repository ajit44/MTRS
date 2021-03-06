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

    public static String myJSON, send_name,send_nfc_id,send_dr_id,send_no,send_phno,send_email,send_address,send_dob,send_blood;
    private static final String TAG_RESULTS = "result";
    JSONArray peoples = null;
    public static int cnt, cnt1;

    private ProgressDialog progress;
    final Context context = this;
    EditText inputSearch;
    public static String nfc_id[],D_name[],mobileno[],email[],D_addr[],bloodgrp[],gender[],dob[],photoid[],d_id[],p_id[],city[];




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_doctor);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            try {

              //  Toast.makeText(context, "hii", Toast.LENGTH_SHORT).show();

                getData();
                Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                sub_list=(ListView)findViewById(R.id.sub_list);
                sub_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


                        TextView a = (TextView) v.findViewById(R.id.name);
                        send_name = a.getText().toString();

                        TextView b = (TextView) v.findViewById(R.id.nfc_id);
                        send_nfc_id = b.getText().toString();

                        TextView c = (TextView) v.findViewById(R.id.id);
                        send_dr_id = c.getText().toString();

                        TextView d = (TextView) v.findViewById(R.id.phno);
                        send_no = d.getText().toString();

                        TextView e = (TextView) v.findViewById(R.id.email);
                        send_email = e.getText().toString();

                        TextView f = (TextView) v.findViewById(R.id.address);
                        send_address = f.getText().toString();

                        TextView g = (TextView) v.findViewById(R.id.dob);
                        send_dob = g.getText().toString();

                        TextView h = (TextView) v.findViewById(R.id.blood);
                        send_blood = h.getText().toString();


                        Intent s = new Intent(getApplicationContext(), ad_Dr_ditails.class);
                        startActivity(s);
                    }
                });

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Intent d = new Intent(getApplicationContext(),ad_Dr_ditails.class);
                       startActivity(d);
                    }
                });


            }catch (Exception s){
                Toast.makeText(context, "ad_dr list  ::"+s, Toast.LENGTH_LONG).show();
            }
        }

    //#######################################################################################3 grt data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
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
                    Toast.makeText(context, "dd", Toast.LENGTH_SHORT).show();
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

                      Toast.makeText(getApplicationContext(), "error recive ::"+f, Toast.LENGTH_SHORT).show();
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

            city= new String[cnt];





            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);



                nfc_id[cnt1]=c.getString("nfc_id");
                D_name[cnt1]=c.getString("D_name");
                mobileno [cnt1]=c.getString("mobileno");
                email[cnt1]=c.getString("email");
                D_addr[cnt1]=c.getString("D_addr");
                bloodgrp[cnt1]=c.getString("bloodgrp");
                gender[cnt1]=c.getString("gender");
                dob[cnt1]=c.getString("dob");
                photoid[cnt1]=c.getString("photoid");
                d_id [cnt1]=c.getString("d_id");
                city[cnt1]=c.getString("city");



                cnt1++;
            }


              Toast.makeText(this, ""+peoples.length(), Toast.LENGTH_SHORT).show();
           Lay_ad_Dr a = new Lay_ad_Dr(this,D_name,nfc_id,mobileno,email,D_addr,bloodgrp,gender,dob,photoid,d_id,p_id,city);
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

