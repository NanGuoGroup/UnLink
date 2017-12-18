package create_xml;

import java.math.BigInteger;

public class UserPrime {
	
	
	private BigInteger u0;
	private BigInteger u1_SNSP;
	private BigInteger u1_Bob;
	
	public UserPrime(BigInteger u0,BigInteger u1_SNSP,BigInteger u1_Bob){
		
		this.u0 = u0;
		this.u1_SNSP = u1_SNSP;
		this.u1_Bob = u1_Bob;		
	}

	public BigInteger getU0() {
		return u0;
	}

	public void setU0(BigInteger u0) {
		this.u0 = u0;
	}

	public BigInteger getU1_SNSP() {
		return u1_SNSP;
	}

	public void setU1_SNSP(BigInteger u1_SNSP) {
		this.u1_SNSP = u1_SNSP;
	}

	public BigInteger getU1_Bob() {
		return u1_Bob;
	}

	public void setU1_Bob(BigInteger u1_Bob) {
		this.u1_Bob = u1_Bob;
	}
}
