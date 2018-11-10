package com.gx.weektwo.presenter;

import com.gx.weektwo.modle.LoginModle;
import com.gx.weektwo.view.LoginView;

public class ILoginPresenter implements LoginPresenter{

    //使presenter层和modle层关联
    private LoginView loginView;
    private LoginModle loginModle;

    //使presenter层和view层关联
    public ILoginPresenter(LoginView loginView){
        this.loginView=loginView;
        loginModle=new LoginModle();
    }

    //如果不为空则到modle层请求数据
    @Override
    public void login(String url,String tel, String pwd) {
        loginModle.Login(url, tel, pwd, new LoginModle.CallBack() {
            @Override
            public void Setdata(String s) {
                if (s.equals("登录成功")){
                    loginView.Success("登录成功");
                }
                else if (s.equals("天呢！密码错误")){
                    loginView.Failer("密码或者账号错误");
                }
                else if (s.equals("请输入正确的手机号码")){
                    loginView.TelFailer("请输入正确的手机号码");
                }
                else if (s.equals("密码格式有问题，不能少于6位数")){
                    loginView.PwdFailer("密码格式有问题，不能少于6位数");
                }

            }
        });
    }
    //如果为空则不用请求modle层 直接调用view层的方法掉给界面吐司显示
    @Override
    public void loginNull() {
            loginView.Null("输入的密码和账号不能为空");
    }
}
