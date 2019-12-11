package seung.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import seung.commons.asn1.SCertificateU;
import seung.commons.http.SHttpVO;

public class SCommonT {

	@Test
	public void test() throws FileNotFoundException, IOException, NoSuchAlgorithmException, DecoderException, ParseException, CertificateException {
		
		
		System.out.println("abcdefg".hashCode());
		
//		SCertificateU.getSignCertInfo(SFileU.readFileToByteArray(new File("ref/signCert2.der")));
		
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCertificateU"
//				, "readSignCertDerByFile"
//				, SCommonV._S_GSON.toJson(SCertificateU.readSignCertDerByFile(new File("ref/signCert.der")))
//				));
//		String src = SFileU.readFileToString(new File("ref/forCompress"), SCommonV._S_CHA_UTF8);
//		System.out.println(String.format(
//				"[%20s] (%8d) - %s"
//				, "before compress"
//				, src.getBytes(SCommonV._S_CHA_UTF8).length
//				, src.substring(0, 100)
//				));
//		byte[] compressed = SCommonU.compress(src.getBytes(SCommonV._S_CHA_UTF8));
//		System.out.println(String.format(
//				"[%20s] (%8d) - %s"
//				, "after compress"
//				, compressed.length
//				, new String(compressed).substring(0, 100)
//				));
//		String hex = Hex.encodeHexString(compressed, true);
//		System.out.println(String.format(
//				"[%20s] (%8d) - %s"
//				, "compressed to hex"
//				, hex.length()
//				, hex.substring(0, 100)
//				));
//		byte[] decodeHex = Hex.decodeHex(hex);
//		System.out.println(String.format(
//				"[%20s] (%8d) - %s"
//				, "decode hex"
//				, decodeHex.length
//				, new String(decodeHex).substring(0, 100)
//				));
//		byte[] decompressed = SCommonU.decompress(decodeHex);
//		System.out.println(String.format(
//				"[%20s] (%8d) - %s"
//				, "decompress"
//				, decompressed.length
//				, new String(decompressed, SCommonV._S_CHA_UTF8).substring(0, 100)
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToDate"
//				, SCommonU.dateDiff(SCommonV._S_TIME_HOURS, new Date(), SCommonU.stringToDate("20190415"))
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToDate"
//				, SCommonU.dateDiff(SCommonV._S_TIME_DAYS,SCommonU.stringToDate("20190415"), new Date())
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToDate"
//				, SCommonU.dateDiff(SCommonV._S_TIME_DAYS, new Date(), SCommonU.stringToDate("20190415"))
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "getDateString"
//				, SCommonV._S_GSON_PRETTY.toJson(SCommonU.getDateString("yyyy-MM-dd HH:mm:ss", SCommonV._S_TIMEZONE_KST))
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "getDateString"
//				, SCommonV._S_GSON_PRETTY.toJson(SCommonU.getDateString("yyyy-MM-dd HH:mm:ss", SCommonV._S_TIMEZONE_UTC))
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "getAvailableZoneIDs"
//				, SCommonV._S_GSON_PRETTY.toJson(SCommonU.getAvailableZoneIDs())
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "getAvailableTimeZoneIDs"
//				, SCommonV._S_GSON_PRETTY.toJson(SCommonU.getAvailableTimeZoneIDs())
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToBinaryStringArray"
//				, SCommonV._S_GSON_PRETTY.toJson(SCommonU.stringToBinaryStringArray("seung승", SCommonV._S_CHA_EUCKR))
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToBinaryStringArray"
//				, SCommonV._S_GSON_PRETTY.toJson(SCommonU.stringToBinaryStringArray("seung승", SCommonV._S_CHA_UTF8))
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToDecimalArray"
//				, SCommonV._S_GSON_PRETTY.toJson(SCommonU.stringToDecimalArray("seung승", SCommonV._S_CHA_EUCKR))
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToDecimalArray"
//				, SCommonV._S_GSON_PRETTY.toJson(SCommonU.stringToDecimalArray("seung승", SCommonV._S_CHA_UTF8))
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToBase64String"
//				, SCommonU.stringToBase64String("seung승seung", SCommonV._S_CHA_EUCKR)
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "base64StringToString"
//				, SCommonU.base64StringToString("c2V1bme9wnNldW5n", SCommonV._S_CHA_EUCKR)
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToBase64String"
//				, SCommonU.stringToBase64String("seung승seung", SCommonV._S_CHA_UTF8)
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "base64StringToString"
//				, SCommonU.base64StringToString("c2V1bmfsirlzZXVuZw==", SCommonV._S_CHA_UTF8)
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToHexString"
//				, SCommonU.stringToHexString("seung승seung", SCommonV._S_CHA_EUCKR, true)
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "hexStringToString"
//				, SCommonU.hexStringToString("7365756e67bdc27365756e67", SCommonV._S_CHA_EUCKR)
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "stringToHexString"
//				, SCommonU.stringToHexString("seung승seung", SCommonV._S_CHA_UTF8, true)
//				));
//		System.out.println(String.format(
//				"[%16s:%32s] - %s"
//				, "SCommonU"
//				, "hexStringToString"
//				, SCommonU.hexStringToString("7365756e67ec8ab97365756e67", SCommonV._S_CHA_UTF8)
//				));
//		SHttpVO sHttpVO = new SHttpVO();
//		sHttpVO.setRequestUrl("https://www.safedriving.or.kr/LnrForRtnLicns/LnrForRtnLicnsTruthYnComplete.do");
//		sHttpVO.setRequestMethod(SCommonV._S_METHOD_POST);
//		sHttpVO.setRequestHeaders("Content-Type", "application/x-www-form-urlencoded");
//		sHttpVO.setRequestParameter("menuCode"  , "MN-PO-1241");
//		sHttpVO.setRequestParameter("sName"     , SCommonU.encodeURIComponent("박종승"));
//		sHttpVO.setRequestParameter("sJumin1"   , "810605");
//		sHttpVO.setRequestParameter("licenLocal", SCommonU.encodeURIComponent("전남"));
//		sHttpVO.setRequestParameter("licence01" , "18");
//		sHttpVO.setRequestParameter("licence02" , "03");
//		sHttpVO.setRequestParameter("licence03" , "039995");
//		sHttpVO.setRequestParameter("licence04" , "43");
//		sHttpVO.setRequestParameter("serialNum" , "ABC12");
//		SHttpU.request(sHttpVO);
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "SHttpU"
//				, "request"
//				, SCommonU.toJson(sHttpVO, true)
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "SHttpU"
//				, "request"
//				, new String(sHttpVO.getResponse(), sHttpVO.getResponseEncoding())
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "getFileListInfo"
//				, ""
//				, SCommonU.toJson(SFileU.getFileListInfo(new File("").getAbsolutePath()), true)
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "getFileInfo"
//				, ""
//				, SFileU.getFileInfo("ref/public_data_1.xlsx").toJson(true)
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "getDirPath"
//				, ""
//				, SFileU.getDirectoryPath("ref/public_data_1.xlsx")
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "getSystemProperties"
//				, ""
//				, SCommonU.getSystemProperties().toJson(true)
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "getNetworks"
//				, ""
//				, SCommonU.getNetworks().toJson(true)
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "readXls"
//				, ""
//				, SCommonU.toJson(SExcelU.readXls("ref/public_data_1.xlsx"), true)
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "encodeURIComponent"
//				, SCommonV._S_CHA_EUCKR
//				, SCommonU.encodeURIComponent("https://www.google.com?p1=박종승&p2=+`~()!", SCommonV._S_CHA_EUCKR)
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "encodeURI"
//				, SCommonV._S_CHA_EUCKR
//				, SCommonU.encodeURI("https://www.google.com?p1=박종승&p2=+`~()!", SCommonV._S_CHA_EUCKR)
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "encodeURIComponent"
//				, SCommonV._S_CHA_UTF8
//				, SCommonU.encodeURIComponent("https://www.google.com?p1=박종승&p2=+`~()!")
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "encodeURI"
//				, SCommonV._S_CHA_UTF8
//				, SCommonU.encodeURI("https://www.google.com?p1=박종승&p2=+`~()!")
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "digestToHexString"
//				, "SHA-512"
//				, SCommonU.digestToHexString("SHA-512", SCommonV._S_CHA_UTF8, "seung")
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "digestToHexString"
//				, "SHA-384"
//				, SCommonU.digestToHexString("SHA-384", SCommonV._S_CHA_UTF8, "seung")
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "digestToHexString"
//				, "SHA-256"
//				, SCommonU.digestToHexString("SHA-256", SCommonV._S_CHA_UTF8, "seung")
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "digestToHexString"
//				, "SHA-1"
//				, SCommonU.digestToHexString("SHA-1", SCommonV._S_CHA_UTF8, "seung")
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "digestToHexString"
//				, "MD5"
//				, SCommonU.digestToHexString("MD5", SCommonV._S_CHA_UTF8, "seung")
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "digestToHexString"
//				, "MD2"
//				, SCommonU.digestToHexString("MD2", SCommonV._S_CHA_UTF8, "seung")
//				));
//		System.out.println(String.format(
//				"[%20s:%8s] - %s"
//				, "getUUID"
//				, "UUID"
//				, SCommonU.getUUID("-")
//				));
	}
}
