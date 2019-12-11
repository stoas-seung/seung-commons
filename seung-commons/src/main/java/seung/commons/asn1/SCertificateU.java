package seung.commons.asn1;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.PBEParameter;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import seung.commons.SCommonU;
import seung.commons.SCommonV;
import seung.commons.SFileU;

public class SCertificateU {

	public static String getProviderProperty(String oid) {
		
		String property = "";
		
		for(Provider provider : Security.getProviders()) {
			provider.getName();
			provider.getVersion();
			provider.getInfo();
			for(Object key : provider.keySet()) {
				if(((String) key).indexOf(oid) > -1) {
					return provider.getProperty((String) key);
				}
			}
		}
		
		return property;
	}
	public static void getSignCertInfo(byte[] der) {
		
		X509Certificate x509Certificate = null;
		try {
			
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(der);
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
			System.out.println(x509Certificate.getType());
			System.out.println(x509Certificate.getVersion());
			System.out.println(x509Certificate.getSerialNumber());
			System.out.println(x509Certificate.getSigAlgOID());
			System.out.println(x509Certificate.getSigAlgName());
			System.out.println(x509Certificate.getIssuerDN());
			System.out.println(x509Certificate.getNotBefore());
			System.out.println(x509Certificate.getNotAfter());
			System.out.println(x509Certificate.getPublicKey());
			System.out.println(Hex.toHexString(x509Certificate.getSignature()));
			System.out.println(x509Certificate.getSubjectDN());
			System.out.println(x509Certificate.getSubjectX500Principal());
			System.out.println(Hex.toHexString(x509Certificate.getEncoded()));
			System.out.println(Hex.toHexString(x509Certificate.getTBSCertificate()));
			ASN1ObjectIdentifier asn1ObjectIdentifier;
			for(String criticalExtensionOIDs : x509Certificate.getCriticalExtensionOIDs()) {
				for(Field field : Extension.class.getFields()) {
					asn1ObjectIdentifier = (ASN1ObjectIdentifier) field.get(Extension.class);
					if(criticalExtensionOIDs.equals(asn1ObjectIdentifier.getId())) {
						System.out.println(criticalExtensionOIDs);
						System.out.println(field.getName());
						break;
					}
				}
			}
			for(String criticalExtensionOIDs : x509Certificate.getNonCriticalExtensionOIDs()) {
				for(Field field : Extension.class.getFields()) {
					asn1ObjectIdentifier = (ASN1ObjectIdentifier) field.get(Extension.class);
					if(criticalExtensionOIDs.equals(asn1ObjectIdentifier.getId())) {
						System.out.println(criticalExtensionOIDs);
						System.out.println(field.getName());
						break;
					}
				}
			}
			
//			System.out.println(Hex.toHexString(x509Certificate.getExtensionValue(Extension.certificatePolicies.toString())));
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SSignCertDerVO readSignCertDerByBase64String(String base64String) {
		return getSSignCertDerVO(SCommonU.decodeBase64(base64String));
	}
	public static SSignCertDerVO readSignCertDerByHexString(String data) {
		return getSSignCertDerVO(Hex.decode(data));
	}
	public static SSignCertDerVO readSignCertDerByPath(String path) {
		
		SSignCertDerVO sSignCertDerVO = null;
		
		try {
			File file = new File(path);
			sSignCertDerVO = readSignCertDerByFile(file);
			sSignCertDerVO.setPath(file.getCanonicalPath());
		} catch (IOException e) {
			sSignCertDerVO.setResult(0);
			sSignCertDerVO.setMessage("" + e);
		}
		
		return sSignCertDerVO;
	}
	public static SSignCertDerVO readSignCertDerByFile(File file) {
		
		SSignCertDerVO sSignCertDerVO = null;
		
		try {
			sSignCertDerVO = getSSignCertDerVO(SFileU.readFileToByteArray(file));
			sSignCertDerVO.setPath(file.getCanonicalPath());
		} catch (IOException e) {
			if(sSignCertDerVO == null) {
				sSignCertDerVO = new SSignCertDerVO();
			}
			sSignCertDerVO.setResult(0);
			sSignCertDerVO.setMessage("" + e);
		}
		
		return sSignCertDerVO;
	}
	public static SSignCertDerVO getSSignCertDerVO(byte[] binaryData) {
		return getSSignCertDerVO(binaryData, SCommonV._S_TIMEZONE_UTC);
	}
	public static SSignCertDerVO getSSignCertDerVO(byte[] binaryData, String timeZone) {
		
		SSignCertDerVO sSignCertDerVO = new SSignCertDerVO();
		
		CertificateFactory certificateFactory = null;
		X509Certificate    x509Certificate    = null;
		
		byte[]            policy            = null;
		ASN1InputStream   asn1InputStream   = null;
		ASN1OctetString   asn1OctetString   = null;
		ASN1Sequence      asn1Sequence      = null;
		PolicyInformation policyInformation = null;
		
		try {
			
//			sSignCertDerVO.setBinaryData(binaryData);
			sSignCertDerVO.setHexString(Hex.toHexString(binaryData));
			sSignCertDerVO.setBase64String(Base64.toBase64String(binaryData));
			
			certificateFactory = CertificateFactory.getInstance("X.509");
			x509Certificate    = (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(binaryData));
			
			sSignCertDerVO.setType(x509Certificate.getType());
			sSignCertDerVO.setVersion(x509Certificate.getVersion());
			sSignCertDerVO.setSerialNumber(x509Certificate.getSerialNumber());
//			sSignCertDerVO.setNotBefore(x509Certificate.getNotBefore());
//			sSignCertDerVO.setNotAfter(x509Certificate.getNotAfter());
			sSignCertDerVO.setTimeZone(timeZone);
			sSignCertDerVO.setNotBeforeTimeZone(SCommonU.getDateString(x509Certificate.getNotBefore(), "yyyyMMddHHmmss", timeZone));
			sSignCertDerVO.setNotAfterTimeZone(SCommonU.getDateString(x509Certificate.getNotAfter(), "yyyyMMddHHmmss", timeZone));
			sSignCertDerVO.setSigAlgName(x509Certificate.getSigAlgName());
			sSignCertDerVO.setSigAlgOID(x509Certificate.getSigAlgOID());
			sSignCertDerVO.setIssuerDNName(x509Certificate.getIssuerDN().getName());
			sSignCertDerVO.setSubjectDNName(x509Certificate.getSubjectDN().getName());
			sSignCertDerVO.setLeftDays(SCommonU.dateDiff(SCommonV._S_TIME_DAYS, new Date(), x509Certificate.getNotAfter()));
			
			policy          = x509Certificate.getExtensionValue(Extension.certificatePolicies.toString());
			asn1InputStream = new ASN1InputStream(new ByteArrayInputStream(policy));
			asn1OctetString = (ASN1OctetString) asn1InputStream.readObject();
			asn1InputStream.close();
			asn1InputStream = new ASN1InputStream(new ByteArrayInputStream(asn1OctetString.getOctets()));
			asn1Sequence    = (ASN1Sequence) asn1InputStream.readObject();
			asn1InputStream.close();
			if(asn1Sequence.size() > 0) {
				policyInformation = PolicyInformation.getInstance(asn1Sequence.getObjectAt(0));
				sSignCertDerVO.setPolicyOID(policyInformation.getPolicyIdentifier().getId());
			}
			
			sSignCertDerVO.setResult(1);
			
		} catch (CertificateException e) {
			sSignCertDerVO.setMessage("" + e);
		} catch (IOException e) {
			sSignCertDerVO.setMessage("" + e);
		}
		
		return sSignCertDerVO;
	}
	
//	public static SSignPriKeyVO getSSignPriKeyVO(byte[] binaryData) {
//		
//		SSignPriKeyVO sSignPriKeyVO = new SSignPriKeyVO();
//		
//		EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = null;
//		ASN1ObjectIdentifier    asn1ObjectIdentifier    = null;
//		ASN1Encodable           asn1Encodable           = null;
//		byte[]                  encryptedData           = null;
//		
//		encryptedPrivateKeyInfo = EncryptedPrivateKeyInfo.getInstance(binaryData);
//		asn1ObjectIdentifier    = encryptedPrivateKeyInfo.getEncryptionAlgorithm().getAlgorithm();
//		asn1Encodable           = encryptedPrivateKeyInfo.getEncryptionAlgorithm().getParameters();
//		encryptedData           = encryptedPrivateKeyInfo.getEncryptedData();
//		
//		PBEParameter    pbeParameter    = null;
//		PBES2Parameters pbes2Parameters = null;
//		PBKDF2Params    pbkdf2Params    = null;
//		byte[]          salt            = null;
//		BigInteger      iterationCount  = null;
//		
//		if(
//			KISAObjectIdentifiers.id_seedCBC.equals(asn1ObjectIdentifier)
//			|| KISAObjectIdentifiers.pbeWithSHA1AndSEED_CBC.equals(asn1ObjectIdentifier)
//			) {
//			pbeParameter   = PBEParameter.getInstance(asn1Encodable);
//			salt           = pbeParameter.getSalt();
//			iterationCount = pbeParameter.getIterationCount();
//		} else if(
//			PKCSObjectIdentifiers.id_PBES2.equals(asn1ObjectIdentifier)
//			) {
//			pbes2Parameters = PBES2Parameters.getInstance(asn1Encodable);
//			
//		}
//		
//		return sSignPriKeyVO;
//	}
	
}
