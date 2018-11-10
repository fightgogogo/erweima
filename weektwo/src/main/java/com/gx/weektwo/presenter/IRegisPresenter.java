package com.gx.weektwo.presenter;

import com.gx.weektwo.modle.LoginModle;
import com.gx.weektwo.modle.RegisModle;
import com.gx.weektwo.view.RegisView;

public class IRegisPresenter implements RegisPresenter{


    private RegisView regisView;
    RegisModle regisModle;


    public IRegisPresenter(RegisView regisView){
        this.regisView=regisView;
        regisModle=new RegisModle();
    }

    @Override
    public void regis(String url, String tel, String pwd) {
        regisModle.Regis(url, tel, pwd, new RegisModle.CallBack() {
            @Override
            public void Setdata(String s) {
                if (s.equals("天呢！用户已注册")){
                    regisView.RegisHave("天呢！用户已注册");
                }
                else if (s.equals("请输入正确的手机号码")){
                    regisView.RegisTel("请输入正确的手机号码");
                }
                else if (s.equals("密码格式有问题，不能少于6位数")){
                    regisView.RegisPwd("密码格式有问题，不能少于6位数");
                }
                else if (s.equals("注册成功")){
                    regisView.RegisSuccess("注册成功");
                }
            }
        });
    }

    @Override
    public void regisNull() {
        regisView.RegisNull("输入的密码账号不能为空");
    }
}
