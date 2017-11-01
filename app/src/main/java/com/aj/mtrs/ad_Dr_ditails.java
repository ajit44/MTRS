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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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

    TextView doctor_name, doctor_id, doctor_phno, doctor_email, doctor_address, doctor_dob, doctor_blood;
    EditText up_name, up_mobile, up_email, up_address, up_dob, up_blood;
    Button up_submit;
    public String up_name_s, up_mobile_s, up_email_s, up_address_s, up_dob_s, up_blood_s;
    LinearLayout lay;
    ImageView up_cls;
    int up_flg = 0;
    FloatingActionButton fab;
    ScrollView up_sc;
    String receivedValue, clearChat = "no", delete;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad__dr_ditails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialize_v();

        up_sc = (ScrollView) findViewById(R.id.up_sc);
        lay = (LinearLayout) findViewById(R.id.lay);
        up_cls = (ImageView) findViewById(R.id.up_cls);
        up_sc.setVisibility(View.INVISIBLE);

        up_cls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (up_flg == 1) {
                    lay.setVisibility(View.VISIBLE);
                    up_flg = 0;
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
                up_flg = 1;
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
            Toast.makeText(context, "nfc : " + Admin_doctor.send_nfc_id, Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder1 = new AlertDialog.Builder(ad_Dr_ditails.this);

            builder1.setTitle("Confirmation");
            builder1.setIcon(R.mipmap.ic_launcher);
            builder1.setMessage("Are you sure wants to Delete?");
            builder1.setCancelable(false);

            builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    clearChat = "yes";
                    new backgroundProcessClass().execute();
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
            lay.setVisibility(View.INVISIBLE);
            fab.setVisibility(View.INVISIBLE);
            up_flg = 1;
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
            HttpPost post = new HttpPost(url.Url + "doctor_update.php");

            //temp=params[0];
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);


            pairs.add(new BasicNameValuePair("up_name_s", up_name_s));
            pairs.add(new BasicNameValuePair("up_mobile_s", up_mobile_s));
            pairs.add(new BasicNameValuePair("up_email_s", up_email_s));

            pairs.add(new BasicNameValuePair("up_address_s", up_address_s));
            pairs.add(new BasicNameValuePair("up_dob_s", up_dob_s));
            pairs.add(new BasicNameValuePair("up_blood_s", up_blood_s));
            pairs.add(new BasicNameValuePair("clearChat", clearChat));
            pairs.add(new BasicNameValuePair("nfc_id", Admin_doctor.send_nfc_id));

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


                clearChat = "no";


                if (receivedValue.contains("pas")) {


                } else if (receivedValue.contains("Student")) {

                } else if (receivedValue.contains("fail")) {


                } else if (receivedValue.equals("    ")) {


                }

            } catch (Exception e) {

                //  Toast.makeText(context, "Error_last:"+e, Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);
        }
    }


    public void initialize_v() {

        doctor_name = (TextView) findViewById(R.id.doctor_name);
        doctor_id = (TextView) findViewById(R.id.doctor_id);
        doctor_phno = (TextView) findViewById(R.id.doctor_phno);
        doctor_email = (TextView) findViewById(R.id.doctor_email);
        doctor_address = (TextView) findViewById(R.id.doctor_address);
        doctor_dob = (TextView) findViewById(R.id.doctor_dob);
        doctor_blood = (TextView) findViewById(R.id.doctor_blood);

        doctor_name.setText(Admin_doctor.send_name);
        doctor_id.setText(Admin_doctor.send_dr_id);
        doctor_phno.setText(Admin_doctor.send_no);
        doctor_email.setText(Admin_doctor.send_email);
        doctor_address.setText(Admin_doctor.send_address);
        doctor_dob.setText(Admin_doctor.send_dob);
        doctor_blood.setText(Admin_doctor.send_blood);


        up_name = (EditText) findViewById(R.id.up_name);
        up_mobile = (EditText) findViewById(R.id.up_mobile);
        up_email = (EditText) findViewById(R.id.up_email);
        up_address = (EditText) findViewById(R.id.up_address);
        up_dob = (EditText) findViewById(R.id.up_dob);
        up_blood = (EditText) findViewById(R.id.up_blood);


        up_submit = (Button) findViewById(R.id.up_submit);

        up_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                up_name_s = up_name.getText().toString();
                up_mobile_s = up_mobile.getText().toString();
                up_email_s = up_email.getText().toString();
                up_address_s = up_address.getText().toString();
                up_dob_s = up_dob.getText().toString();
                up_blood_s = up_blood.getText().toString();

                if (up_name_s.equals("")) {
                    up_name_s = Admin_doctor.send_name;
                }
                if (up_mobile_s.equals("")) {
                    up_mobile_s = Admin_doctor.send_no;
                }
                if (up_email_s.equals("")) {
                    up_email_s = Admin_doctor.send_email;
                }
                if (up_address_s.equals("")) {
                    up_address_s = Admin_doctor.send_address;
                }
                if (up_dob_s.equals("")) {
                    up_dob_s = Admin_doctor.send_dob;
                }
                if (up_blood_s.equals("")) {
                    up_blood_s = Admin_doctor.send_blood;
                }
                clearChat = "no";
                Toast.makeText(context, "id = " + Admin_doctor.send_nfc_id + "   type " + clearChat, Toast.LENGTH_SHORT).show();
                new backgroundProcessClass().execute();
                Snackbar.make(view, "Working chalu aahe", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

    }



}
