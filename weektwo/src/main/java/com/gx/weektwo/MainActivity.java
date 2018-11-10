package com.gx.weektwo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gx.weektwo.activity.RegisActivity;
import com.gx.weektwo.activity.ShowActivity;
import com.gx.weektwo.presenter.ILoginPresenter;
import com.gx.weektwo.presenter.LoginPresenter;
import com.gx.weektwo.url.UrlUtils;
import com.gx.weektwo.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView {

    private EditText tel,pwd;
    private Button login,regis;
    private ILoginPresenter iLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //findview
        setContentView(R.layout.activity_main);
        tel = findViewById(R.id.tel);
        pwd = findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        regis = findViewById(R.id.regis);
        iLoginPresenter=new ILoginPresenter(this);
        //点击跳转注册页面
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = MainActivity.this.tel.getText().toString().trim();
                String pwd = MainActivity.this.pwd.getText().toString().trim();
                //账号或者密码为空的话
                if (tel.equals("")||pwd.equals("")){
                    iLoginPresenter.loginNull();
                }
                else {
                    iLoginPresenter.login(UrlUtils.LOGIN,tel+UrlUtils.LOGINEND,pwd);
                }

            }
        });

    }
    @Override
    public void Null(String Nullresult) {
        Toast.makeText(MainActivity.this,""+Nullresult,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Success(String Successresult) {
        //如果登录成功则吐司 并且跳转到第三个Activity
        Toast.makeText(MainActivity.this,""+Successresult,Toast.LENGTH_SHORT).show();
        //跳转到第三个Activity
        if (Successresult.equals("登录成功")){
            startActivity(new Intent(MainActivity.this,ShowActivity.class));
        }
    }

    @Override
    public void TelFailer(String Telresult) {
        Toast.makeText(MainActivity.this,""+Telresult,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void PwdFailer(String Pwdresult) {
        Toast.makeText(MainActivity.this,""+Pwdresult,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Failer(String Failerresult) {
        Toast.makeText(MainActivity.this,""+Failerresult,Toast.LENGTH_SHORT).show();
    }
}
