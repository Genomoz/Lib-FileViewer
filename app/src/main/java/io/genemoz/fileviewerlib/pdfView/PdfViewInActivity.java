package io.genemoz.fileviewerlib.pdfView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.genemoz.fileviewerlib.R;
import io.genemoz.fileviewerlib.databinding.ActivityPdfViewBinding;

public class PdfViewInActivity extends AppCompatActivity {

    ActivityPdfViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}