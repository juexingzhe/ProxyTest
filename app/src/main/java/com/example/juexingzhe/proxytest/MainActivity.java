package com.example.juexingzhe.proxytest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.bind_view_btn)
    Button mBindView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.injectView(this);
        Utils.injectEvent(this);
    }

    @onClick({R.id.bind_click_btn, R.id.bind_view_btn})
    public void InvokeBtnClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        switch (view.getId()) {
            case R.id.bind_click_btn:
                Log.i(Utils.TAG, "bind_click_btn Click");

                builder.setTitle(this.getClass().getSimpleName())
                        .setMessage("button onClick")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                break;
            case R.id.bind_view_btn:
                Log.i(Utils.TAG, "bind_view_btn Click");

                builder.setTitle(this.getClass().getSimpleName())
                        .setMessage("button binded")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                break;

        }
    }

}
