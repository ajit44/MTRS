package com.aj.mtrs;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.aj.mtrs.Admin_doctor.cnt;
import static com.aj.mtrs.Admin_doctor.myJSON;

public class PasswordActivity extends AppCompatActivity {

    EditText Password;
    Button PasswordB;
    String Username,NFCID,Data,Type,PasswordS;
    Intent i;

    public static String myJSON;
    private static final String TAG_RESULTS="result";
    JSONArray peoples = null;
    public  static  int cnt;
    String weburl="http://192.168.0.3/MRTS/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        try {
            Password = (EditText) findViewById(R.id.Password);
            PasswordB = (Button) findViewById(R.id.PasswordB);

            PasswordB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PasswordS=Password.getText().toString();
                    Toast.makeText(PasswordActivity.this, "uname : "+Username+" pas : "+PasswordS+" nfc : "+NFCID, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(PasswordActivity.this, "uname : "+Username+" pas : "+Password+" nfc : "+NFCID, Toast.LENGTH_SHORT).show();

                    getData();
                }
            });

        }catch (Exception s){
            Toast.makeText(this, "p "+s, Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost= new HttpPost(weburl+"login.php");

                try {


                    if(Username != null){
                        httppost= new HttpPost(weburl+"login.php?Username="+Username+"&Password="+PasswordS);
                        Toast.makeText(PasswordActivity.this, "1 "+weburl+"login.php?Username="+Username+"&Password="+PasswordS, Toast.LENGTH_LONG).show();
                    }else  if(NFCID != null){
                        httppost= new HttpPost(weburl+"login.php?NFCID="+NFCID+"&Password="+PasswordS);
                    }
                }catch (Exception s){
                    Toast.makeText(PasswordActivity.this, "get 1 "+s, Toast.LENGTH_SHORT).show();
                }
               /* try {
                    if (Username != "") {
                        List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
                        pairs.add(new BasicNameValuePair("ID", NFCID));
                        pairs.add(new BasicNameValuePair("Password", Password.getText().toString()));
                        try {
                            httppost.setEntity(new UrlEncodedFormEntity(pairs));
                        } catch (Exception ex) {
                        }
                    } else if (NFCID != "") {
                        List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
                        pairs.add(new BasicNameValuePair("Username", Username));
                        pairs.add(new BasicNameValuePair("Password", Password.getText().toString()));
                        try {
                            httppost.setEntity(new UrlEncodedFormEntity(pairs));
                        } catch (Exception ex) {
                        }
                    }


                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");
                }catch (Exception s){
                    Toast.makeText(PasswordActivity.this, "get 1 "+s, Toast.LENGTH_SHORT).show();
                }*/


                // Depends on your web service
                try{
                    httppost.setHeader("Content-type", "application/json");
                }catch (Exception s){
                    Toast.makeText(PasswordActivity.this, "get 1 "+s, Toast.LENGTH_SHORT).show();
                }
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

                try{
                    Toast.makeText(PasswordActivity.this, result, Toast.LENGTH_SHORT).show();
                    if(result.contains("fail"))
                    {
                        Toast.makeText(PasswordActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        myJSON=result;
                        Toast.makeText(PasswordActivity.this, "Data : "+result, Toast.LENGTH_SHORT).show();
                           showList();
                    }
                }catch (Exception s){
                    Toast.makeText(PasswordActivity.this, "get 2 "+s, Toast.LENGTH_SHORT).show();
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
            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                cnt++;
            }
            if(cnt==0)
            {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            }

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                Type=c.getString("Type");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (Type)
        {
            case "Doctor":
                i = new Intent(getApplicationContext(),Admin_Activity.class);
                startActivity(i);
                break;
            case  "Admin":
                i = new Intent(getApplicationContext(),PasswordActivity.class);
                startActivity(i);
                break;
            case "Patient":
                i = new Intent(getApplicationContext(),PasswordActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Type=null;
        NFCID = LoginActivity.IDS;
        Username =url.UsernameS;
        Data = LoginActivity.Data;

    }
}
