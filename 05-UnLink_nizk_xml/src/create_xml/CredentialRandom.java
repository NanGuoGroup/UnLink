package create_xml;

import java.math.BigInteger;

public class CredentialRandom {

	private String issuer;
	private String date;
	
    private BigInteger e; 
    private BigInteger v1;
    private BigInteger A1;  
      
    public CredentialRandom(String issuer,String date,BigInteger e,BigInteger v1,BigInteger A1){
    	
    	this.issuer = issuer;
    	this.date = date;
   	
    	this.e = e;
    	this.A1 = A1;
    	this.v1 = v1;
    }
     
    public CredentialRandom(BigInteger e,BigInteger v1,BigInteger A1){
    	   	
    	this.e = e;
    	this.A1 = A1;
    	this.v1 = v1;
    }
    
	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public BigInteger getE() {
		return e;
	}
	
	public void setE(BigInteger e) {
		this.e = e;
	}
	
	public BigInteger getA1() {
		return A1;
	}
	
	public void setA1(BigInteger a1) {
		A1 = a1;
	}
	
	public BigInteger getV1() {
		return v1;
	}
	
	public void setV1(BigInteger v1) {
		this.v1 = v1;
	} 	
	
}
