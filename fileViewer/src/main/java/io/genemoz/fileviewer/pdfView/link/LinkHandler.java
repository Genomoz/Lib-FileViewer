package io.genemoz.fileviewer.pdfView.link;


import io.genemoz.fileviewer.pdfView.model.LinkTapEvent;

public interface LinkHandler {

    /**
     * Called when link was tapped by user
     *
     * @param event current event
     */
    void handleLinkEvent(LinkTapEvent event);
}
