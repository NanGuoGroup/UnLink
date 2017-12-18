package create_xml;

import java.math.BigInteger;

public class Key {
	
	private String owner;
	private String date;
	
	private BigInteger p;
	private BigInteger n;
	private BigInteger R0;
	private BigInteger R1;
	private BigInteger S;
	private BigInteger Z;
	
	public Key(String owner,String date,BigInteger p,BigInteger n,BigInteger R0,BigInteger R1,BigInteger S,BigInteger Z){
		
		this.owner = owner;
		this.date = date;
		this.p = p;
		this.n = n;
		this.R0 = R0;
		this.R1 = R1;
		this.S = S;
		this.Z = Z;	
	}
	
	public Key(BigInteger p,BigInteger n,BigInteger R0,BigInteger R1,BigInteger S,BigInteger Z){
		this.p = p;
		this.n = n;
		this.R0 = R0;
		this.R1 = R1;
		this.S = S;
		this.Z = Z;	
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigInteger getP() {
		return p;
	}

	public void setP(BigInteger p) {
		this.p = p;
	}

	public BigInteger getN() {
		return n;
	}

	public void setN(BigInteger n) {
		this.n = n;
	}

	public BigInteger getR0() {
		return R0;
	}

	public void setR0(BigInteger r0) {
		R0 = r0;
	}

	public BigInteger getR1() {
		return R1;
	}

	public void setR1(BigInteger r1) {
		R1 = r1;
	}

	public BigInteger getS() {
		return S;
	}

	public void setS(BigInteger s) {
		S = s;
	}

	public BigInteger getZ() {
		return Z;
	}

	public void setZ(BigInteger z) {
		Z = z;
	}
	
}
