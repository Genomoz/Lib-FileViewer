package io.genemoz.fileviewerlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.genemoz.fileviewer.FileViewer;
import io.genemoz.fileviewerlib.databinding.ActivityVideoViewBinding;

public class ActivityVideoView extends AppCompatActivity {

    ActivityVideoViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.videoViewDialog.setOnClickListener(v -> {
            FileViewer.CustomVideoViewerDialog dialog = new FileViewer.CustomVideoViewerDialog(this);
            dialog.show();
        });


    }
}