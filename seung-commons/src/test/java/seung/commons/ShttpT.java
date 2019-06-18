package seung.commons;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import seung.commons.http.SHttpClientVO;
import seung.commons.http.SHttpVO;

public class ShttpT {

	@Test
	public void test() {
		
//		CookieManager cookieManager = new CookieManager();
//		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
//		CookieJar cookieJar = new JavaNetCookieJar(cookieManager);
//		
//		a(cookieJar);
//		
//		b(cookieJar);
		t();
	}
	
	public void t() {
		
		SHttpClientVO sHttpClientVO = new SHttpClientVO();
		
		sHttpClientVO.setSSL(false);
		sHttpClientVO.setRequestEncoding(SCommonV._S_CHA_UTF8);
		sHttpClientVO.setRequestURL("https://www.kebhana.com/cms/rate/wpfxd651_01i.do");
		
		sHttpClientVO.addRequestHeader("Accept"             , "text/javascript, text/html, application/xml, text/xml, */*");
		sHttpClientVO.addRequestHeader("Content-type"       , "application/x-www-form-urlencoded; charset=UTF-8");
		sHttpClientVO.addRequestHeader("Origin"             , "https://www.kebhana.com");
		sHttpClientVO.addRequestHeader("Referer"            , "https://www.kebhana.com/cms/rate/index.do?contentUrl=/cms/rate/wpfxd651_01i.do");
		sHttpClientVO.addRequestHeader("User-Agent"         , "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
//		sHttpClientVO.addRequestHeader("X-Prototype-Version", "1.5.1.1");
		sHttpClientVO.addRequestHeader("X-Requested-With"   , "XMLHttpRequest");
		
		sHttpClientVO.addRequestParameter("ajax"          , "true");
		sHttpClientVO.addRequestParameter("inqType"       , "0");
		sHttpClientVO.addRequestParameter("tmpInqStrDt_d" , "20190617");
		sHttpClientVO.addRequestParameter("tmpInqStrDtY_m", "2019");
		sHttpClientVO.addRequestParameter("tmpInqStrDtM_m", "06");
		sHttpClientVO.addRequestParameter("tmpInqStrDt_p" , "20190617");
		sHttpClientVO.addRequestParameter("tmpInqEndDt_p" , "20190617");
		sHttpClientVO.addRequestParameter("curCd"         , "USD");
		sHttpClientVO.addRequestParameter("tmpPbldDvCd"   , "1");
		sHttpClientVO.addRequestParameter("inqDt"         , "20190617");
		sHttpClientVO.addRequestParameter("inqDvCd"       , "1");
		sHttpClientVO.addRequestParameter("hid_key_data"  , "");
		sHttpClientVO.addRequestParameter("hid_enc_data"  , "");
		sHttpClientVO.addRequestParameter("requestTarget" , "searchContentDiv");
		
		// for logging
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new okhttp3.logging.HttpLoggingInterceptor.Logger() {
			@Override
			public void log(String message) {
				System.out.println(message);
				
			}
		});
		httpLoggingInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS);
		sHttpClientVO.addInterceptor(httpLoggingInterceptor);
		try {
			sHttpClientVO = SHttpClientU.request(sHttpClientVO);
//			System.out.println(SCommonV._S_GSON_PRETTY.toJson(sHttpClientVO));
			System.out.println(new String(sHttpClientVO.getResponseBytes()));
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CookieJar b(CookieJar cookieJar) {
		
		SHttpVO sHttpVO = new SHttpVO();
		
		sHttpVO.setIsSSL(false);
		sHttpVO.setRequestEncoding(SCommonV._S_CHA_UTF8);
		sHttpVO.setRequestUrl("https://www.kebhana.com/cms/rate/wpfxd651_07i_01.do");
		
		sHttpVO.setRequestHeaders("Accept"             , "text/javascript, text/html, application/xml, text/xml, */*");
		sHttpVO.setRequestHeaders("Accept-Encoding"    , "gzip, deflate, br");
		sHttpVO.setRequestHeaders("Accept-Language"    , "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
		sHttpVO.setRequestHeaders("Cache-Control"      , "no-cache");
		sHttpVO.setRequestHeaders("Connection"         , "keep-alive");
		sHttpVO.setRequestHeaders("Content-Length"     , "234");
		sHttpVO.setRequestHeaders("Content-type"       , "application/x-www-form-urlencoded; charset=UTF-8");
		sHttpVO.setRequestHeaders("Host"               , "www.kebhana.com");
		sHttpVO.setRequestHeaders("Origin"             , "https://www.kebhana.com");
		sHttpVO.setRequestHeaders("Pragma"             , "no-cache");
		sHttpVO.setRequestHeaders("Referer"            , "https://www.kebhana.com/cms/rate/index.do?contentUrl=/cms/rate/wpfxd651_01i.do");
		sHttpVO.setRequestHeaders("User-Agent"         , "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
//		sHttpVO.setRequestHeaders("X-Prototype-Version", "1.5.1.1");
		sHttpVO.setRequestHeaders("X-Requested-With"   , "XMLHttpRequest");
		
		sHttpVO.setRequestParameter("ajax"          , "true");
		sHttpVO.setRequestParameter("inqType"       , "0");
		sHttpVO.setRequestParameter("tmpInqStrDt_d" , "20190617");
		sHttpVO.setRequestParameter("tmpInqStrDtY_m", "2019");
		sHttpVO.setRequestParameter("tmpInqStrDtM_m", "06");
		sHttpVO.setRequestParameter("tmpInqStrDt_p" , "20190617");
		sHttpVO.setRequestParameter("tmpInqEndDt_p" , "20190617");
		sHttpVO.setRequestParameter("curCd"         , "USD");
		sHttpVO.setRequestParameter("tmpPbldDvCd"   , "1");
		sHttpVO.setRequestParameter("inqDt"         , "20190617");
		sHttpVO.setRequestParameter("inqDvCd"       , "1");
		sHttpVO.setRequestParameter("hid_key_data"  , "");
		sHttpVO.setRequestParameter("hid_enc_data"  , "");
		sHttpVO.setRequestParameter("requestTarget" , "searchContentDiv");
		
		try {
			
			OkHttpClient okHttpClient = buildOkHttpClient(cookieJar, sHttpVO);
			
			FormBody.Builder formBodyBuilder = new FormBody
					.Builder(Charset.forName(sHttpVO.getRequestEncoding()))
					;
			
			for(String[] requestParameters : sHttpVO.getRequestParameters()) {
				formBodyBuilder.add(requestParameters[0], requestParameters[1]);
			}
			
			RequestBody requestBody = formBodyBuilder.build();
			
			Request.Builder requestBuilder = new Request
					.Builder()
					.url(sHttpVO.getRequestUrl())
					.post(requestBody)
					;
			
			if(sHttpVO.getRequestHeaders() != null) {
				for(String[] header : sHttpVO.getRequestHeaders()) {
					requestBuilder.addHeader(header[0], header[1]);
				}
			}
			
			Request request = requestBuilder.build();
			
			Response response = okHttpClient.newCall(request).execute();
			
			ResponseBody responseBody = response.body();
			
			String responseText = responseBody.string();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cookieJar;
	}
	
	public CookieJar a(CookieJar cookieJar) {
		
		SHttpVO sHttpVO = new SHttpVO();
		
		sHttpVO.setIsSSL(false);
		sHttpVO.setRequestEncoding(SCommonV._S_CHA_UTF8);
		sHttpVO.setRequestUrl("https://www.kebhana.com/cms/rate/wpfxd651_01i.do");
		
		sHttpVO.setRequestHeaders("Accept"             , "text/javascript, text/html, application/xml, text/xml, */*");
		sHttpVO.setRequestHeaders("Content-type"       , "application/x-www-form-urlencoded; charset=UTF-8");
		sHttpVO.setRequestHeaders("Origin"             , "https://www.kebhana.com");
		sHttpVO.setRequestHeaders("Referer"            , "https://www.kebhana.com/cms/rate/index.do?contentUrl=/cms/rate/wpfxd651_01i.do");
		sHttpVO.setRequestHeaders("User-Agent"         , "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
//		sHttpVO.setRequestHeaders("X-Prototype-Version", "1.5.1.1");
		sHttpVO.setRequestHeaders("X-Requested-With"   , "XMLHttpRequest");
		
		sHttpVO.setRequestParameter("ajax"          , "true");
		sHttpVO.setRequestParameter("inqType"       , "0");
		sHttpVO.setRequestParameter("tmpInqStrDt_d" , "20190617");
		sHttpVO.setRequestParameter("tmpInqStrDtY_m", "2019");
		sHttpVO.setRequestParameter("tmpInqStrDtM_m", "06");
		sHttpVO.setRequestParameter("tmpInqStrDt_p" , "20190617");
		sHttpVO.setRequestParameter("tmpInqEndDt_p" , "20190617");
		sHttpVO.setRequestParameter("curCd"         , "USD");
		sHttpVO.setRequestParameter("tmpPbldDvCd"   , "1");
		sHttpVO.setRequestParameter("inqDt"         , "20190617");
		sHttpVO.setRequestParameter("inqDvCd"       , "1");
		sHttpVO.setRequestParameter("hid_key_data"  , "");
		sHttpVO.setRequestParameter("hid_enc_data"  , "");
		sHttpVO.setRequestParameter("requestTarget" , "searchContentDiv");
		
		try {
			
			OkHttpClient okHttpClient = buildOkHttpClient(cookieJar, sHttpVO);
			
			FormBody.Builder formBodyBuilder = new FormBody
					.Builder(Charset.forName(sHttpVO.getRequestEncoding()))
					;
			
			for(String[] requestParameters : sHttpVO.getRequestParameters()) {
				formBodyBuilder.add(requestParameters[0], requestParameters[1]);
			}
			
			RequestBody requestBody = formBodyBuilder.build();
			
			Request.Builder requestBuilder = new Request
					.Builder()
					.url(sHttpVO.getRequestUrl())
					.get()
					;
			
			if(sHttpVO.getRequestHeaders() != null) {
				for(String[] header : sHttpVO.getRequestHeaders()) {
					requestBuilder.addHeader(header[0], header[1]);
				}
			}
			
			Request request = requestBuilder.build();
			
			Response response = okHttpClient.newCall(request).execute();
			for(String setCookie : response.headers("Set-Cookie")) {
				System.out.println(setCookie);
			}
			
			ResponseBody responseBody = response.body();
			
			String responseText = responseBody.string();
			
			Document body = Jsoup.parse(responseText);
			Element select = body.getElementById("curCd");
			for(Element option : select.select("option")) {
				System.out.println("===");
				System.out.println(option.val());
				System.out.println(option.text());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cookieJar;
	}
	
	public OkHttpClient buildOkHttpClient(
			CookieJar cookieJar
			, SHttpVO sHttpVO
			) {
		
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		
		try {
			
			builder.cookieJar(cookieJar);
			
			if(sHttpVO.getIsSSL()) {
				
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
				
				builder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0]);
				
				HostnameVerifier hostnameVerifier = new HostnameVerifier() {
						@Override
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					};
				
				builder.hostnameVerifier(hostnameVerifier);
				
			}
			
			HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new Logger() {
				
				@Override
				public void log(String message) {
					System.out.println(message);
					
				}
				
			});
			
			httpLoggingInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS);
			builder.addInterceptor(httpLoggingInterceptor);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return builder.build();
		
	}
}
