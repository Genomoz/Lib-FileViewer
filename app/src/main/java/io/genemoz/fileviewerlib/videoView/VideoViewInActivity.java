package io.genemoz.fileviewerlib.videoView;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import io.genemoz.fileviewer.videoView.CustomVideoView;
import io.genemoz.fileviewerlib.databinding.ActivityVideoViewInActivityBinding;

public class VideoViewInActivity extends AppCompatActivity {

    private static final String VIDEO_URL = "/storage/emulated/0/Download/FB Downloader/A.mp4";
    ActivityVideoViewInActivityBinding binding;
    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;
    private static final Uri VIDEO_URI = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia/document/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2FWhatsApp%20Video%2FVID-20230404-WA0000.mp4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoViewInActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setVideoAreaSize();

        new Handler().postDelayed(() -> runOnUiThread(() -> {
            binding.videoView.start();
            File file = new File(VIDEO_URL);
            binding.mediaController.setTitle(file.getName() + "");

        }), 1000);


        binding.videoView.setMediaController(binding.mediaController);

        binding.videoView.setVideoViewCallback(new CustomVideoView.VideoViewCallback() {
            @Override
            public void onScaleChange(boolean isFullscreen) {
                VideoViewInActivity.this.isFullscreen = isFullscreen;
                if (isFullscreen) {
                    ViewGroup.LayoutParams layoutParams = binding.videoLayout.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    binding.videoLayout.setLayoutParams(layoutParams);
                    //GONE the unconcerned views to leave room for video and controller
                    binding.bottomLayout.setVisibility(View.GONE);

                } else {
                    ViewGroup.LayoutParams layoutParams = binding.videoLayout.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = VideoViewInActivity.this.cachedHeight;
                    binding.videoLayout.setLayoutParams(layoutParams);
                    binding.bottomLayout.setVisibility(View.VISIBLE);
                }

                switchTitleBar(!isFullscreen);


            }

            @Override
            public void onPause(MediaPlayer mediaPlayer) {

            }

            @Override
            public void onStart(MediaPlayer mediaPlayer) {

            }

            @Override
            public void onBufferingStart(MediaPlayer mediaPlayer) {

            }

            @Override
            public void onBufferingEnd(MediaPlayer mediaPlayer) {

            }
        });

    }

    private void switchTitleBar(boolean b) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (b) {
                supportActionBar.show();
                Log.e("TAG", "switchTitleBar: " + "show");
            } else {
                supportActionBar.hide();
                Log.e("TAG", "switchTitleBar: " + "hide");
            }
        }
    }


    private void setVideoAreaSize() {
        binding.videoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = binding.videoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);

                ViewGroup.LayoutParams videoLayoutParams = binding.videoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                binding.videoLayout.setLayoutParams(videoLayoutParams);
                binding.videoView.setVideoURI(VIDEO_URI);
                binding.videoView.requestFocus();
            }
        });
    }


}