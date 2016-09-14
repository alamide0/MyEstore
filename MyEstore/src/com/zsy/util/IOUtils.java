package com.zsy.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
	private IOUtils() {

	}

	public static void In2Out(InputStream in, OutputStream out) {

		try {
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(InputStream in, OutputStream out) {

		try {
			if (in != null)
				in.close();
		} catch (IOException e) {
			e.printStackTrace();
			in = null;
		}finally{
			in = null;
		}
		try {
			if (out != null)
				out.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}finally{
			out = null;
		}

	}
}
