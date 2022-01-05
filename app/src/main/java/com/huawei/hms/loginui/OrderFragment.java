package com.huawei.hms.loginui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class OrderFragment extends Fragment {

    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;
    RadioButton radioButtonG1, radioButtonG2, radioButtonG3, radioButtonG4, minicrunchradbtn, bubblericeradbtn, chocradbtn, strawberryradbtn, butterscotchradbtn, cadburyadbtn, kitkatradbtn, kinderradbtn, gram3radbtn, gram5radbtn;
    Button btncheckout, btnaddtocart;
    ImageButton btnadd, btnminus;
    TextView tvquantity;
    int quantity;
    float total;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.order_fragment, container, false);
        radioGroup1 = root.findViewById(R.id.rg1);
        //radioGroup2 = root.findViewById(R.id.rg2);
        radioGroup3 = root.findViewById(R.id.rg3);
        radioGroup4 = root.findViewById(R.id.rg4);
        btnadd = root.findViewById(R.id.cartaddbtn);
        btnminus = root.findViewById(R.id.cartminusbtn);
        btncheckout = root.findViewById(R.id.btnchkout);
        tvquantity = root.findViewById(R.id.tvquantitycart);
        btnaddtocart = root.findViewById(R.id.btnaddcart);
        minicrunchradbtn=root.findViewById(R.id.minicrunchradbtn);
        bubblericeradbtn=root.findViewById(R.id.bubblericeradbtn);
//        chocradbtn=root.findViewById(R.id.chocradbtn);
//        strawberryradbtn=root.findViewById(R.id.strawberryradbtn);
//        butterscotchradbtn=root.findViewById(R.id.butterscotchradbtn);
        cadburyadbtn=root.findViewById(R.id.cadburyradbtn);
        kitkatradbtn=root.findViewById(R.id.kitkatradbtn);
        kinderradbtn=root.findViewById(R.id.kinderradbtn);
        gram3radbtn=root.findViewById(R.id.gram3radbtn);
        gram5radbtn=root.findViewById(R.id.gram5radbtn);
        quantity = 0;



        float[] price = new float[4];

        if (minicrunchradbtn.isChecked() || bubblericeradbtn.isChecked())
            price[0] = 7;





        if (cadburyadbtn.isChecked() || kitkatradbtn.isChecked() || kinderradbtn.isChecked())
            price[2] = 2;


        if (gram3radbtn.isChecked())
           price[3] = 1;
        else if (gram5radbtn.isChecked())
            price[3] = 2;





       btnadd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               quantity++;
               tvquantity.setText("" + quantity);
           }
       });
       btnminus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               quantity--;
               tvquantity.setText("" + quantity);
           }
       });
       btncheckout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(getActivity(),"Thank you for ordering: "+ quantity + " Jar",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getActivity(), MainActivity.class));
               //Intent intent= new Intent(getActivity(), OrderDetailActivity.class);
               //Intent.putExtra("quantity",quantity);
               //intent.putExtra("name",name);
               //startActivity(intent);
//               for(int i=0;i<= price.length;i++)
//               {
//
//                   total=total+price[i];
//               }
           }
       });
       btnaddtocart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               requireActivity().getSharedPreferences("Your Cart: ", Context.MODE_PRIVATE).edit().apply();
               startActivity(new Intent(getActivity(), MainActivity.class));
//               for(int i=0;i<= price.length;i++)
//               {
//
//                   total=total+price[i];
//               }
           }

       });
       return root;
   }
   public void checkButton(View v){
       int radioId = radioGroup1.getCheckedRadioButtonId();
       int radioId2 = radioGroup2.getCheckedRadioButtonId();
       int radioId3 = radioGroup3.getCheckedRadioButtonId();
       int radioId4 = radioGroup4.getCheckedRadioButtonId();
       radioButtonG1= v.findViewById(radioId);
       Toast.makeText(getActivity(), "Selected Radio Button: "+ radioButtonG1.getText(),Toast.LENGTH_SHORT).show();
//       radioButtonG2= v.findViewById(radioId2);
//       Toast.makeText(getActivity(), "Selected Radio Button: "+ radioButtonG2.getText(),Toast.LENGTH_SHORT).show();
       radioButtonG3= v.findViewById(radioId3);
       Toast.makeText(getActivity(), "Selected Radio Button: "+ radioButtonG3.getText(),Toast.LENGTH_SHORT).show();
       radioButtonG4= v.findViewById(radioId4);
       Toast.makeText(getActivity(), "Selected Radio Button: "+ radioButtonG4.getText(),Toast.LENGTH_SHORT).show();


   }


}



