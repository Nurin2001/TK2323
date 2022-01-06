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

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class OrderBottomSheetFragment extends BottomSheetDialogFragment {

    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;
    RadioButton radioButtonG1, radioButtonG2, radioButtonG3, radioButtonG4, minicrunchradbtn, bubblericeradbtn, chocradbtn, strawberryradbtn, butterscotchradbtn, cadburyadbtn, kitkatradbtn, kinderradbtn, gram3radbtn, gram5radbtn;
    Button btncheckout;
    ImageButton btnadd, btnminus;
    TextView tvquantity;
    int quantity;
    float[] price;
    Intent gotoCheckoutIntent;

    public OrderBottomSheetFragment () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.order_fragment, container, false);

        //radiogroup
        radioGroup1 = root.findViewById(R.id.rg1);
        radioGroup2 = root.findViewById(R.id.rg2);
        radioGroup3 = root.findViewById(R.id.rg3);
        radioGroup4 = root.findViewById(R.id.rg4);

        //button
        btnadd = root.findViewById(R.id.cartaddbtn);
        btnminus = root.findViewById(R.id.cartminusbtn);
        btncheckout = root.findViewById(R.id.btnchkout);
        //btnaddtocart = root.findViewById(R.id.btnaddcart);

        //textview
        tvquantity = root.findViewById(R.id.tvquantitycart);

        //initialize variables for qty and price array
        price = new float[4];

        gotoCheckoutIntent = new Intent(getContext(), CheckOut.class);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radiogroup1(i);
            }
        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radiogroup4(i);
            }
        });
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radiogroup2(i);
            }
        });
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radiogroup3(i);
            }
        });

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
               if(quantity>0) {
                   quantity--;
                   tvquantity.setText("" + quantity);
               }

           }
       });
       btncheckout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (radioGroup1.getCheckedRadioButtonId() == -1 || radioGroup3.getCheckedRadioButtonId() == -1 || radioGroup4.getCheckedRadioButtonId() == -1)
               {
                   // no radio buttons are checked
                   Toast.makeText(root.getContext(), "Please make one choice for every group" ,Toast.LENGTH_SHORT).show();

               }
               else if(quantity==0)
                   Toast.makeText(root.getContext(), "Quantity can't be 0" ,Toast.LENGTH_SHORT).show();

               else {
                   goToCheckOut(root);
               }

           }
       });

       return root;
   }

   private void goToCheckOut(ViewGroup root) {
       float totalPrice = totalPrice();
       gotoCheckoutIntent.putExtra("qty", quantity);
       gotoCheckoutIntent.putExtra("total_price", totalPrice);

       Toast.makeText(root.getContext(), "Thank you for ordering: "+ quantity + " Jar",Toast.LENGTH_SHORT).show();
       startActivity(gotoCheckoutIntent);
   }

//fillings
   private void radiogroup1(int radBtnId) {
        switch (radBtnId) {
            case R.id.minicrunchradbtn:
                gotoCheckoutIntent.putExtra("fillings", "Mini Crunch");

                price[0] = 7;
                break;
            case  R.id.bubblericeradbtn:
                gotoCheckoutIntent.putExtra("fillings", "Bubble Rice");
                price[0] = 7;
                break;
        }
    }
    //topping
    private void radiogroup2(int radBtnId) {
        switch (radBtnId) {
            case R.id.cadburyradbtn:
                gotoCheckoutIntent.putExtra("topping", "Cadbury");
                price[1] = 2;
                break;
            case  R.id.kitkatradbtn:
                gotoCheckoutIntent.putExtra("topping", "Kit Kat");
                price[1] = 2;
                break;
            case  R.id.kinderradbtn:
                gotoCheckoutIntent.putExtra("topping", "Kinder Bueno");
                price[1] = 2;
                break;
        }
    }
    //size
    private void radiogroup3(int radBtnId) {
        switch (radBtnId) {
            case R.id.gram3radbtn:
                gotoCheckoutIntent.putExtra("size", "300 g");
                price[2] = 1;
                break;
            case  R.id.gram5radbtn:
                gotoCheckoutIntent.putExtra("size", "500 g");
                price[2] = 2;
                break;
        }
    }
    private void radiogroup4(int radBtnId) {
        switch (radBtnId) {
            case R.id.chocolateradbtn:
                gotoCheckoutIntent.putExtra("flavor", "Chocolate");
                gotoCheckoutIntent.putExtra("ImageFlavour", R.drawable.chocolate_80);
                price[3] = 1;
                break;
            case  R.id.strawberryradbtn:
                gotoCheckoutIntent.putExtra("flavor", "Strawberry");
                gotoCheckoutIntent.putExtra("ImageFlavour", R.drawable.strawberry_80);
                price[3] = 1;
                break;
            case  R.id.butterscotchradbtn:
                gotoCheckoutIntent.putExtra("flavor", "Butterscotch");
                gotoCheckoutIntent.putExtra("ImageFlavour", R.drawable.butterscotch);
                price[3] = 2;
                break;
        }
    }
    private float totalPrice() {
        float total = 0;

        for(int i=0;i< price.length;i++)
        {
            total=total+price[i];
        }
        return total;
    }

}



