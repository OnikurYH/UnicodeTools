package com.tanpn.unicodetools.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil
{
	private static final char[] hexChar = {
        '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };
	
	public static String readTextFile (String filePath)
	{
		String str = null;
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(filePath));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null)
		    {
		        sb.append(line);
		        sb.append("\n");
		        line = br.readLine();
		    }
		    str = sb.toString();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
		    try
		    {
				br.close();
			}
		    catch (IOException e)
		    {
				e.printStackTrace();
			}
		}
		
		return str;
	}
	
	public static String getFileNameWithoutExtension (File file)
	{
		String path = file.getName();
        if (path == null) return null;
        int pos = path.lastIndexOf(".");
        if (pos == -1) return path;
        return path.substring(0, pos);
	}
	
	public static String getFileExtension (String path)
	{
		String extension = "";

		int i = path.lastIndexOf('.');
		int p = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));

		if (i > p) {
		    extension = path.substring(i+1);
		}
		
		return extension;
	}
	
	public static String unicodeEscape(String s)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
		    char c = s.charAt(i);
		    if ((c >> 7) > 0) {
			sb.append("\\u");
			sb.append(hexChar[(c >> 12) & 0xF]); // append the hex character for the left-most 4-bits
			sb.append(hexChar[(c >> 8) & 0xF]);  // hex for the second group of 4-bits from the left
			sb.append(hexChar[(c >> 4) & 0xF]);  // hex for the third group
			sb.append(hexChar[c & 0xF]);         // hex for the last group, e.g., the right most 4-bits
		    }
		    else {
			sb.append(c);
		    }
		}
		return sb.toString();
	    }
	
	public static String unicodeUnescape (String st)
	{
	    StringBuilder sb = new StringBuilder(st.length());
	 
	    for (int i = 0; i < st.length(); i++)
	    {
	        char ch = st.charAt(i);
	        if (ch == '\\') {
	            char nextChar = (i == st.length() - 1) ? '\\' : st
	                    .charAt(i + 1);
	            // Octal escape?
	            if (nextChar >= '0' && nextChar <= '7')
	            {
	                String code = "" + nextChar;
	                i++;
	                if ((i < st.length() - 1) && st.charAt(i + 1) >= '0'
	                        && st.charAt(i + 1) <= '7')
	                {
	                    code += st.charAt(i + 1);
	                    i++;
	                    if ((i < st.length() - 1) && st.charAt(i + 1) >= '0'
	                            && st.charAt(i + 1) <= '7')
	                    {
	                        code += st.charAt(i + 1);
	                        i++;
	                    }
	                }
	                sb.append((char) Integer.parseInt(code, 8));
	                continue;
	            }
	            switch (nextChar)
	            {
	            case '\\':
	                ch = '\\';
	                break;
	            case 'b':
	                ch = '\b';
	                break;
	            case 'f':
	                ch = '\f';
	                break;
	            case 'n':
	                ch = '\n';
	                break;
	            case 'r':
	                ch = '\r';
	                break;
	            case 't':
	                ch = '\t';
	                break;
	            case '\"':
	                ch = '\"';
	                break;
	            case '\'':
	                ch = '\'';
	                break;
	            // Hex Unicode: u????
	            case 'u':
	                if (i >= st.length() - 5)
	                {
	                    ch = 'u';
	                    break;
	                }
	                int code = Integer.parseInt (
	                        "" + st.charAt(i + 2) + st.charAt(i + 3)
	                                + st.charAt(i + 4) + st.charAt(i + 5), 16);
	                sb.append(Character.toChars(code));
	                i += 5;
	                continue;
	            }
	            i++;
	        }
	        sb.append(ch);
	    }
	    return sb.toString();
	}
}
