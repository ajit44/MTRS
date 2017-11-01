package com.aj.mtrs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    Button bn;
    LinearLayout log_lay;
    EditText username, password;
    public String uname, pass, weburl, receivedValue;
//    String url="192.168.43.214/login.php";

    private ProgressDialog progress;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bn = (Button) findViewById(R.id.submit);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        weburl = "192.168.0.100";

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   url.UsernameS= uname = username.getText().toString();

                    pass = password.getText().toString();
                    if (uname.equals("")) {
                        username.setError("Username Can not be blank !");
                    } /*else if (password.equals("")) {
                        password.setError("Password Can not be blank !");
                    }*/ else {

                        Intent s =new Intent(getApplicationContext(),PasswordActivity.class);
                        startActivity(s);
                       // new backgroundProcessClass().execute();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "Error Button="+e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*private class backgroundProcessClass extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {

                progress = new ProgressDialog(context);
                progress.setMessage("Wait...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(false);
                progress.setProgress(0);
                progress.setCancelable(false);
                progress.show();

            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            }

        }
        @Override
        protected Void doInBackground(String... params) {


            // Toast.makeText(context, "indoback", Toast.LENGTH_SHORT).show();
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("Http://" + url.Url);

            //temp=params[0];
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("username", uname));
            pairs.add(new BasicNameValuePair("password", pass));

            try {
                post.setEntity(new UrlEncodedFormEntity(pairs));
            } catch (Exception ex) {

            }
            //Perform HTTP Request
            try {
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                receivedValue = client.execute(post, responseHandler);


            } catch (Exception ex) {

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                Toast.makeText(context, "recv : " + receivedValue, Toast.LENGTH_SHORT).show();
                progress.dismiss();
                if (receivedValue.contains("pass")) {
                    password.setText("");
                    username.setText("");
                } else if (receivedValue.contains("fail")) {
                    Toast.makeText(context, "Invalid User Name or password", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    username.setText("");

                } else {
                    Toast.makeText(context, "Error : " + receivedValue, Toast.LENGTH_SHORT).show();
                }
                super.onPostExecute(aVoid);
            } catch (Exception e) {
                Toast.makeText(context, "Error Post="+e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }*/
}
