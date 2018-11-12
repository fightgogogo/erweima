package com.gx.gaoxin20181112;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.gx.gaoxin20181112.activity.TwoActivity;
import com.gx.gaoxin20181112.presenter.LoginImpl;
import com.gx.gaoxin20181112.view.LoginView;

//搭建MVP框架
//分包
public class MainActivity extends AppCompatActivity implements LoginView {

    private EditText name,pwd;
    private Button login;
    private CheckBox checkBox;
    private LoginImpl loginimpl;
    private SharedPreferences sp;
    private String username;
    private String password;
    private Intent intent;
    private String name1;
    private String pwd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find  view
        name = findViewById(R.id.name);
        pwd= findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        checkBox = findViewById(R.id.remeber);
        loginimpl = new LoginImpl(this);
        intent = new Intent(MainActivity.this,TwoActivity.class);
        //得到sp
        sp = getSharedPreferences("text", Context.MODE_PRIVATE);
        //首先判断是否已经登录过
        name1 = sp.getString("name", "1");
        pwd1 = sp.getString("pwd", "1");
        if (name1.equals("1")&& pwd1.equals("1")){
            //得到输入的用户名和密码
            username = MainActivity.this.name.getText().toString().trim();
            password = MainActivity.this.pwd.getText().toString().trim();
        }
        else {
            name.setText(name1);
            pwd.setText(pwd1);
        }

        //点击登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = MainActivity.this.name.getText().toString().trim();
                password = MainActivity.this.pwd.getText().toString().trim();
                loginimpl.LoginRemeber(username, password);
            }
        });

    }

    @Override
    public void Succss(String result) {
        if (checkBox.isChecked()){
              ////获取编辑器\
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("name",username);
            edit.putString("pwd",password);
            //提交修改
            edit.commit();
        }
        //跳转页面
        startActivity(intent);
    }

    @Override
    public void Failer(String result) {
        Toast.makeText(MainActivity.this,"用户名或密码错误的提示",Toast.LENGTH_SHORT).show();
    }
}
