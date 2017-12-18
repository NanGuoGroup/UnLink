package initialization;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Method {
	
	public static SecureRandom rnd = new SecureRandom();
		
	public static String getTime(){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		return time;
	}
	
	public  BigInteger Hash(BigInteger ...b){
		
	    BigInteger c = null;
	    StringBuilder sb = new StringBuilder();	    
		try {
			  for(int i=0;i<b.length;i++){
				  sb = sb.append(b[i]);
			  }
			  byte[] bytes = sb.toString().getBytes();
		      MessageDigest md = MessageDigest.getInstance("SHA-256");
		      md.update(bytes);
		      byte[] digesta = md.digest();
		      c = new BigInteger(digesta).abs();		      
		   }
		   catch (java.security.NoSuchAlgorithmException ex) {
		     System.out.println("非法摘要算法");
		   }
		return c;
	}
	
	public BigInteger createX(int len,BigInteger n){
		
		BigInteger x = new BigInteger(len,rnd);
		if(x.gcd(n).equals(SetUp.BI1)){
			return x;
		}else{
			return createX(len,n);
		}	
	}

}
