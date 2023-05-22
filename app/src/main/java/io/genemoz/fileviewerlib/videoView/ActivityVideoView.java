package io.genemoz.fileviewerlib.videoView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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


        binding.videoViewActivity.setOnClickListener(v -> {
            startActivity(new android.content.Intent(this, VideoViewInActivity.class));
        });


    }
}