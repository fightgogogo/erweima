package com.gx.gaoxin20181112.presenter;

import com.gx.gaoxin20181112.bean.Nine;
import com.gx.gaoxin20181112.bean.Two;
import com.gx.gaoxin20181112.modle.TwoModle;
import com.gx.gaoxin20181112.view.TwoView;

import java.util.List;

public class TwoPresenterIm implements TwoPresenter {

    TwoView twoView;
    TwoModle twoModle;
    public TwoPresenterIm(TwoView twoView) {
        this.twoView = twoView;
        twoModle=new TwoModle();
    }

    @Override
    public void SetUrl(String url) {
        twoModle.getdata(url, new TwoModle.CallBack() {
            @Override
            public void SetData(List<Nine.DataBean> data) {
                twoView.NineData(data);
            }

            @Override
            public void SetTwoData(List<Two.DataBean> data) {
                twoView.TwoData(data);
            }
        });
    }

}
