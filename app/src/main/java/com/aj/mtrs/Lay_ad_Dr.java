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
    private final String[] nfc_id;
    private final String[] mobileno;
    private final String[] email;
    private final String[] D_addr;
    private final String[] bloodgrp;
    private final String[] gender;
    private final String[] dob;
    private final String[] photoid;
    private final String[] d_id;
    private final String[] p_id;
    private final String[] city;

    public Lay_ad_Dr(Activity context, String[] D_name,String[] nfc_id, String[] mobileno, String[] email, String[] D_addr, String[] bloodgrp, String[] gender, String[] dob, String[] photoid, String[] d_id, String[] p_id, String[] city){
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
         this.nfc_id=nfc_id;
         this.mobileno=mobileno;
         this.email=email;
         this.D_addr=D_addr;
         this.bloodgrp=bloodgrp;
         this.gender=gender;
         this.dob=dob;
         this.photoid=photoid;
         this.d_id=d_id;
         this.p_id=p_id;
         this.city=city;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.lay_admin_doctor, null, true);

        TextView f_name=(TextView)rowView.findViewById(R.id.name);
        f_name.setText(D_name[position]);

        TextView f_text=(TextView)rowView.findViewById(R.id.city);
        f_text.setText(city[position]);

        TextView f_time=(TextView)rowView.findViewById(R.id.id);
        f_time.setText(d_id[position]);

        TextView nfc_ida=(TextView)rowView.findViewById(R.id.nfc_id);
        nfc_ida.setText(nfc_id[position]);

        TextView fphno=(TextView)rowView.findViewById(R.id.phno);
        fphno.setText(mobileno[position]);

        TextView femail=(TextView)rowView.findViewById(R.id.email);
        femail.setText(email[position]);
        TextView faddress=(TextView)rowView.findViewById(R.id.address);
        faddress.setText(D_addr[position]);
        TextView fdob=(TextView)rowView.findViewById(R.id.dob);
        fdob.setText(dob[position]);

        TextView fblood=(TextView)rowView.findViewById(R.id.blood);
        fblood.setText(bloodgrp[position]);






        return rowView;
    }
}
