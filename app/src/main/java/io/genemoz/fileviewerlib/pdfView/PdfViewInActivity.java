package io.genemoz.fileviewerlib.pdfView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Objects;

import io.genemoz.fileviewerlib.databinding.ActivityPdfViewInActivityBinding;

public class PdfViewInActivity extends AppCompatActivity {

    ActivityPdfViewInActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfViewInActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pdfView.fromFile(new File("/storage/emulated/0/Test/Hello.pdf"))
                .enableSwipe(true)
                .swipeHorizontal(false)
                .defaultPage(0)
                .load();

           Objects.requireNonNull(getSupportActionBar()).setTitle("Test PDF");


    }
}