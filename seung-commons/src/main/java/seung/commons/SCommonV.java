package seung.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SCommonV {

	public final static String _S_FILE               = "F";
	public final static String _S_DIRECTORY          = "D";
	public final static String _S_FILE_AND_DIRECTORY = "FD";
	
	public final static String _S_DATA_HTML = "HTML";
	public final static String _S_DATA_JSON = "JSON";
	public final static String _S_DATA_XML  = "XML";
	public final static String _S_DATA_CSV  = "CSV";
	
	public final static String _S_CHA_UTF8  = "UTF-8";
	public final static String _S_CHA_EUCKR = "EUC-KR";
	
	public final static String _S_METHOD_GET     = "GET";
	public final static String _S_METHOD_POST    = "POST";
	public final static String _S_METHOD_OPTIONS = "OPTIONS";
	public final static String _S_METHOD_CONNECT = "CONNECT";
	public final static String _S_METHOD_PUT     = "PUT";
	public final static String _S_METHOD_DELETE  = "DELETE";
	public final static String _S_METHOD_TRACE   = "TRACE";
	public final static String _S_METHOD_HEAD    = "HEAD";
	
	public final static String _S_ENCODING_URL    = "URL";
	public final static String _S_ENCODING_HEX    = "HEX";
	public final static String _S_ENCODING_BASE64 = "BASE64";
	public final static String _S_ENCODING_ASCII  = "ASCII";
	
	public final static String _S_HASH_MD2    = "MD2";
	public final static String _S_HASH_MD5    = "MD5";
	public final static String _S_HASH_SHA    = "SHA";
	public final static String _S_HASH_SHA1   = "SHA-1";
	public final static String _S_HASH_SHA256 = "SHA-256";
	public final static String _S_HASH_SHA384 = "SHA-384";
	public final static String _S_HASH_SHA512 = "SHA-512";
	public final static String _S_HASH_HAS160 = "HAS-160";
	
	public final static String _S_TIMEZONE_UTC = "UTC";
	public final static String _S_TIMEZONE_KST = "Asia/Seoul";
	
	
	public final static String _S_TIME_MILLISECONDS = "MS";
	public final static String _S_TIME_SECONEDS     = "S";
	public final static String _S_TIME_MINUTES      = "M";
	public final static String _S_TIME_HOURS        = "H";
	public final static String _S_TIME_DAYS         = "D";
	
	public final static Gson _S_GSON        = new GsonBuilder().disableHtmlEscaping().create();
	public final static Gson _S_GSON_PRETTY = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
	
}
