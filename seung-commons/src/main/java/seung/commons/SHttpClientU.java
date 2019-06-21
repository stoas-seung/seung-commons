package seung.commons;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import seung.commons.http.SHttpClientVO;

public class SHttpClientU {

	public static SHttpClientVO request(SHttpClientVO sHttpClientVO) throws NoSuchAlgorithmException, KeyManagementException, IOException {
		
		OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
		
		if(sHttpClientVO.getInterceptors() != null) {
			for(Interceptor interceptor : sHttpClientVO.getInterceptors()) {
				okHttpClientBuilder.addInterceptor(interceptor);
			}
		}
		
		if(sHttpClientVO.isSSL()) {
			
			SSLContext sslContext = SSLContext.getInstance("SSL");
			
			TrustManager[] trustManagers = new TrustManager[] {
					new X509TrustManager() {
						@Override
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return new X509Certificate[0];
						}
						@Override
						public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
							
						}
						@Override
						public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
						}
					}
			}
			;
			
			SecureRandom secureRandom = new SecureRandom();
			
			sslContext.init(null, trustManagers, secureRandom);
			
			okHttpClientBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0]);
			
			HostnameVerifier hostnameVerifier = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			
			okHttpClientBuilder.hostnameVerifier(hostnameVerifier);
			
		}
		
		List<Interceptor>interceptors = sHttpClientVO.getInterceptors();
		if(interceptors != null && interceptors.size() > 0) {
			
			/*
			 * HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new Logger() { @Override public void log(String message) { } });
			 * httpLoggingInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS);
			 */
			for(Interceptor interceptor : interceptors) {
				okHttpClientBuilder.addInterceptor(interceptor);
			}
			
		}
		
		OkHttpClient    okHttpClient   = okHttpClientBuilder.build();
		Request.Builder requestBuilder = new Request.Builder();
		HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(sHttpClientVO.getRequestURL()).newBuilder();
		
		if(sHttpClientVO.getRequestHeaders() != null) {
			for(String[] header : sHttpClientVO.getRequestHeaders()) {
				requestBuilder.addHeader(header[0], header[1]);
			}
		}
		
		if(sHttpClientVO.getCacheType() == null || SCommonV._S_CACHE_NO_CACHE.equals(sHttpClientVO.getCacheType())) {
			requestBuilder.cacheControl(new CacheControl.Builder().noCache().build());
		} else if(SCommonV._S_CACHE_ONLY_IF_CACHED.equals(sHttpClientVO.getCacheType())) {
			requestBuilder.cacheControl(new CacheControl.Builder().onlyIfCached().build());
		} else {
			
			TimeUnit timeUnit = null;
			switch(sHttpClientVO.getCacheTimeUnit()) {
				case SCommonV._S_TIMEUNIT_NANOSECONDS:
					timeUnit = TimeUnit.NANOSECONDS;
					break;
				case SCommonV._S_TIMEUNIT_MICROSECONDS:
					timeUnit = TimeUnit.MICROSECONDS;
					break;
				case SCommonV._S_TIMEUNIT_MILLISECONDS:
					timeUnit = TimeUnit.MILLISECONDS;
					break;
				case SCommonV._S_TIMEUNIT_SECONDS:
					timeUnit = TimeUnit.SECONDS;
					break;
				case SCommonV._S_TIMEUNIT_MINUTES:
					timeUnit = TimeUnit.MINUTES;
					break;
				case SCommonV._S_TIMEUNIT_HOURS:
					timeUnit = TimeUnit.HOURS;
					break;
				case SCommonV._S_TIMEUNIT_DAYS:
					timeUnit = TimeUnit.DAYS;
					break;
				default:
					timeUnit = TimeUnit.SECONDS;
					break;
			}
			
			switch(sHttpClientVO.getCacheType()) {
				case SCommonV._S_CACHE_MAX_STALE:
					requestBuilder.cacheControl(new CacheControl.Builder().maxStale(sHttpClientVO.getCacheTime(), timeUnit).build());
					break;
				case SCommonV._S_CACHE_MIN_FRESH:
					requestBuilder.cacheControl(new CacheControl.Builder().minFresh(sHttpClientVO.getCacheTime(), timeUnit).build());
					break;
				case SCommonV._S_CACHE_MAX_AGE:
					requestBuilder.cacheControl(new CacheControl.Builder().maxAge(sHttpClientVO.getCacheTime(), timeUnit).build());
					break;
				default:
					requestBuilder.cacheControl(new CacheControl.Builder().maxAge(0, timeUnit).build());
					break;
			}
			
		}// end of cache
		
		switch(sHttpClientVO.getRequestMethod()) {
			case SCommonV._S_METHOD_GET:
				for(String[] requestParameters : sHttpClientVO.getRequestParameters()) {
					httpUrlBuilder.addQueryParameter(requestParameters[0], requestParameters[1]);
				}
				requestBuilder.url(httpUrlBuilder.build());
				requestBuilder.get();
			case SCommonV._S_METHOD_POST:
				requestBuilder.url(httpUrlBuilder.build());
				FormBody.Builder formBodyBuilder = new FormBody.Builder(Charset.forName(sHttpClientVO.getRequestEncoding()));
				for(String[] requestParameters : sHttpClientVO.getRequestParameters()) {
					formBodyBuilder.add(requestParameters[0], requestParameters[1]);
				}
				RequestBody requestBody = formBodyBuilder.build();
				requestBuilder.post(requestBody);
				if(requestBody.contentLength() > 0) {
					requestBuilder.addHeader("Content-Length", Long.toString(requestBody.contentLength()));
				}
			default:
				break;
		}
		
		Request request = requestBuilder.build();
		
		Response response = okHttpClient.newCall(request).execute();
		
		if(response == null) {
			sHttpClientVO.setResultCode("E001");
			sHttpClientVO.setResultMessage("Response is null.");
			return sHttpClientVO;
		}
		
		sHttpClientVO.setSuccessful(response.isSuccessful());
		if(!response.isSuccessful()) {
			sHttpClientVO.setResultCode("E002");
			sHttpClientVO.setResultMessage("Request is failed");
			return sHttpClientVO;
		}
		
		sHttpClientVO.setRedirect(response.isRedirect());
		if(response.isRedirect()) {
			sHttpClientVO.setResultCode("E011");
			sHttpClientVO.setResultMessage("Request is redirected.");
			return sHttpClientVO;
		}
		
		sHttpClientVO.setResponseCode(response.code());
		sHttpClientVO.setResponseMessage(response.message());
		if(response.code() != 200) {
			sHttpClientVO.setResultCode("F" + response.code());
			sHttpClientVO.setResultMessage(response.message());
			return sHttpClientVO;
		}
		
		Headers responseHeaders = response.headers();
		if(response.headers() != null) {
			sHttpClientVO.setResponseHeaders(responseHeaders.toMultimap());
		}
		
		ResponseBody responseBody = response.body();
		if(responseBody != null) {
			sHttpClientVO.setResponseContentType(response.body().contentType().toString());
			sHttpClientVO.setResponseContentLength(response.body().contentLength());
			sHttpClientVO.setResponseBytes(response.body().bytes());
		}
		
		return sHttpClientVO;
	}
	
}
