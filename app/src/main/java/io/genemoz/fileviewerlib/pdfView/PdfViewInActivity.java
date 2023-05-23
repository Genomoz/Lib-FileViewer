package io.genemoz.fileviewerlib.pdfView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

import io.genemoz.fileviewerlib.R;
import io.genemoz.fileviewerlib.databinding.ActivityPdfViewBinding;
import io.genemoz.fileviewerlib.databinding.ActivityPdfViewInActivityBinding;

public class PdfViewInActivity extends AppCompatActivity {

    ActivityPdfViewInActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfViewInActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Objects.requireNonNull(getSupportActionBar()).setTitle("PDF View In Activity");



    }
}