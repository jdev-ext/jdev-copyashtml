package com.cajhughes.jdev.copy.view;

import com.cajhughes.jdev.copy.model.CopyPreferences;
import java.io.IOException;
import java.io.Writer;
import javax.swing.text.BadLocationException;
import oracle.ide.Ide;
import oracle.ide.ceditor.options.PrintingHTMLOptions;
import oracle.javatools.editor.BasicDocument;
import oracle.javatools.editor.print.DocumentToHTMLGenerator;
import oracle.javatools.editor.print.HTMLGeneratorOptions;

/**
 * This class handles the choice of which output format to generate, based on
 * the current preference, and calls the generator to write the HTML output
 * into the stream.
 *
 * @author Chris Hughes
 */
public class Formatter {
    private String filename = null;
    private String text = null;

    public Formatter(final String filename, final String text) {
        this.filename = filename;
        this.text = text;
    }

    public void format(final Writer writer, final CopyPreferences prefs) throws BadLocationException, IOException {
        BasicDocument document = new BasicDocument(filename);
        document.insertString(0, text, null);
        int format = prefs.getCopyFormat();
        if (format == CopyPreferences.CODE) {
            HtmlGenerator generator = new HtmlGenerator(document);
            generator.generateCode(writer);
        }
        else if (format == CopyPreferences.PRE) {
            HtmlGenerator generator = new HtmlGenerator(document);
            generator.generatePre(writer);
        }
        else if (format == CopyPreferences.FULL) {
            DocumentToHTMLGenerator generator = new DocumentToHTMLGenerator(document, null);
            generator.generateHTML(writer, getOptions());
        }
        else if (format == CopyPreferences.CODEMARKUP) {
            HtmlGenerator generator = new HtmlGenerator(document);
            generator.generateCodeMarkup(writer);
        }
        else if (format == CopyPreferences.RTF) {
            RtfGenerator generator = new RtfGenerator(document, prefs);
            generator.generateRTF(writer);
        }
    }

    public static HTMLGeneratorOptions getOptions() {
        HTMLGeneratorOptions result = new HTMLGeneratorOptions();
        PrintingHTMLOptions printOptions =
            (PrintingHTMLOptions)Ide.getSettings().getData(PrintingHTMLOptions.KEY_SETTINGS);
        if (printOptions != null) {
            result.setCharsetEncoding(printOptions.getCharsetEncoding());
            result.setFontFamily(printOptions.getFontFamily());
            result.setFontSize(printOptions.getFontSize());
            result.setPrintColors(printOptions.getPrintColors());
            result.setPrintFileHeader(printOptions.getPrintFileHeader());
            result.setPrintFontStyles(printOptions.getPrintFontStyles());
            result.setPrintLineNumbers(printOptions.getPrintLineNumbers());
            result.setPrintTimeStamp(printOptions.getPrintTimeStamp());
            result.setUseAnchors(printOptions.getUseAnchors());
            result.setUseFontInfo(printOptions.getUseFontInfo());
            result.setWrapBehavior(printOptions.getWrapBehavior());
            result.setWrapColumn(printOptions.getWrapColumn());
            result.setWrapSymbol(printOptions.getWrapSymbol());
        }
        return result;
    }
}