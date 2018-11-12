package com.gx.gaoxin20181112.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.gx.gaoxin20181112.R;
import com.gx.gaoxin20181112.adapter.MyNineAdapter;
import com.gx.gaoxin20181112.adapter.MyTwoAdapter;
import com.gx.gaoxin20181112.bean.Nine;
import com.gx.gaoxin20181112.bean.Two;
import com.gx.gaoxin20181112.presenter.TwoPresenterIm;
import com.gx.gaoxin20181112.utils.UtilsUrl;
import com.gx.gaoxin20181112.view.TwoView;

import java.util.List;

public class TwoActivity extends AppCompatActivity implements TwoView {

    private ImageView imageView;
    private GridView gridView,twogridview;
    private MyNineAdapter myNineAdapter;
    private TwoPresenterIm twoPresenterIm;
    private MyTwoAdapter myTwoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        //find view
        imageView = findViewById(R.id.erweima);
        gridView = findViewById(R.id.gridview);
        twogridview = findViewById(R.id.twogridview);
        //点击
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TwoActivity.this,ThreeActivity.class));
            }
        });
        twoPresenterIm = new TwoPresenterIm(this);
        twoPresenterIm.SetUrl(UtilsUrl.NINE);
    }
    //返回来的数据
    @Override
    public void NineData(List<Nine.DataBean> data) {
        myNineAdapter = new MyNineAdapter(TwoActivity.this, data);
        gridView.setAdapter(myNineAdapter);
        twoPresenterIm.SetUrl(UtilsUrl.TWO);
    }

    @Override
    public void TwoData(List<Two.DataBean> data) {
        myTwoAdapter = new MyTwoAdapter(TwoActivity.this, data);
        twogridview.setAdapter(myTwoAdapter);
    }
}
