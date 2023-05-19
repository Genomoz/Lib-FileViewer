package io.genemoz.fileviewer;

import android.app.Activity;
import android.app.Dialog;
import android.widget.ImageView;

public class FileViewer {


    public static class CustomVideoViewerDialog {

        Activity activity;
        Dialog dialog;

        ImageView closeIcon;

        public CustomVideoViewerDialog(Activity activity) {
            this.activity = activity;
            dialog = new Dialog(activity);

            init();
        }

        public void init() {
            dialog.setContentView(R.layout.custom_video_viewer_dialog_layout);
            dialog.setCancelable(false);

            closeIcon = dialog.findViewById(R.id.close_icon_video_view);

            closeIcon.setOnClickListener(v -> {
                dialog.dismiss();
            });

        }

        public void setCancelable(boolean cancelable) {
            dialog.setCancelable(cancelable);
        }


        public void show() {
            dialog.show();
        }

    }


}
