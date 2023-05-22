package io.genemoz.fileviewerlib.videoView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import io.genemoz.fileviewer.videoView.CustomVideoView;
import io.genemoz.fileviewerlib.databinding.ActivityVideoViewInActivityBinding;

public class VideoViewInActivity extends AppCompatActivity {

    private static final String VIDEO_URL = "/storage/emulated/0/Download/MP4 Downloader/Adele-Hello.mp4";
    ActivityVideoViewInActivityBinding binding;
    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoViewInActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setVideoAreaSize();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.videoView.start();
                    }
                });
            }
        }, 1000);


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
                binding.videoView.setVideoPath(VIDEO_URL);
                binding.videoView.requestFocus();
            }
        });
    }


}