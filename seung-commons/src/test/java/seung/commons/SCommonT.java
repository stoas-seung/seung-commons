package seung.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class SCommonT {

	@Test
	public void test() throws FileNotFoundException, IOException {
		try {
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "getUUID"
					, "UUID"
					, SCommonU.getUUID("-")
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "digestToHexString"
					, "MD2"
					, SCommonU.digestToHexString("MD2", SCommonV._S_CHA_UTF8, "seung")
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "digestToHexString"
					, "MD5"
					, SCommonU.digestToHexString("MD5", SCommonV._S_CHA_UTF8, "seung")
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "digestToHexString"
					, "SHA-1"
					, SCommonU.digestToHexString("SHA-1", SCommonV._S_CHA_UTF8, "seung")
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "digestToHexString"
					, "SHA-256"
					, SCommonU.digestToHexString("SHA-256", SCommonV._S_CHA_UTF8, "seung")
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "digestToHexString"
					, "SHA-384"
					, SCommonU.digestToHexString("SHA-384", SCommonV._S_CHA_UTF8, "seung")
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "digestToHexString"
					, "SHA-512"
					, SCommonU.digestToHexString("SHA-512", SCommonV._S_CHA_UTF8, "seung")
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "encodeURI"
					, SCommonV._S_CHA_UTF8
					, SCommonU.encodeURI("https://www.google.com?p1=박종승&p2=+`~()!")
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "encodeURIComponent"
					, SCommonV._S_CHA_UTF8
					, SCommonU.encodeURIComponent("https://www.google.com?p1=박종승&p2=+`~()!")
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "encodeURI"
					, SCommonV._S_CHA_EUCKR
					, SCommonU.encodeURI("https://www.google.com?p1=박종승&p2=+`~()!", SCommonV._S_CHA_EUCKR)
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "encodeURIComponent"
					, SCommonV._S_CHA_EUCKR
					, SCommonU.encodeURIComponent("https://www.google.com?p1=박종승&p2=+`~()!", SCommonV._S_CHA_EUCKR)
					));
//			System.out.println(String.format(
//					"[%20s:%8s] - %s"
//					, "readXls"
//					, ""
//					, SCommonU.toJson(SExcelU.readXls("docs/public_data_1.xlsx"), true)
//					));
//			System.out.println(String.format(
//					"[%20s:%8s] - %s"
//					, "getNetworks"
//					, ""
//					, SCommonU.getNetworks().toJson(true)
//					));
//			System.out.println(String.format(
//					"[%20s:%8s] - %s"
//					, "getSystemProperties"
//					, ""
//					, SCommonU.getSystemProperties().toJson(true)
//					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "getDirPath"
					, ""
					, SFileU.getDirectoryPath("docs/public_data_1.xlsx")
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "getFileInfo"
					, ""
					, SFileU.getFileInfo("docs/public_data_1.xlsx").toJson(true)
					));
			System.out.println(String.format(
					"[%20s:%8s] - %s"
					, "getFileListInfo"
					, ""
					, SCommonU.toJson(SFileU.getFileListInfo(new File("").getAbsolutePath()), true)
					));
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
