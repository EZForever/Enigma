/*******************************************************************************
 * Copyright (c) 2015 Jeff Martin.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Contributors:
 * Jeff Martin - initial API and implementation
 ******************************************************************************/

package cuchaz.enigma.gui.dialog;

import cuchaz.enigma.Enigma;
import cuchaz.enigma.gui.util.GuiUtil;
import cuchaz.enigma.utils.I18n;
import cuchaz.enigma.gui.util.ScaleUtil;
import cuchaz.enigma.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AboutDialog {

	public static void show(JFrame parent) {
		// init frame
		final JFrame frame = new JFrame(String.format(I18n.translate("menu.help.about.title"), Enigma.NAME));
		final Container pane = frame.getContentPane();
		pane.setLayout(new FlowLayout());

		// load the content
		try {
			String html = Utils.readResourceToString("/about.html");
			html = String.format(html, Enigma.NAME, Enigma.VERSION);
			JLabel label = new JLabel(html);
			label.setHorizontalAlignment(JLabel.CENTER);
			pane.add(label);
		} catch (IOException ex) {
			throw new Error(ex);
		}

		// show the link
		String html = "<html><a href=\"%s\">%s</a></html>";
		html = String.format(html, Enigma.URL, Enigma.URL);
		JButton link = new JButton(html);
		link.addActionListener(event -> GuiUtil.openUrl(Enigma.URL));
		link.setBorderPainted(false);
		link.setOpaque(false);
		link.setBackground(Color.WHITE);
		link.setCursor(new Cursor(Cursor.HAND_CURSOR));
		link.setFocusable(false);
		JPanel linkPanel = new JPanel();
		linkPanel.add(link);
		pane.add(linkPanel);

		// show ok button
		JButton okButton = new JButton(I18n.translate("menu.help.about.ok"));
		pane.add(okButton);
		okButton.addActionListener(arg0 -> frame.dispose());

		// show the frame
		pane.doLayout();
		frame.setSize(ScaleUtil.getDimension(400, 220));
		frame.setResizable(false);
		frame.setLocationRelativeTo(parent);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
