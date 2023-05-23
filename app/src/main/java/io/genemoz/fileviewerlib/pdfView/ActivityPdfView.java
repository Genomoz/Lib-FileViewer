package io.genemoz.fileviewerlib.pdfView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

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
            dialog.setPdfTitle("PDF Title");
            dialog.setUpPdf(new File("/storage/emulated/0/Test/Hello.pdf"),true, true, 0);
            dialog.show();
        });


        binding.pdfViewActivity.setOnClickListener(v -> {
            startActivity(new android.content.Intent(this, VideoViewInActivity.class));
        });
    }
}