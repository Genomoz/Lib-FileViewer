package io.genemoz.fileviewerlib.pdfView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import io.genemoz.fileviewer.FileViewer;
import io.genemoz.fileviewerlib.R;
import io.genemoz.fileviewerlib.databinding.ActivityPdfViewBinding;
import io.genemoz.fileviewerlib.videoView.VideoViewInActivity;

public class ActivityPdfView extends AppCompatActivity {

    ActivityPdfViewBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pdfViewDialog.setOnClickListener(v -> {
            FileViewer.CustomPDFViewerDialog dialog = new FileViewer.CustomPDFViewerDialog(this);
            dialog.show();
        });


        binding.pdfViewActivity.setOnClickListener(v -> {
            startActivity(new android.content.Intent(this, VideoViewInActivity.class));
        });
    }
}