package com.example.toasty;

import android.content.Context;
import android.widget.Toast;

public class Toaster {

    public static void ShowToast(Context context,String message){
        Toast.makeText(context, "MESSAGE : "+message, Toast.LENGTH_SHORT).show();
    }
}
