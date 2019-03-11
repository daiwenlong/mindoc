package com.dwl.mindoc.service;

import java.io.IOException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;

public class TextFooterEventHandler implements IEventHandler {

	private Document doc;
    private int page;

	public TextFooterEventHandler(Document doc) {
		this.doc = doc;
	}

	public void handleEvent(Event event) {
		PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
		PdfCanvas canvas = new PdfCanvas(docEvent.getPage());
		Rectangle pageSize = docEvent.getPage().getPageSize();
		page++;
		canvas.beginText();
		try {
			canvas.setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE), 15);
		} catch (IOException e) {
			e.printStackTrace();
		}
		canvas.moveText((pageSize.getRight() - doc.getRightMargin() + (pageSize.getLeft() + doc.getLeftMargin())) / 2,
				pageSize.getBottom() + doc.getBottomMargin()).showText("" + page).endText().release();
	}

}
