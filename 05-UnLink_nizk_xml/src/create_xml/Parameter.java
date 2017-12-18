package create_xml;

import java.math.BigInteger;

public class Parameter {


	private BigInteger a;
	private BigInteger rho;
	private BigInteger rho1;
	
	private BigInteger D;
	private BigInteger g;
	private BigInteger h;
		
	public Parameter(BigInteger a,BigInteger rho,BigInteger rho1,BigInteger D,BigInteger g,BigInteger h){
				
		this.a = a;
		this.rho = rho;
		this.rho1 = rho1;
				
		this.D = D;
		this.g = g;
		this.h = h;
	}
	
	public BigInteger getA() {
		return a;
	}

	public void setA(BigInteger a) {
		this.a = a;
	}

	public BigInteger getRho() {
		return rho;
	}

	public void setRho(BigInteger rho) {
		this.rho = rho;
	}

	public BigInteger getRho1() {
		return rho1;
	}

	public void setRho1(BigInteger rho1) {
		this.rho1 = rho1;
	}

	public BigInteger getD() {
		return D;
	}

	public void setD(BigInteger d) {
		D = d;
	}

	public BigInteger getG() {
		return g;
	}

	public void setG(BigInteger g) {
		this.g = g;
	}

	public BigInteger getH() {
		return h;
	}

	public void setH(BigInteger h) {
		this.h = h;
	}
}
