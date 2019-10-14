package seung.commons.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import seung.commons.SCommonV;

public class SHttpClientVO {

	// result
	private String                    resultCode            = "0000";
	private String                    resultMessage         = "";
	
	// request
	private boolean                   isSSL                 = false;
	private String                    requestURL            = "";
	private String                    requestMethod         = SCommonV._S_METHOD_POST;
	private String                    requestEncoding       = SCommonV._S_CHA_UTF8;
	private long                      readTimeout           = 0l;
	private long                      connectTimeout        = 0l;
	private String                    cacheType             = SCommonV._S_CACHE_NO_CACHE;
	private int                       cacheTime             = -1;
	private String                    cacheTimeUnit         = SCommonV._S_TIMEUNIT_SECONDS;
	private ArrayList<String[]>       requestHeaders        = null;
	private ArrayList<String[]>       requestParameters     = null;
	private ArrayList<Interceptor>    interceptors          = null;
	
	// response
	private boolean                   isSuccessful          = false;
	private boolean                   isRedirect            = true;
	private int                       responseCode          = -1;
	private Map<String, List<String>> responseHeaders       = null;
	private String                    responseContentType   = "";
	private long                      responseContentLength = -1L;
	private byte[]                    responseBytes         = null;
	private String                    responseMessage       = "";
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	public boolean isSSL() {
		return isSSL;
	}
	public void setSSL(boolean isSSL) {
		this.isSSL = isSSL;
	}
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public String getRequestEncoding() {
		return requestEncoding;
	}
	public void setRequestEncoding(String requestEncoding) {
		this.requestEncoding = requestEncoding;
	}
	public long getReadTimeout() {
		return readTimeout;
	}
	public void setRequestEncoding(long readTimeout) {
		this.readTimeout = readTimeout;
	}
	public long getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(long connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public String getCacheType() {
		return cacheType;
	}
	public void setCacheType(String cacheType) {
		this.cacheType = cacheType;
	}
	public int getCacheTime() {
		return cacheTime;
	}
	public void setCacheTime(int cacheTime) {
		this.cacheTime = cacheTime;
	}
	public String getCacheTimeUnit() {
		return cacheTimeUnit;
	}
	public void setCacheTimeUnit(String cacheTimeUnit) {
		this.cacheTimeUnit = cacheTimeUnit;
	}
	public ArrayList<String[]> getRequestHeaders() {
		return requestHeaders;
	}
	public void setRequestHeaders(ArrayList<String[]> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	public void addRequestHeader(String key, String value) {
		if(requestHeaders == null) {
			requestHeaders = new ArrayList<String[]>();
		}
		requestHeaders.add(new String[] {key, value});
	}
	public void clearRequestHeaders() {
		requestHeaders = new ArrayList<String[]>();
	}
	public ArrayList<String[]> getRequestParameters() {
		return requestParameters;
	}
	public void setRequestParameters(ArrayList<String[]> requestParameters) {
		this.requestParameters = requestParameters;
	}
	public void addRequestParameter(String key, String value) {
		if(requestParameters == null) {
			requestParameters = new ArrayList<String[]>();
		}
		requestParameters.add(new String[] {key, value});
	}
	public void clearRequestParameters() {
		requestParameters = new ArrayList<String[]>();
	}
	public ArrayList<Interceptor> getInterceptors() {
		return interceptors;
	}
	public void setInterceptors(ArrayList<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}
	public void addInterceptor(Interceptor interceptor) {
		if(interceptors == null) {
			interceptors = new ArrayList<Interceptor>();
		}
		interceptors.add(interceptor);
	}
	
	public boolean isSuccessful() {
		return isSuccessful;
	}
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public Map<String, List<String>> getResponseHeaders() {
		return responseHeaders;
	}
	public void setResponseHeaders(Map<String, List<String>> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}
	public String getResponseContentType() {
		return responseContentType;
	}
	public void setResponseContentType(String responseContentType) {
		this.responseContentType = responseContentType;
	}
	public long getResponseContentLength() {
		return responseContentLength;
	}
	public void setResponseContentLength(long responseContentLength) {
		this.responseContentLength = responseContentLength;
	}
	public byte[] getResponseBytes() {
		return responseBytes;
	}
	public void setResponseBytes(byte[] responseBytes) {
		this.responseBytes = responseBytes;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
