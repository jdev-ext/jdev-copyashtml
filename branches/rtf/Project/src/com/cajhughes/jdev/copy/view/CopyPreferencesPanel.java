package com.cajhughes.jdev.copy.view;

import com.cajhughes.jdev.copy.model.CopyPreferences;
import com.cajhughes.jdev.copy.view.resource.CopyResourceUtil;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import oracle.ide.panels.DefaultTraversablePanel;
import oracle.ide.panels.TraversableContext;
import oracle.ide.panels.TraversalException;
import oracle.ide.util.ResourceUtils;

/**
 * This class extends the DefaultTraversablePanel, so that the JPanel for the
 * preferences associated with the CopyAsHtml action can be traversed within
 * the Preferences dialog.
 *
 * @author Chris Hughes
 */
public final class CopyPreferencesPanel extends DefaultTraversablePanel {
    private final JLabel heading = new JLabel();
    private final JRadioButton codeSnippet = new JRadioButton();
    private final JRadioButton preSnippet = new JRadioButton();
    private final JRadioButton codeMarkupSnippet = new JRadioButton();
    private final JRadioButton fullSnippet = new JRadioButton();
    private final JRadioButton rtfSnippet = new JRadioButton();

    public CopyPreferencesPanel() {
        initialiseResources();
        layoutControls();
        initialiseControls();
    }

    protected void initialiseResources() {
        ResourceUtils.resLabel(heading, heading, CopyResourceUtil.getString("CopyPanelHeading"));
        ResourceUtils.resButton(codeSnippet, CopyResourceUtil.getString("CopyPanelCodeOption"));
        ResourceUtils.resButton(preSnippet, CopyResourceUtil.getString("CopyPanelPreOption"));
        ResourceUtils.resButton(codeMarkupSnippet, CopyResourceUtil.getString("CopyPanelCodeMarkupOption"));
        ResourceUtils.resButton(fullSnippet, CopyResourceUtil.getString("CopyPanelFullOption"));
        ResourceUtils.resButton(rtfSnippet, CopyResourceUtil.getString("CopyPanelRTFOption"));
    }

    protected void layoutControls() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.anchor = 17;
        c.weightx = 1.0d;
        c.weighty = 0.0d;
        c.insets = new Insets(0, 0, 5, 0);
        add(heading, c);
        c.gridy++;
        c.gridwidth = 1;
        c.weightx = 0.0d;
        c.insets = new Insets(0, 24, 5, 5);
        add(codeSnippet, c);
        c.gridy++;
        add(preSnippet, c);
        c.gridy++;
        add(codeMarkupSnippet, c);
        c.gridy++;
        add(fullSnippet, c);
        c.gridy++;
        add(rtfSnippet, c);
       c.gridy++;
        c.weighty = 1.0d;
        add(new JPanel(), c);
    }

    protected void initialiseControls() {
        ButtonGroup group = new ButtonGroup();
        group.add(codeSnippet);
        group.add(preSnippet);
        group.add(codeMarkupSnippet);
        group.add(fullSnippet);
        group.add(rtfSnippet);
    }

    @Override
    public void onEntry(final TraversableContext context) {
        CopyPreferences prefs = (CopyPreferences)context.find(CopyPreferences.KEY);
        if (prefs != null) {
            codeSnippet.setSelected(prefs.getCopyFormat() == CopyPreferences.CODE);
            preSnippet.setSelected(prefs.getCopyFormat() == CopyPreferences.PRE);
            codeMarkupSnippet.setSelected(prefs.getCopyFormat() == CopyPreferences.CODEMARKUP);
            fullSnippet.setSelected(prefs.getCopyFormat() == CopyPreferences.FULL);
            rtfSnippet.setSelected(prefs.getCopyFormat() == CopyPreferences.RTF);
        }
    }

    @Override
    public void onExit(final TraversableContext context) throws TraversalException {
        CopyPreferences prefs = (CopyPreferences)context.find(CopyPreferences.KEY);
        if (prefs != null) {
            if (codeSnippet.isSelected()) {
                prefs.setCopyFormat(CopyPreferences.CODE);
            }
            else if (preSnippet.isSelected()) {
                prefs.setCopyFormat(CopyPreferences.PRE);
            }
            else if (codeMarkupSnippet.isSelected()) {
                prefs.setCopyFormat(CopyPreferences.CODEMARKUP);
            }
            else if (fullSnippet.isSelected()) {
                prefs.setCopyFormat(CopyPreferences.FULL);
            }
            else if (rtfSnippet.isSelected()) {
                prefs.setCopyFormat(CopyPreferences.RTF);
            }
        }
    }
}