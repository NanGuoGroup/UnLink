package issue_credential;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import initialization.SetUp;


public class CLSigner {

	SecureRandom rnd = new SecureRandom();
	
	public BigInteger createRandomE(){
			
		BigInteger b1 = SetUp.BI2.pow(SetUp.Le-1).add(SetUp.BI2.pow(SetUp.Le-2));
		BigInteger b2 = SetUp.BI2.pow(SetUp.Le-4-SetUp.Lc-SetUp.Lz); 
		
		BigInteger b3 = b1.subtract(b2);
		BigInteger b4 = b1.add(b2);
	
		BigDecimal b5 = new BigDecimal(b4.subtract(b3));
		
		BigDecimal ran = new BigDecimal(Math.random());
		BigDecimal b6 = ran.multiply(b5);
	
		BigInteger b7 = b3.add(b6.toBigInteger());
		
		BigInteger e = b7.nextProbablePrime();
		
		if(e.compareTo(b3)==1 && e.compareTo(b4)==-1){
			return e;
		}else{
			return createRandomE();
		}		
	}
	
	public BigInteger createRandomU(){
		
		BigInteger u = BigInteger.probablePrime(SetUp.Lu1, rnd);
		if (u.isProbablePrime(200)){
			return u; 
		}            
        else {
        	 return (createRandomU()); 
        } 
	}
	
	public BigInteger createRandomV(){
	
		BigInteger v = new BigInteger(SetUp.Lv, rnd);
		return v; 	 
	}
			
	public BigInteger sign(BigInteger p,BigInteger n,BigInteger e,BigInteger u1,BigInteger r2,BigInteger C,BigInteger R1,BigInteger S,BigInteger Z){
					
		BigInteger q = n.divide(p);
		BigInteger p1 = (p.subtract(SetUp.BI1)).divide(SetUp.BI2);
		BigInteger q1 = (q.subtract(SetUp.BI1)).divide(SetUp.BI2);
		BigInteger n1 = p1.multiply(q1);
				
		BigInteger inver_e = e.modInverse(n1);
		
		BigInteger R11 = R1.modPow(u1, n);
		BigInteger S1 = S.modPow(r2, n);
						
		BigInteger RS1 = (C.multiply(R11).multiply(S1)).mod(n);	
		BigInteger RS = RS1.modInverse(n);		
		BigInteger b1 = Z.multiply(RS).mod(n);
		BigInteger A = b1.modPow(inver_e, n);
		
		return A;	
	}	
		
	public boolean verify(BigInteger Z,BigInteger A,BigInteger R0,BigInteger R1,BigInteger S,
			  BigInteger e,BigInteger u0,BigInteger u1,BigInteger v,BigInteger n){

		BigInteger b1 = A.modPow(e, n);
		BigInteger b2 = R0.modPow(u0, n);
		BigInteger b3 = R1.modPow(u1, n);
		BigInteger b4 = S.modPow(v, n);
		BigInteger Z1 = (b1.multiply(b2).multiply(b3).multiply(b4)).mod(n);

		BigInteger e_min = SetUp.BI2.pow(SetUp.Le-1);
		BigInteger e_max = SetUp.BI2.pow(SetUp.Le);

		boolean result1 = Z.equals(Z1);
		boolean result2 = (u0.bitLength()<SetUp.Lu) && (u1.bitLength()<SetUp.Lu);
		boolean result3 = (e.compareTo(e_min)==1) && (e.compareTo(e_max)==-1);

		if(result1 && result2 && result3){
			return true;
		}else{
			return false;
		}	
}
	
}
