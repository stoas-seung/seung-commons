package seung.commons;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import seung.commons.http.SHttpVO;

public class SHttpU {

	public static SHttpVO request(SHttpVO sHttpVO) {
		
		boolean           isPost            = SCommonV._S_METHOD_POST.equals(sHttpVO.getRequestMethod()) ? true : false;
		HttpURLConnection httpURLConnection = null;
		OutputStream      outputStream      = null;
		InputStreamReader inputStreamReader = null;
		
		try {
			
			URL url = new URL(sHttpVO.getRequestUrl());
			httpURLConnection = (HttpURLConnection) url.openConnection();
			
			if(sHttpVO.getRequestHeaders() != null) {
				for(String[] header : sHttpVO.getRequestHeaders()) {
					httpURLConnection.setRequestProperty(header[0], header[1]);
				}
			}
			
			if(isPost) {
				httpURLConnection.setRequestProperty("Content-Length", "" + sHttpVO.getRequestContentLength());
			}
			
			httpURLConnection.setRequestMethod(sHttpVO.getRequestMethod());
			httpURLConnection.setConnectTimeout(sHttpVO.getConnectionTimeout());
			httpURLConnection.setReadTimeout(sHttpVO.getReadTimeout());
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			
			outputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
			if(isPost) {
				outputStream.write(sHttpVO.getQueryString().getBytes(sHttpVO.getRequestEncoding()));
			}
			outputStream.flush();
			
			sHttpVO.setResponseCode(httpURLConnection.getResponseCode());
			sHttpVO.setContentType(httpURLConnection.getContentType());
			sHttpVO.setContentLength(httpURLConnection.getContentLength());
			sHttpVO.setContentDisposition(httpURLConnection.getHeaderField("Content-Disposition"));
			
			inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
			sHttpVO.setResponse(IOUtils.toByteArray(inputStreamReader, sHttpVO.getResponseEncoding()));
			
		} catch (IOException e) {
			sHttpVO.setExceptionMessage(e.getMessage());
		} finally {
			try {
				if(inputStreamReader != null) {
					inputStreamReader.close();
				}
				if(outputStream != null) {
					outputStream.close();
				}
				if(httpURLConnection != null) {
					httpURLConnection.disconnect();
				}
			} catch (IOException e) {
				sHttpVO.setExceptionMessage(e.getMessage());
			}
		}
		
		return sHttpVO;
	}
}
