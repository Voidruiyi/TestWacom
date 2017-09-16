package com.ruiyi.testwacom;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import widget.WacomView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WacomView mWacom;
    private ImageView mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.reset).setOnClickListener(this);
        findViewById(R.id.save).setOnClickListener(this);
        mWacom = (WacomView) findViewById(R.id.wacom);
        mShow = (ImageView) findViewById(R.id.show);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reset:
                mWacom.reset();
                break;
            case R.id.save:
                Bitmap b = mWacom.save2();
                if(b!=null){
                    mShow.setImageBitmap(b);
                }
                break;
        }

    }
}
