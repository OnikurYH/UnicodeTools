package com.tanpn.unicodetools.handler;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class FileTransferHandlerBase extends TransferHandler
{
	protected File textFile;
	protected JLabel label;

	public int getSourceActions(JComponent c)
	{
	   return COPY_OR_MOVE;
	}

	public boolean canImport(TransferSupport ts)
	{
	   return ts.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
	}

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
	      label.setText(textFile.getPath());
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
