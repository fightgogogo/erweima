package com.gx.gaoxin20181112.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.gx.gaoxin20181112.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ThreeActivity extends AppCompatActivity implements QRCodeView.Delegate{

    private ZXingView zXingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //find view
        setContentView(R.layout.activity_three);
        zXingView = findViewById(R.id.zxingview);
        //打开后置摄像头
        zXingView.stopCamera();
        //监听
        zXingView.setDelegate(this);
        //开始扫描
        zXingView.startSpot();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zXingView.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        zXingView.startSpot();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(ThreeActivity.this,""+result,Toast.LENGTH_SHORT).show();
        ThreeActivity.this.finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
