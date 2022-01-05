package com.huawei.hms.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;
import com.stripe.android.model.PaymentIntent;


public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        PaymentConfiguration.init(
                getApplicationContext(),"pk_test_51KDtMuCNVJfAhtRn3pQEoCEiw3CFEHLE3C4xirdIcp2dAYrv9J1Yqif1pIfwGMZekuy8wDINFVW190j9dx3dxecE00VLYjRjoJ"
        );
    }
private void paymentMethod(String price) {
//
//    // Set your secret key. Remember to switch to your live secret key in production.
//// See your keys here: https://dashboard.stripe.com/apikeys
//    Stripe.apiKey = "sk_test_51KDtMuCNVJfAhtRnze2qLIrgPWWDzIJWbtd5S5isKeJ7UWHmnxROpqeaVb2Tj2d2C7pcEE6rZQsyzFPuNT5YuLYD00jmn8GMTZ";
//
//    PaymentIntentCreateParams params =
//            PaymentIntentCreateParams
//                    .builder()
//                    .addPaymentMethodType("fpx")
//                    .setAmount(1099L)
//                    .setCurrency("myr")
//                    .build();
//
//    PaymentIntent paymentIntent = PaymentIntent.create(params);

}

}