package com.cajhughes.jdev.copy.controller;

import com.cajhughes.jdev.util.ClipboardUtil;
import com.cajhughes.jdev.util.EditorUtil;
import com.cajhughes.jdev.util.NodeUtil;
import oracle.ide.Context;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;

/**
 * This class implements the Controller interface, and exists to update the
 * enabled status of the CopyAsHtml action based on:
 *
 * (a) The system clipboard can be accessed
 * (b) The document currently being edited is a text document
 * (c) There is an active selection within the document
 *
 * @author Chris Hughes
 */
public class CopyController implements Controller {
    @Override
    public boolean handleEvent(final IdeAction action, final Context context) {
        return false;
    }

    @Override
    public boolean update(final IdeAction action, final Context context) {
        action.setEnabled(ClipboardUtil.canBeAccessed() && NodeUtil.isTextNode(context) &&
                          EditorUtil.hasActiveSelection(context));
        return true;
    }
}
