package io.genemoz.fileviewer;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import io.genemoz.fileviewer.videoView.AspectRatioFrameLayout;
import io.genemoz.fileviewer.videoView.CustomVideoView;
import io.genemoz.fileviewer.videoView.VideoMediaController;

public class FileViewer {


    public static class CustomVideoViewerDialog {

        Activity activity;
        Dialog dialog;
        CustomVideoView customVideoView;
        VideoMediaController videoMediaController;
        AspectRatioFrameLayout videoLayout;

        ImageView closeIcon;


        public CustomVideoViewerDialog(Activity activity) {
            this.activity = activity;
            dialog = new Dialog(activity);

            init();
        }

        public void init() {
            dialog.setContentView(R.layout.custom_video_viewer_dialog_layout);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            // full screen
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


            customVideoView = dialog.findViewById(R.id.videoView);
            videoMediaController = dialog.findViewById(R.id.media_controller);
            videoLayout = dialog.findViewById(R.id.video_layout);

            videoLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // Ensure we call this only once
                    videoLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    int width = videoLayout.getWidth(); // Get actual width
                    int height = width * 16 / 9; // Calculate height based on 9:16 ratio

                    // Create new layout params and apply them to the layout
                    ViewGroup.LayoutParams layoutParams = videoLayout.getLayoutParams();
                    layoutParams.width = width;
                    layoutParams.height = height;
                    videoLayout.setLayoutParams(layoutParams);
                }
            });

            customVideoView.setMediaController(videoMediaController);

            customVideoView.setVideoViewCallback(new CustomVideoView.VideoViewCallback() {
                @Override
                public void onScaleChange(boolean isFullscreen) {
                    Log.e("TAG", "onScaleChange: " + "isFullscreen: " + isFullscreen);
                }

                @Override
                public void onPause(MediaPlayer mediaPlayer) {
                    Log.e("TAG", "onPause: ");
                }

                @Override
                public void onStart(MediaPlayer mediaPlayer) {
                    Log.e("TAG", "onStart: ");
                }

                @Override
                public void onBufferingStart(MediaPlayer mediaPlayer) {
                    Log.e("TAG", "onBufferingStart: ");
                }

                @Override
                public void onBufferingEnd(MediaPlayer mediaPlayer) {
                    Log.e("TAG", "onBufferingEnd: ");
                }
            });

            closeIcon = dialog.findViewById(R.id.close_icon_video_view);

            closeIcon.setOnClickListener(v -> {
                dialog.dismiss();
            });

        }

        public void setCancelable(boolean cancelable) {
            dialog.setCancelable(cancelable);
        }

        public void setVideoPath(String videoPath) {
            customVideoView.setVideoPath(videoPath);
        }

        public void setVideoUri(Uri videoUri) {
            customVideoView.setVideoURI(videoUri);
        }

        public void setVideoTitle(String videoTitle) {
            videoMediaController.setTitle(videoTitle);
        }

        public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
            customVideoView.setOnCompletionListener(onCompletionListener);
        }



        public void show() {
            dialog.show();
            customVideoView.requestFocus();
            customVideoView.start();
        }

        public void dismiss() {
            dialog.dismiss();
        }

    }


}
