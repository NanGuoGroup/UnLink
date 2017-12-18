package issue_credential;

import java.math.BigInteger;
import java.security.SecureRandom;

import initialization.Method;
import initialization.SetUp;

public class ZKP_sign {
	
	Method method = new Method();
	
	public BigInteger createRandomU0(){
		
		SecureRandom random = new SecureRandom();
	    BigInteger u0 = BigInteger.probablePrime(SetUp.Lu1, random);
	    return u0;
	}
	
	public BigInteger createR1(BigInteger n){
		
		BigInteger r1 = new BigInteger(SetUp.Ln, Method.rnd);
		
		if(r1.compareTo(n)<0){
			return r1;
		}else{
			return createR1(n);
		}
	}
	
	public BigInteger computeC(BigInteger R0,BigInteger S,BigInteger u0,BigInteger r1,BigInteger n){
		
		BigInteger b1 = R0.modPow(u0, n);
		BigInteger b2 = S.modPow(r1, n);	
		BigInteger C = (b1.multiply(b2)).mod(n);		
		return C;
	}
	
	public boolean prove(BigInteger C,BigInteger R0,BigInteger S,BigInteger u0,BigInteger r1,BigInteger n){
		
		int len1 = SetUp.Lu + SetUp.Lc;
		int len2 = SetUp.Lr1 + SetUp.Lc;

		BigInteger x1 = method.createX(len1, n);
		BigInteger x2 = method.createX(len2, n);
		BigInteger C1 = computeC(R0, S, x1, x2, n);
		
		BigInteger c_temp = method.Hash(C, C1);
		BigInteger c = c_temp.mod(SetUp.BI2.pow(SetUp.Lc));

		BigInteger y1 = x1.add(u0.multiply(c));
		BigInteger y2 = x2.add(r1.multiply(c));
		
		BigInteger C2 = computeC(R0, S, y1, y2, n);
		BigInteger C1Cc = C1.multiply(C.modPow(c, n)).mod(n);
		
		BigInteger u0_max = SetUp.BI2.pow(len1+1);
		BigInteger r1_max = SetUp.BI2.pow(len2+1);
			
		boolean result1 = C1Cc.equals(C2);
		boolean result2 = y1.compareTo(u0_max)==-1;	
		boolean result3 = y2.compareTo(r1_max)==-1;	
			
		if(result1 && result2 && result3){
			return true;
		}else{
			return false;
		}
		
	}
}
