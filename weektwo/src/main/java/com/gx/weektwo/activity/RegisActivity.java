package com.gx.weektwo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gx.weektwo.MainActivity;
import com.gx.weektwo.R;
import com.gx.weektwo.presenter.ILoginPresenter;
import com.gx.weektwo.presenter.IRegisPresenter;
import com.gx.weektwo.url.UrlUtils;
import com.gx.weektwo.view.RegisView;

public class RegisActivity extends AppCompatActivity implements RegisView {

    private EditText regispwd,registel;
    private Button regisbut;

    private IRegisPresenter iRegisPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        regispwd = findViewById(R.id.regispwd);
        registel = findViewById(R.id.registel);
        regisbut= findViewById(R.id.regisbut);
        iRegisPresenter=new IRegisPresenter(this);
        regisbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = registel.getText().toString().trim();
                String pwd = regispwd.getText().toString().trim();
                if (tel.equals("")||pwd.equals("")){
                    iRegisPresenter.regisNull();
                }
                else {
                    iRegisPresenter.regis(UrlUtils.REGIS,tel+UrlUtils.REGISEND,pwd);
                }
            }
        });
    }

    @Override
    public void RegisSuccess(String Successresult) {
        Toast.makeText(RegisActivity.this,""+Successresult,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisActivity.this,MainActivity.class));
    }

    @Override
    public void RegisHave(String Haveresult) {
        Toast.makeText(RegisActivity.this,""+Haveresult,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RegisNull(String Nullresult) {
        Toast.makeText(RegisActivity.this,""+Nullresult,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RegisTel(String Telresult) {
        Toast.makeText(RegisActivity.this,""+Telresult,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RegisPwd(String Pwdresult) {
        Toast.makeText(RegisActivity.this,""+Pwdresult,Toast.LENGTH_SHORT).show();
    }
}
