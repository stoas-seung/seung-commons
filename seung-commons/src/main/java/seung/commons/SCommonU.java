package seung.commons;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.CodeSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import seung.commons.arguments.SMap;

public class SCommonU {

	public static String getJarPath(Object o) throws URISyntaxException {
		CodeSource codeSource = o.getClass().getProtectionDomain().getCodeSource();
		return codeSource.getLocation().toURI().getPath();
	}
	
	public static byte[] decompress(byte[] compressed) throws IOException {
		
		ByteArrayInputStream  byteArrayInputStream  = new ByteArrayInputStream(compressed);
		GZIPInputStream       gzipInputStream       = new GZIPInputStream(byteArrayInputStream);
		BufferedInputStream   bufferedInputStream   = new BufferedInputStream(gzipInputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		byte[] b = new byte[1024];
		int length;
		while((length = bufferedInputStream.read(b)) != -1) {
			byteArrayOutputStream.write(b, 0, length);
		}
		
		byteArrayOutputStream.close();
		bufferedInputStream.close();
		gzipInputStream.close();
		byteArrayInputStream.close();
		
		return byteArrayOutputStream.toByteArray();
	}
	public static byte[] compress(byte[] src) throws IOException {
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		GZIPOutputStream      gzipOutputStream      = new GZIPOutputStream(byteArrayOutputStream);
		BufferedOutputStream  bufferedOutputStream  = new BufferedOutputStream(gzipOutputStream);
		bufferedOutputStream.write(src);
		
		bufferedOutputStream.close();
		gzipOutputStream.close();
		byteArrayOutputStream.close();
		
		return byteArrayOutputStream.toByteArray();
	}
	
	public static String[] stringToBinaryStringArray(String data, String charsetName) throws UnsupportedEncodingException {
		int[]    decimalArray      = stringToDecimalArray(data, charsetName);
		String[] binaryStringArray = new String[decimalArray.length];
		for(int i = 0; i < binaryStringArray.length; i++) {
			binaryStringArray[i] = Integer.toBinaryString(decimalArray[i]);
		}
		return binaryStringArray;
	}
	public static int[] stringToDecimalArray(String data, String charsetName) throws UnsupportedEncodingException {
		String hexString    = stringToHexString(data, charsetName);
		int[]  decimalArray = new int[hexString.length() / 2];
		for(int i = 0; i < decimalArray.length; i++) {
			decimalArray[i] = Integer.parseInt(hexString.substring(i * 2, i * 2 + 2), 16);
		}
		return decimalArray;
	}
	
	public static String base64StringToString(String base64String, String charsetName) throws UnsupportedEncodingException {
		return new String(decodeBase64(base64String), charsetName);
	}
	public static byte[] decodeBase64(String base64String) {
		return Base64.decodeBase64(base64String);
	}
	public static String stringToBase64String(String data, String charsetName) throws UnsupportedEncodingException {
		return encodeBase64String(data.getBytes(charsetName));
	}
	public static String encodeBase64String(byte[] binaryData) {
		return Base64.encodeBase64String(binaryData);
	}
	
	public static String hexStringToString(String data, String charsetName) throws DecoderException, UnsupportedEncodingException {
		return new String(decodeHex(data), charsetName);
	}
	public static byte[] decodeHex(String data) throws DecoderException {
		return Hex.decodeHex(data);
	}
	public static String stringToHexString(String data, String charsetName) throws UnsupportedEncodingException {
		return stringToHexString(data, charsetName, true);
	}
	public static String stringToHexString(String data, String charsetName, boolean toLowerCase) throws UnsupportedEncodingException {
		return encodeHexString(data.getBytes(charsetName), toLowerCase);
	}
	public static String encodeHexString(byte[] data, boolean toLowerCase) {
		return Hex.encodeHexString(data, toLowerCase);
	}
	
	/**
	 * @desc repeat string
	 * @param str - source
	 * @param repeat times
	 * @return repeated string
	 */
	public static String repeat(String str, int repeat) {
		return StringUtils.repeat(str, repeat);
	}
	
	/**
	 * @desc uuid
	 * @return e.g. 770386e0-1fff-4860-af18-e5c75aa72e1c
	 */
	public static String getUUID(String regex) {
		return getUUID().replaceAll(regex, "");
	}
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static Date stringToDate(String source) throws ParseException {
		return stringToDate(source, "yyyyMMdd");
	}
	public static Date stringToDate(String source, String pattern) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(source);
	}
	public static String dateDiff(String unit, Date from, Date to) {
		long diff = 0l;
		switch(unit) {
			case SCommonV._S_TIME_MILLISECONDS:
				diff = to.getTime() - from.getTime();
				break;
			case SCommonV._S_TIME_SECONEDS:
				diff = TimeUnit.SECONDS.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS);
				break;
			case SCommonV._S_TIME_MINUTES:
				diff = TimeUnit.MINUTES.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS);
				break;
			case SCommonV._S_TIME_HOURS:
				diff = TimeUnit.HOURS.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS);
				break;
			case SCommonV._S_TIME_DAYS:
				diff = TimeUnit.DAYS.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS);
				break;
			default:
				break;
		}
		return Long.toString(diff);
	}
	public static int getDateInteger(String pattern) {
		return Integer.parseInt(getDateString(pattern).replaceAll("[^0-9]", ""));
	}
	public static String getDateString(String pattern) {
		return getDateString(pattern, SCommonV._S_TIMEZONE_KST);
	}
	public static String getDateString(String pattern, String timeZone) {
		return getDateString(new Date(), pattern, timeZone);
	}
	public static String getDateString(Date date, String pattern, String timeZone) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern != null && pattern.length() > 0 ? pattern : "yyyy-MM-dd HH:mm:sssss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		return simpleDateFormat.format(date);
	}
	public static Set<String> getAvailableZoneIDs() {
		return ZoneId.getAvailableZoneIds();
	}
	public static String[] getAvailableTimeZoneIDs() {
		return TimeZone.getAvailableIDs();
	}
	
	/**
	 * @param algorithm: MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512
	 * @param charsetName: SCommonV._S_CHA_UTF8, SCommonV._S_CHA_EUCKR
	 * @param str - source
	 * @return byte array or string
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String digestToBase64(String algorithm, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return digestToBase64(algorithm, SCommonV._S_CHA_UTF8, str);
	}
	public static String digestToBase64(String algorithm, String charsetName, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return Base64.encodeBase64String(digestToByteArray(algorithm, charsetName, str));
	}
	public static String digestToHexString(String algorithm, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return digestToHexString(algorithm, SCommonV._S_CHA_UTF8, str);
	}
	public static String digestToHexString(String algorithm, String charsetName, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return Hex.encodeHexString(digestToByteArray(algorithm, charsetName, str));
	}
	public static byte[] digestToByteArray(String algorithm, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return digestToByteArray(algorithm, SCommonV._S_CHA_UTF8, str);
	}
	public static byte[] digestToByteArray(String algorithm, String charsetName, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
		return messageDigest.digest(str.getBytes(charsetName));
	}
	
	/**
	 * @param s - source
	 * @param enc - character set name
	 * @return encoded string
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeURI(String s) throws UnsupportedEncodingException {
		return encodeURI(s, SCommonV._S_CHA_UTF8);
	}
	public static String encodeURI(String s, String enc) throws UnsupportedEncodingException {
		return URLEncoder.encode(s, enc);
	}
	public static String encodeURIComponent(String s) throws UnsupportedEncodingException {
		return encodeURIComponent(s, SCommonV._S_CHA_UTF8);
	}
	public static String encodeURIComponent(String s, String enc) throws UnsupportedEncodingException {
		return URLEncoder.encode(s, enc)
				.replaceAll("\\+"  , "%20")
				.replaceAll("\\%21", "!")
				.replaceAll("\\%27", "'")
				.replaceAll("\\%28", "(")
				.replaceAll("\\%29", ")")
				.replaceAll("\\%7E", "~")
				;
	}
	public static String decodeURI(String s) throws UnsupportedEncodingException {
		return decodeURI(s, SCommonV._S_CHA_UTF8);
	}
	public static String decodeURI(String s, String enc) throws UnsupportedEncodingException {
		return URLDecoder.decode(s, enc);
	}
	
	public static String toJson(Object src) {
		return toJson(src, false);
	}
	public static String toJson(Object src, boolean isPretty) {
		if(isPretty) {
			return SCommonV._S_GSON_PRETTY.toJson(src);
		} else {
			return SCommonV._S_GSON.toJson(src);
		}
	}
	
	/**
	 * @desc network information
	 * @return
	 *   result   - 0:FAIL, 1: SUCCESS
	 *   message  - error message
	 *   osName   - os name of system properties
	 *   networks - isUp and not isLoopback
	 *     isVirtual
	 *     isPointToPoint
	 *     name
	 *     displayName
	 *     hostName - host name of InetAddress
	 *     hostAddress - host address of InetAddress
	 *     isReachable
	 *     mac
	 */
	public static SMap getNetworks() {
		
		SMap            res      = new SMap();
		ArrayList<SMap> networks = new ArrayList<SMap>();
		SMap            tmp      = null;
		res.put("result" , 0);
		res.put("message", "");
		
		try {
			
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			
			NetworkInterface         networkInterface  = null;
			byte[]                   hardwareAddresses = null;
			Enumeration<InetAddress> inetAddresses     = null;
			InetAddress              inetAddress       = null;
			StringBuffer             macs              = null;
			while(networkInterfaces.hasMoreElements()) {
				
				networkInterface = networkInterfaces.nextElement();
				if(!networkInterface.isUp()) {
					continue;
				}
				if(networkInterface.isLoopback()) {
					continue;
				}
				
				tmp = new SMap();
				
				tmp.put(String.format("%-16s", "name")          , networkInterface.getName());
				tmp.put(String.format("%-16s", "displayName")   , networkInterface.getDisplayName());
//				tmp.put(String.format("%-16s", "isUp")          , networkInterface.isUp());
//				tmp.put(String.format("%-16s", "isLoopback")    , networkInterface.isLoopback());
				tmp.put(String.format("%-16s", "isVirtual")     , networkInterface.isVirtual());
				tmp.put(String.format("%-16s", "isPointToPoint"), networkInterface.isPointToPoint());
				
				inetAddresses = networkInterface.getInetAddresses();
				while(inetAddresses.hasMoreElements()) {
					inetAddress = inetAddresses.nextElement();
					tmp.put(String.format("%-16s", "hostName")   , inetAddress.getHostName());
					tmp.put(String.format("%-16s", "hostAddress"), inetAddress.getHostAddress());
					tmp.put(String.format("%-16s", "isReachable"), inetAddress.isReachable(3000));
				}
				
				hardwareAddresses = networkInterface.getHardwareAddress();
				if(hardwareAddresses != null) {
					macs = new StringBuffer();
					for(int i = 0; i < hardwareAddresses.length; i++) {
						macs.append(String.format("%02X%s", hardwareAddresses[i], (i < hardwareAddresses.length - 1) ? "-" : ""));
					}
					tmp.put(String.format("%-16s", "mac"), macs.toString());
				}
				
				networks.add(tmp);
			}
			
//			res.put("hostName", inetAddress.getHostName());
			res.put("networks", networks);
			res.put("result"  , 1);
			
		} catch (SocketException e) {
			e.printStackTrace();
			res.put("message", e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			res.put("message", e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * @desc system properties
	 * @return key and value
	 */
	@SuppressWarnings("unchecked")
	public static SMap getSystemProperties() {
		
		SMap res = new SMap();
		
		Properties properties = System.getProperties();
		Enumeration<String> propertyNames = (Enumeration<String>) properties.propertyNames();
		
		String propertyName = "";
		while(propertyNames.hasMoreElements()) {
			propertyName = (String) propertyNames.nextElement();
			res.put(String.format("%-32s", propertyName), properties.getProperty(propertyName));
		}
		
		return res;
	}
}
