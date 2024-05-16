package com.example.networkapplication.test.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.networkapplication.databinding.ActivityLoginBinding;
import com.example.networkapplication.test.logic.BasePresenter;
import com.example.networkapplication.test.logic.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements BaseView {
    ActivityLoginBinding activityLoginBinding;
    BasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        presenter = new LoginPresenter(this);
        activityLoginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.executeLogin(activityLoginBinding.username.getText().toString(), activityLoginBinding.password.toString());
            }
        });


    }

    @Override
    public void showTips(String tips) {
        Toast.makeText(LoginActivity.this, tips, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyLoginResult(boolean loginResult) {
        if (loginResult) {
            showTips("登录成功");
        } else {
            showTips("登录失败");
        }
    }
}