package io.genemoz.fileviewerlib.audioView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import io.genemoz.fileviewer.FileViewer;
import io.genemoz.fileviewerlib.databinding.ActivityAudioPlayerBinding;

public class ActivityAudioPlayer extends AppCompatActivity {


    ActivityAudioPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAudioPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.audioPlayDialog.setOnClickListener(v -> {
            FileViewer.CustomAudioPlayerDialog dialog = new FileViewer.CustomAudioPlayerDialog(this);
            dialog.setTitle("Audio Title");
            try {
                dialog.setDataSource("/storage/emulated/0/Test/AUD.mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialog.setCancelable(false);
            dialog.show();
        });


        binding.audioPlayActivity.setOnClickListener(v -> {
            startActivity(new android.content.Intent(this, AudioPlayInActivity.class));
        });


    }
}