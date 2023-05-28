package io.genemoz.fileviewer.audioView;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.genemoz.fileviewer.R;

public class CustomAudioPlayer extends RelativeLayout {
    private ImageView playButton;
    private SeekBar positionBar;
    private TextView elapsedTimeLabel;
    private TextView remainingTimeLabel;
    private MediaPlayer mediaPlayer;
    private final Handler handler = new Handler();
    private Runnable updateSeekBarRunnable = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
        }
    };

    public CustomAudioPlayer(Context context) {
        super(context);
        init(context);
    }

    public CustomAudioPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomAudioPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_audio_player_view, this, true);
        playButton = findViewById(R.id.playButton);
        positionBar = findViewById(R.id.positionBar);
        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = findViewById(R.id.remainingTimeLabel);
        playButton.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                pause();
            } else {
                play();
            }
        });

        positionBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                    elapsedTimeLabel.setText(formatTime(progress));
                    remainingTimeLabel.setText(formatTime(mediaPlayer.getDuration() - progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
            }
        });

    }

    public void setDataSource(String path) throws IOException {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(path);
        mediaPlayer.prepare();
        positionBar.setMax(mediaPlayer.getDuration());
    }

    public void setDataSource(Uri uri) throws IOException {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(getContext(), uri);
        mediaPlayer.prepare();
        positionBar.setMax(mediaPlayer.getDuration());
    }


    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            playButton.setImageResource(R.drawable.ic_pause_audio_lib);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    handler.removeCallbacks(updateSeekBarRunnable);  // stop updating
                    playButton.setImageResource(R.drawable.ic_play_audio_lib);
                    positionBar.setProgress(0);
                    elapsedTimeLabel.setText(formatTime(0));
                    remainingTimeLabel.setText(formatTime(mediaPlayer.getDuration()));
                }
            });
            handler.post(updateSeekBarRunnable);  // start updating
        }
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            playButton.setImageResource(R.drawable.ic_play_audio_lib);
            handler.removeCallbacks(updateSeekBarRunnable);  // stop updating
        }
    }

    private void updateSeekBar() {
        if (mediaPlayer != null) {
            positionBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(updateSeekBarRunnable, 1000);
            elapsedTimeLabel.setText(formatTime(mediaPlayer.getCurrentPosition()));
            remainingTimeLabel.setText(formatTime(mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition()));
        }
    }

    private String formatTime(int millis) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(minutes);
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            handler.removeCallbacks(updateSeekBarRunnable);  // stop updating
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
