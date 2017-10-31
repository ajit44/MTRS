package com.aj.mtrs;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

public class ad_Dr_ditails extends AppCompatActivity {
    LinearLayout lay;
    ImageView up_cls;
    int up_flg=0;
    FloatingActionButton fab;
    ScrollView up_sc;
    String receivedValue,clearChat,delete;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad__dr_ditails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        up_sc=(ScrollView)findViewById(R.id.up_sc);
        lay=(LinearLayout) findViewById(R.id.lay);
        up_cls=(ImageView)findViewById(R.id.up_cls);


        up_cls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(up_flg==1){
                    lay.setVisibility(View.VISIBLE);
                    up_flg=0;
                    up_sc.setVisibility(View.INVISIBLE);
                    fab.setVisibility(View.VISIBLE);
                }
            }
        });
         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    up_sc.setVisibility(View.VISIBLE);

                    //layout_anchorGravity="center_vertical|right"
                lay.setVisibility(View.INVISIBLE);
                   fab.setVisibility(View.INVISIBLE);
                    up_flg=1;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ad_dr_di_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(ad_Dr_ditails.this);

            builder1.setTitle("Confirmation");
            builder1.setIcon(R.mipmap.ic_launcher);
            builder1.setMessage("Are you sure wants to Delete?");
            builder1.setCancelable(false);

            builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog alert11 = builder1.create();
            alert11.show();



            return true;
        }

        if (id == R.id.update) {
        fab.setVisibility(View.INVISIBLE);
            up_flg=1;
            up_sc.setVisibility(View.VISIBLE);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4 send server $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

    private class backgroundProcessClass extends AsyncTask<String, Void, Void> {
        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            try {




            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            }

        }
        @Override
        protected Void doInBackground(String... params) {


            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url.Url+"send1.php");

            //temp=params[0];
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
          /*  pairs.add(new BasicNameValuePair("chat", ssmg));
            pairs.add(new BasicNameValuePair("mob", mob));
            pairs.add(new BasicNameValuePair("dat", sdate));
            pairs.add(new BasicNameValuePair("clear", clearChat));*/

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



                clearChat="no";


                if (receivedValue.contains("pas")) {




                } else if (receivedValue.contains("Student")) {

                }else if (receivedValue.contains("fail")) {


                }
                else if (receivedValue.equals("    ")) {



                }

            }catch(Exception e)
            {

                //  Toast.makeText(context, "Error_last:"+e, Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);
        }
    }


}