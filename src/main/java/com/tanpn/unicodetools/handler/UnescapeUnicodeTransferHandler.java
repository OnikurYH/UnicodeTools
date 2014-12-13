package com.tanpn.unicodetools.handler;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;

import com.tanpn.unicodetools.threads.UnescapeUnicodeThread;

@SuppressWarnings("serial")
public class UnescapeUnicodeTransferHandler extends FileTransferHandlerBase
{
	public UnescapeUnicodeTransferHandler (File textFile, JLabel label)
	{
		this.textFile = textFile;
		this.label = label;
	}
	
	@Override
	public boolean importData (TransferSupport ts)
	{
	   try
	   {
	      List<?> data = (List<?>) ts.getTransferable().getTransferData(
	            DataFlavor.javaFileListFlavor);
	      if (data.size() < 1)
	      {
	         return false;
	      }
	      
	      textFile = (File) data.get(0);
	      label.setText(textFile.getPath() + " To Text");
	      new UnescapeUnicodeThread(textFile, label).start();
	      return true;

	   }
	   catch (UnsupportedFlavorException e)
	   {
	      return false;
	   }
	   catch (IOException e)
	   {
	      return false;
	   }
	}
}
