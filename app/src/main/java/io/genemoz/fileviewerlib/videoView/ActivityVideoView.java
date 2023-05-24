package io.genemoz.fileviewerlib.videoView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

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
            dialog.setVideoPath("/storage/emulated/0/Test/FB.mp4");
            dialog.setCancelable(false);
            dialog.setVideoTitle(new File("/storage/emulated/0/Download/FB Downloader/A.mp4").getName());
            dialog.setOnCompletionListener(mp -> dialog.dismiss());
            dialog.show();
        });


        binding.videoViewActivity.setOnClickListener(v -> {
            startActivity(new android.content.Intent(this, VideoViewInActivity.class));
        });


    }
}