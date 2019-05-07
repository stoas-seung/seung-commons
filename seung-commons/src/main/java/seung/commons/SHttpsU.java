package seung.commons;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import seung.commons.http.SHttpVO;

public class SHttpsU {

	public static SHttpVO request(SHttpVO sHttpVO) {
		
		boolean            isPost             = SCommonV._S_METHOD_POST.equals(sHttpVO.getRequestMethod()) ? true : false;
		HttpsURLConnection httpsURLConnection = null;
		OutputStream       outputStream       = null;
		InputStreamReader  inputStreamReader  = null;
		
		try {
			
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(
					null
					, new TrustManager[] {
						new X509TrustManager() {
							@Override
							public java.security.cert.X509Certificate[] getAcceptedIssuers() {
								return null;
							}
							@Override
							public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
								
							}
							@Override
							public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
							}
						}
					}
					, new SecureRandom()
					);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			
			URL url = new URL(sHttpVO.getRequestUrl());
			httpsURLConnection = (HttpsURLConnection) url.openConnection();
			
			if(sHttpVO.getRequestHeaders() != null) {
				for(String[] header : sHttpVO.getRequestHeaders()) {
					httpsURLConnection.setRequestProperty(header[0], header[1]);
				}
			}
			
			if(isPost) {
				httpsURLConnection.setRequestProperty("Content-Length", "" + sHttpVO.getRequestContentLength());
			}
			
			httpsURLConnection.setRequestMethod(sHttpVO.getRequestMethod());
			httpsURLConnection.setConnectTimeout(sHttpVO.getConnectionTimeout());
			httpsURLConnection.setReadTimeout(sHttpVO.getReadTimeout());
			httpsURLConnection.setDoInput(true);
			httpsURLConnection.setDoOutput(true);
			
			outputStream = new BufferedOutputStream(httpsURLConnection.getOutputStream());
			if(isPost) {
				outputStream.write(sHttpVO.getQueryString().getBytes(sHttpVO.getRequestEncoding()));
			}
			outputStream.flush();
			
			sHttpVO.setResponseCode(httpsURLConnection.getResponseCode());
			sHttpVO.setContentType(httpsURLConnection.getContentType());
			sHttpVO.setContentLength(httpsURLConnection.getContentLength());
			sHttpVO.setContentDisposition(httpsURLConnection.getHeaderField("Content-Disposition"));
			
			inputStreamReader = new InputStreamReader(httpsURLConnection.getInputStream());
			sHttpVO.setResponse(IOUtils.toByteArray(inputStreamReader, sHttpVO.getResponseEncoding()));
			
		} catch (NoSuchAlgorithmException e) {
			sHttpVO.setExceptionMessage(e.getMessage());
		} catch (KeyManagementException e) {
			sHttpVO.setExceptionMessage(e.getMessage());
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
				if(httpsURLConnection != null) {
					httpsURLConnection.disconnect();
				}
			} catch (IOException e) {
				sHttpVO.setExceptionMessage(e.getMessage());
			}
		}
		
		return sHttpVO;
	}
	
	public static SHttpVO requestWithFile(SHttpVO sHttpVO, String fileFieldName, String fileName, byte[] file) {
		
		String             boundary           = "----" + SCommonU.getUUID();
		String             delim              = "--";
		String             newLine            = "\r\n";
		
		HttpsURLConnection httpsURLConnection = null;
		DataOutputStream   dataOutputStream   = null;
		InputStreamReader  inputStreamReader  = null;
		
		try {
			
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(
					null
					, new TrustManager[] {
						new X509TrustManager() {
							@Override
							public java.security.cert.X509Certificate[] getAcceptedIssuers() {
								return null;
							}
							@Override
							public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
								
							}
							@Override
							public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
							}
						}
					}
					, new SecureRandom()
					);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			
			URL url = new URL(sHttpVO.getRequestUrl());
			httpsURLConnection = (HttpsURLConnection) url.openConnection();
			
			if(sHttpVO.getRequestHeaders() != null) {
				for(String[] header : sHttpVO.getRequestHeaders()) {
					httpsURLConnection.setRequestProperty(header[0], header[1]);
				}
			}
			
			httpsURLConnection.setRequestMethod(sHttpVO.getRequestMethod());
			httpsURLConnection.setConnectTimeout(sHttpVO.getConnectionTimeout());
			httpsURLConnection.setReadTimeout(sHttpVO.getReadTimeout());
			httpsURLConnection.setUseCaches(false);
			httpsURLConnection.setDoInput(true);
			httpsURLConnection.setDoOutput(true);
			httpsURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			
			dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
			dataOutputStream.writeBytes(delim + boundary + newLine);
			dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + fileFieldName + "\"; filename=\"" + fileName + "\"" + newLine);
			dataOutputStream.writeBytes("Content-Type: application/octet-stream" + newLine);
			dataOutputStream.writeBytes(newLine);
			dataOutputStream.write(file);
			dataOutputStream.writeBytes(newLine);
			for(String[] requestParameters : sHttpVO.getRequestParameters()) {
				dataOutputStream.writeBytes(delim + boundary + newLine);
				dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + requestParameters[0] + "\"" + newLine);
				dataOutputStream.writeBytes(newLine);
				dataOutputStream.writeBytes(requestParameters[1]);
				dataOutputStream.writeBytes(newLine);
			}
			dataOutputStream.writeBytes(delim + boundary + delim);
			dataOutputStream.flush();
			
			inputStreamReader = new InputStreamReader(httpsURLConnection.getInputStream());
			sHttpVO.setResponse(IOUtils.toByteArray(inputStreamReader, sHttpVO.getResponseEncoding()));
			
		} catch (NoSuchAlgorithmException e) {
			sHttpVO.setExceptionMessage(e.getMessage());
		} catch (KeyManagementException e) {
			sHttpVO.setExceptionMessage(e.getMessage());
		} catch (IOException e) {
			sHttpVO.setExceptionMessage(e.getMessage());
		} finally {
			try {
				if(inputStreamReader != null) {
					inputStreamReader.close();
				}
				if(dataOutputStream != null) {
					dataOutputStream.close();
				}
				if(httpsURLConnection != null) {
					httpsURLConnection.disconnect();
				}
			} catch (IOException e) {
				sHttpVO.setExceptionMessage(e.getMessage());
			}
		}
		
		return sHttpVO;
	}
	
}
