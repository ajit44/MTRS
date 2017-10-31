package com.aj.mtrs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Lay_ad_Dr extends ArrayAdapter<String> {

    private final Activity context;
    //number of item declared in list
  //  private final String[] fname;
  /*  private final String[] fmobile;
    private final String[] fdp;
    private final String[] fstatus;
    private final String ftimef;
    private final String[] fonline;
    private final String fchat;
    private final String fsmsnof;*/

    private final String[] D_name;
    private final String[] mobileno;
    private final String[] email;
    private final String[] D_addr;
    private final String[] bloodgrp;
    private final String[] gender;
    private final String[] dob;
    private final String[] photoid;
    private final String[] d_id;
    private final String[] p_id;

    public Lay_ad_Dr(Activity context, String[] D_name, String[] mobileno, String[] email, String[] D_addr, String[] bloodgrp, String[] gender, String[] dob, String[] photoid, String[] d_id, String[] p_id){
        super(context, R.layout.lay_admin_doctor,D_name);

        this.context=context;
      //  this.fname=fname;
      /*  this.fmobile=fmobile;
        this.fdp=fdp;
        this.fstatus=fstatus;
        this.ftimef=ftimef;
        this.fonline=fonline;
        this.fchat=fchat;
        this.fsmsnof=fsmsnof;*/

         this.D_name=D_name;
         this.mobileno=mobileno;
         this.email=email;
         this.D_addr=D_addr;
         this.bloodgrp=bloodgrp;
         this.gender=gender;
         this.dob=dob;
         this.photoid=photoid;
         this.d_id=d_id;
         this.p_id=p_id;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.lay_admin_doctor, null, true);

        TextView f_name=(TextView)rowView.findViewById(R.id.name);
        f_name.setText(D_name[position]);

       /* TextView f_text=(TextView)rowView.findViewById(R.id.ftext);
        f_text.setText(fmobile[position]+":"+fchat);

        TextView f_time=(TextView)rowView.findViewById(R.id.time);
        f_time.setText(ftimef);

        TextView smsno=(TextView)rowView.findViewById(R.id.smsno);
        smsno.setText(fsmsnof);*/



        return rowView;
    }
}
