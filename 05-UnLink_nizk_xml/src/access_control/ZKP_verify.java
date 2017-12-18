package access_control;

import java.math.BigInteger;

import initialization.Method;
import initialization.SetUp;


public class ZKP_verify {
	
	Method method = new Method();

	private int len = SetUp.Lu + SetUp.Lc;
	
	public BigInteger compute_Z(BigInteger x1,BigInteger x2,BigInteger x3,BigInteger x4,BigInteger n,
			   BigInteger R0,BigInteger R1,BigInteger A1,BigInteger S){

		BigInteger b1 = A1.modPow(x3, n);
		BigInteger b2 = R0.modPow(x1, n);
		BigInteger b3 = R1.modPow(x2, n);
		BigInteger b4 = S.modPow(x4, n);
		BigInteger Z_Alice = (b1.multiply(b2).multiply(b3).multiply(b4)).mod(n);
		return Z_Alice;
	}

	public BigInteger compute_D(BigInteger x1,BigInteger x2,BigInteger g,BigInteger h,BigInteger n){

		BigInteger b1 = g.modPow(x1, n);
		BigInteger b2 = h.modPow(x2, n);		
		BigInteger D = (b1.multiply(b2)).mod(n);
		return D;
	}

	public BigInteger compute_gE(BigInteger x1,BigInteger x2,BigInteger h,BigInteger D,BigInteger n){

		BigInteger b1 = D.modPow(x1, n);
		BigInteger b2 = h.modPow(x2, n);		
		BigInteger gE_Alice = (b1.multiply(b2)).mod(n);
		return gE_Alice;
	}
	
	public boolean prove_interval(BigInteger e,BigInteger u0,BigInteger u1,BigInteger c){
				
		BigInteger el = (SetUp.BI2.pow(SetUp.Le-1)).add(SetUp.BI1);
		BigInteger er = (SetUp.BI2.pow(SetUp.Le)).subtract(SetUp.BI1);
		BigInteger e_min = c.multiply(el);
		BigInteger e_max = c.multiply(er); 
		BigInteger u_max = SetUp.BI2.pow(len+1);
		
		boolean result1 = (e.compareTo(e_min)==1) && (e.compareTo(e_max)==-1);
		boolean result2 = u0.compareTo(u_max)==-1 && u1.compareTo(u_max)==-1;
		
		if(result1&&result2){
			return true;
		}else{
			return false;
		}
	}
	public boolean prove_SNSP(BigInteger e_SNSP,BigInteger v1_SNSP,BigInteger u0_Alice,BigInteger u1_SNSP_Alice,BigInteger Z_SNSP,
						  BigInteger R0_SNSP,BigInteger R1_SNSP,BigInteger A1_SNSP,BigInteger S_SNSP,BigInteger n_SNSP){
				
		BigInteger x1 = method.createX(SetUp.Le, n_SNSP);
		BigInteger x2 = method.createX(SetUp.Ln, n_SNSP);
		BigInteger x3 = method.createX(len, n_SNSP);
		BigInteger x4 = method.createX(len, n_SNSP);
		BigInteger Z1 = compute_Z(x3, x4, x1, x2, n_SNSP, R0_SNSP, R1_SNSP, A1_SNSP, S_SNSP);
		
		BigInteger c_temp = method.Hash(Z_SNSP, Z1);
		BigInteger c = c_temp.mod(SetUp.BI2.pow(SetUp.Lc));
		
		BigInteger y1 = x1.add(c.multiply(e_SNSP));
		BigInteger y2 = x2.add(c.multiply(v1_SNSP));
		BigInteger y3 = x3.add(c.multiply(u0_Alice));
		BigInteger y4 = x4.add(c.multiply(u1_SNSP_Alice));
				
		BigInteger Z2 = compute_Z(y3, y4, y1, y2, n_SNSP, R0_SNSP, R1_SNSP, A1_SNSP, S_SNSP);
		BigInteger Z1Zc = (Z_SNSP.modPow(c, n_SNSP).multiply(Z1)).mod(n_SNSP);
		
		boolean result1 = Z2.equals(Z1Zc);
		boolean result2 = prove_interval(y1,y3,y4,c);
				
		if(result1&&result2){
			return true;
		}else{
			return false;
		}
	}
	public boolean prove_Bob(BigInteger e_Bob,BigInteger v1_Bob,BigInteger u0_Alice,BigInteger u1_Bob_Alice,BigInteger a,BigInteger rho,BigInteger rho1,BigInteger Z_Bob,
			     BigInteger R0_Bob,BigInteger R1_Bob,BigInteger A1_Bob,BigInteger S_Bob,BigInteger D,BigInteger g,BigInteger h,BigInteger E,BigInteger n_Bob){
				
		BigInteger gE = g.modPow(E, n_Bob);		
		
		BigInteger x1 = method.createX(SetUp.Le, n_Bob);
		BigInteger x2 = method.createX(SetUp.Ln, n_Bob);
		BigInteger x3 = method.createX(len, n_Bob);
		BigInteger x4 = method.createX(len, n_Bob);
		BigInteger x5 = method.createX(SetUp.Ln, n_Bob);
		BigInteger x6 = method.createX(SetUp.Ln, n_Bob);
		BigInteger x7 = method.createX(SetUp.Ln, n_Bob);
		
		BigInteger Z1 = compute_Z(x3, x4, x1, x2, n_Bob, R0_Bob, R1_Bob, A1_Bob, S_Bob);
		BigInteger D1 = compute_D(x4, x6, g, h, n_Bob);
		BigInteger gE1 = compute_gE(x5, x7, h, D, n_Bob);
				
		BigInteger c_temp = method.Hash(Z_Bob,Z1,D,D1,gE,gE1);
		BigInteger c = c_temp.mod(SetUp.BI2.pow(SetUp.Lc));
			
		BigInteger y1 = x1.add(c.multiply(e_Bob));
		BigInteger y2 = x2.add(c.multiply(v1_Bob));
		BigInteger y3 = x3.add(c.multiply(u0_Alice));
		BigInteger y4 = x4.add(c.multiply(u1_Bob_Alice));
		BigInteger y5 = x5.add(c.multiply(a));
		BigInteger y6 = x6.add(c.multiply(rho));
		BigInteger y7 = x7.add(c.multiply(rho1));
				
		BigInteger Z2 = compute_Z(y3, y4, y1, y2, n_Bob, R0_Bob, R1_Bob, A1_Bob, S_Bob);
		BigInteger Z1Zc = (Z_Bob.modPow(c, n_Bob).multiply(Z1)).mod(n_Bob);

		BigInteger D2 = compute_D(y4, y6, g, h, n_Bob);
		BigInteger D1Dc = (D.modPow(c, n_Bob).multiply(D1)).mod(n_Bob);

		BigInteger gE2 = compute_gE(y5, y7, h, D, n_Bob);
		BigInteger gE1gEc = (gE.modPow(c, n_Bob).multiply(gE1)).mod(n_Bob);
				
		boolean result1 = Z2.equals(Z1Zc);
		boolean result2 = D1Dc.equals(D2);
		boolean result3 = gE1gEc.equals(gE2);
		boolean result4 = prove_interval(y1,y3,y4,c);
				
		if(result1&&result2&&result3&&result4){
			return true;
		}else{
			return false;
		}	
	}	
}
