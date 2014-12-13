package com.tanpn.unicodetools.threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JLabel;

import com.tanpn.unicodetools.util.FileUtil;

public class UnescapeUnicodeThread extends Thread
{
	private final File textFile;
 	private final JLabel label;
	
	public UnescapeUnicodeThread (File textFile, JLabel label)
	{
		this.textFile = textFile;
		this.label = label;
	}
	
	public void run ()
	{
		String rawFileStr = FileUtil.readTextFile(textFile.getPath());
		Path p = Paths.get(textFile.getPath());
		
		String unescStr = FileUtil.unicodeUnescape(rawFileStr);
		String unescFileName = FileUtil.getFileNameWithoutExtension(textFile) + "_txt." +
							   FileUtil.getFileExtension(textFile.getPath());
		String unescFile = p.getParent() + File.separator + unescFileName;
		
		try
		{
			PrintWriter writer = new PrintWriter(unescFile, "UTF-8");
			writer.println(unescStr);
			writer.close();
			label.setText("<html>Successfully convert to unescape string!<br>"
						  + unescFileName + "</html>");
		}
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
}
