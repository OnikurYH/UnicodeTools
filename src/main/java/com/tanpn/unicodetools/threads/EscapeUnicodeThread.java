package com.tanpn.unicodetools.threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JLabel;

import com.tanpn.unicodetools.util.FileUtil;

public class EscapeUnicodeThread extends Thread
{
	private final File textFile;
 	private final JLabel label;
 	
 	public EscapeUnicodeThread (File textFile, JLabel label)
 	{
 		this.textFile = textFile;
 		this.label = label;
 	}
 	
 	public void run ()
	{
 		String rawFileStr = FileUtil.readTextFile(textFile.getPath());
		Path p = Paths.get(textFile.getPath());
 		
 		String escStr = FileUtil.unicodeEscape(rawFileStr);
 		String escFileName = FileUtil.getFileNameWithoutExtension(textFile) + "_esc." +
				   FileUtil.getFileExtension(textFile.getPath());
		String escFile = p.getParent() + File.separator + escFileName;
		
		try
		{
			PrintWriter writer = new PrintWriter(escFile, "UTF-8");
			writer.println(escStr);
			writer.close();
			label.setText("<html>Successfully convert to escape string!<br>"
						  + escFileName + "</html>");
		}
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
}
