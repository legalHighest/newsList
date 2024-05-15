package com.example.networkapplication.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.networkapplication.R;
import com.example.networkapplication.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {

    ActivityTestBinding activityTestBinding;
    TestVM testVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTestBinding= DataBindingUtil.setContentView(this,R.layout.activity_test);
    }
}