package seung.commons.http;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import seung.commons.SCommonV;

public class SHttpVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// request
	private String              requestUrl           = "";
	private String              requestMethod        = "POST";
	private int                 connectionTimeout    = 1000 * 3;
	private ArrayList<String[]> requestHeaders;
	private ArrayList<String[]> requestParameters;
	private String              requestEncoding      = "UTF-8";
	private String              queryString;
	
	// response
	private int                 readTimeout          = 1000 * 3;;
	private int                 responseCode         = -1;
	private String              contentType;
	private int                 contentLength        = -1;
	private String              contentDisposition;
	private String              responseEncoding     = "UTF-8";
	private byte[]              response;
	
	private String              exceptionMessage;
	
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public String getResponseEncoding() {
		return responseEncoding;
	}
	public void setResponseEncoding(String responseEncoding) {
		this.responseEncoding = responseEncoding;
	}
	public int getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	public int getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	public ArrayList<String[]> getRequestHeaders() {
		return requestHeaders;
	}
	public void setRequestHeaders(ArrayList<String[]> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	public void setRequestHeaders(String key, String value) {
		if(requestHeaders == null) {
			requestHeaders = new ArrayList<String[]>();
		}
		requestHeaders.add(new String[] {key, value});
	}
	public ArrayList<String[]> getRequestParameters() {
		return requestParameters;
	}
	public void setRequestParameters(ArrayList<String[]> requestParameters) {
		this.requestParameters = requestParameters;
	}
	public void setRequestParameter(String key, String value) {
		if(requestParameters == null) {
			requestParameters = new ArrayList<String[]>();
		}
		requestParameters.add(new String[] {key, value});
	}
	public String getRequestEncoding() {
		return requestEncoding;
	}
	public void setRequestEncoding(String requestEncoding) {
		this.requestEncoding = requestEncoding;
	}
	public String getQueryString() throws UnsupportedEncodingException {
		StringBuilder queryStringBuilder = new StringBuilder();
		if(getRequestParameters() != null && "POST".equals(getRequestMethod().toUpperCase())) {
			for(String[] parameter : requestParameters) {
				queryStringBuilder.append("&");
//				queryStringBuilder.append(URLEncoder.encode(parameter[0], getRequestEncoding()));
				queryStringBuilder.append(parameter[0]);
				queryStringBuilder.append("=");
//				queryStringBuilder.append(URLEncoder.encode(parameter[1], getRequestEncoding()));
				queryStringBuilder.append(parameter[1]);
			}
		}
		queryString = queryStringBuilder.length() > 0 ? queryStringBuilder.toString().substring(1) : "";
		return queryString;
	}
	public int getRequestContentLength() throws UnsupportedEncodingException {
		return getQueryString().getBytes(getRequestEncoding()).length;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	public byte[] getResponse() {
		return response;
	}
	public void setResponse(byte[] response) {
		this.response = response;
	}
	public int getContentLength() {
		return contentLength;
	}
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
	
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
	@Override
	public String toString() {
		return SCommonV._S_GSON.toJson(this);
	}
	
}
