package com.gx.zbar;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class TestScanActivity extends AppCompatActivity implements QRCodeView.Delegate {

    private ZXingView mZXingView;
    private static final String TAG = TestScanActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scan);
        mZXingView = findViewById(R.id.zxingview);
        //打开后置摄像头
        mZXingView.startCamera();
        //监听
        mZXingView.setDelegate(this);
        //开始扫描
        mZXingView.startSpot();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mZXingView.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mZXingView.startSpot();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        //扫描的结果也就是result   只要开始扫描就会调用这个方法  吐司
        Toast.makeText(TestScanActivity.this,""+result,Toast.LENGTH_SHORT).show();
        TestScanActivity.this.finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
    }
}
