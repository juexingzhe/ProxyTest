package com.example.juexingzhe.proxytest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.ProxyGeneratorClass;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //生成Proxy字节码
        ProxyGeneratorClass.generateClassFile(this.getClass(), "activityProxy");
        Utils.injectEvent(this);
    }

    @onClick({R.id.test_btn})
    public void InvokeBtnClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Log.i("ProxyTest", "onClick");


        builder.setTitle(this.getClass().getSimpleName())
                .setMessage("buttton onClick")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

    }

}
