package io.genemoz.fileviewerlib;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import io.genemoz.fileviewerlib.databinding.ActivityMainBinding;
import io.genemoz.fileviewerlib.pdfView.ActivityPdfView;
import io.genemoz.fileviewerlib.videoView.ActivityVideoView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.videoViewCard.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityVideoView.class));
        });

        binding.pdfViewCard.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityPdfView.class));
        });

        binding.audioViewCard.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityAudioView.class));
        });


    }
}