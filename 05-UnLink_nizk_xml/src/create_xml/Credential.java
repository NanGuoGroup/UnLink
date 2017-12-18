package create_xml;

import java.math.BigInteger;

public class Credential {
	
	private String issuer;
	private String date;
	
    private BigInteger e; 
    private BigInteger v;
    private BigInteger A;  
      
    public Credential(String issuer,String date,BigInteger e,BigInteger v,BigInteger A){
    	
    	this.issuer = issuer;
    	this.date = date;
   
    	this.e = e;
    	this.A = A;
    	this.v = v;
    }
     
    public Credential(BigInteger e,BigInteger v,BigInteger A){
    	   	
    	this.e = e;
    	this.A = A;
    	this.v = v;
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
	
	public BigInteger getA() {
		return A;
	}
	
	public void setA(BigInteger a) {
		A = a;
	}
	
	public BigInteger getV() {
		return v;
	}
	
	public void setV(BigInteger v) {
		this.v = v;
	} 
}
