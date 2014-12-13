package com.tanpn.unicodetools;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import com.tanpn.unicodetools.handler.EscapeUnicodeTransferHandler;
import com.tanpn.unicodetools.handler.UnescapeUnicodeTransferHandler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UnicodeTools {

	private JFrame frmUnicodetools;
	
	private File textFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnicodeTools window = new UnicodeTools();
					window.frmUnicodetools.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UnicodeTools() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUnicodetools = new JFrame();
		frmUnicodetools.setTitle("UnicodeTools");
		frmUnicodetools.getContentPane().setBackground(Color.DARK_GRAY);
		frmUnicodetools.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmUnicodetools.getContentPane().add(tabbedPane);
		
		JPanel panelUnescape = new JPanel();
		tabbedPane.addTab("Unescape", null, panelUnescape, null);
		panelUnescape.setLayout(new BorderLayout(0, 0));
		
		JLabel lbUnescape_DropZone = new JLabel("Drop a escape text fille to here...");
		lbUnescape_DropZone.setHorizontalAlignment(SwingConstants.CENTER);
		lbUnescape_DropZone.setTransferHandler(new UnescapeUnicodeTransferHandler(textFile, lbUnescape_DropZone));
		panelUnescape.add(lbUnescape_DropZone);
		
		JPanel panelEscape = new JPanel();
		tabbedPane.addTab("Escape", null, panelEscape, null);
		panelEscape.setLayout(new BorderLayout(0, 0));
		
		JLabel lbEscape_DropZone = new JLabel("Drop a unescape text fille to here...");
		panelEscape.add(lbEscape_DropZone);
		lbEscape_DropZone.setTransferHandler(new EscapeUnicodeTransferHandler(textFile, lbEscape_DropZone));
		lbEscape_DropZone.setHorizontalAlignment(SwingConstants.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmUnicodetools.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnMain = new JMenu("UnicodeTools");
		menuBar.add(mnMain);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmUnicodetools, "UncodeTools\nDevelop by: Onikur Chan\nLicense under GNU GPL v3");
			}
		});
		menuBar.add(mntmAbout);
		frmUnicodetools.setBounds(100, 100, 499, 320);
		frmUnicodetools.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
