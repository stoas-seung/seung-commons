package seung.commons.asn1;

import java.math.BigInteger;

import seung.commons.SCommonV;

public class SSignCertDerVO {

	private int        result        = 0;
	private String     message       = "";
	
//	private byte[]     binaryData;
	private String     hexString;
	private String     base64String;
	
	private String     path;
	private String     type;
	private int        version;
	private BigInteger serialNumber;
//	private Date       notBefore;
//	private Date       notAfter;
	private String     sigAlgName;
	private String     sigAlgOID;
	private String     issuerDNName;
	private String     subjectDNName;
	private String     policyOID;
	
	private String     timeZone          = SCommonV._S_TIMEZONE_UTC;
	private String     notBeforeTimeZone;
	private String     notAfterTimeZone;
	private String     leftDays;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
//	public byte[] getBinaryData() {
//		return binaryData;
//	}
//	public void setBinaryData(byte[] binaryData) {
//		this.binaryData = binaryData;
//	}
	public String getHexString() {
		return hexString;
	}
	public void setHexString(String hexString) {
		this.hexString = hexString;
	}
	public String getBase64String() {
		return base64String;
	}
	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public BigInteger getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(BigInteger serialNumber) {
		this.serialNumber = serialNumber;
	}
//	public Date getNotBefore() {
//		return notBefore;
//	}
//	public void setNotBefore(Date notBefore) {
//		this.notBefore = notBefore;
//	}
//	public Date getNotAfter() {
//		return notAfter;
//	}
//	public void setNotAfter(Date notAfter) {
//		this.notAfter = notAfter;
//	}
	public String getSigAlgName() {
		return sigAlgName;
	}
	public void setSigAlgName(String sigAlgName) {
		this.sigAlgName = sigAlgName;
	}
	public String getSigAlgOID() {
		return sigAlgOID;
	}
	public void setSigAlgOID(String sigAlgOID) {
		this.sigAlgOID = sigAlgOID;
	}
	public String getIssuerDNName() {
		return issuerDNName;
	}
	public void setIssuerDNName(String issuerDNName) {
		this.issuerDNName = issuerDNName;
	}
	public String getSubjectDNName() {
		return subjectDNName;
	}
	public void setSubjectDNName(String subjectDNName) {
		this.subjectDNName = subjectDNName;
	}
	public String getPolicyOID() {
		return policyOID;
	}
	public void setPolicyOID(String policyOID) {
		this.policyOID = policyOID;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getNotBeforeTimeZone() {
		return notBeforeTimeZone;
	}
	public void setNotBeforeTimeZone(String notBeforeTimeZone) {
		this.notBeforeTimeZone = notBeforeTimeZone;
	}
	public String getNotAfterTimeZone() {
		return notAfterTimeZone;
	}
	public void setNotAfterTimeZone(String notAfterTimeZone) {
		this.notAfterTimeZone = notAfterTimeZone;
	}
	public String getLeftDays() {
		return leftDays;
	}
	public void setLeftDays(String leftDays) {
		this.leftDays = leftDays;
	}
	
}
