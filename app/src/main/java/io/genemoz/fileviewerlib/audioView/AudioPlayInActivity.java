package io.genemoz.fileviewerlib.audioView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;
import java.util.Objects;

import io.genemoz.fileviewerlib.R;
import io.genemoz.fileviewerlib.databinding.ActivityAudioPlayInBinding;

public class AudioPlayInActivity extends AppCompatActivity {


    ActivityAudioPlayInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAudioPlayInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Audio Title");

        try {
            binding.audioPlayer.setDataSource("/storage/emulated/0/Test/AUD.mp3/");
            binding.audioPlayer.play();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


}