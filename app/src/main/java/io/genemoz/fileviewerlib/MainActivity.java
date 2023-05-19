package io.genemoz.fileviewerlib;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import io.genemoz.fileviewerlib.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}