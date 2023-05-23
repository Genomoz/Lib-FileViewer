
package io.genemoz.fileviewer.pdfView.listener;

/**
 * Implements this interface to receive events from PDFView
 * when a page has been scrolled
 */
public interface OnPageScrollListener {

    /**
     * Called on every move while scrolling
     *
     * @param page current page index
     * @param positionOffset see {@link io.genemoz.fileviewer.pdfView.PDFView#getPositionOffset()}
     */
    void onPageScrolled(int page, float positionOffset);
}
