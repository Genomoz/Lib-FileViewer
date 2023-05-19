package io.genemoz.fileviewerlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.genemoz.fileviewerlib.databinding.ActivityVideoViewBinding;

public class ActivityVideoView extends AppCompatActivity {

    ActivityVideoViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}