package initialization;

import java.math.BigInteger;

import create_xml.Key;
import create_xml.XML_Write;

public class KeyGen {
	
	public BigInteger createPrimeP(){
		
		BigInteger p1 = BigInteger.probablePrime(SetUp.Ln/2, Method.rnd);
		BigInteger p = p1.multiply(SetUp.BI2).add(SetUp.BI1);
		
		if (p.isProbablePrime(200)){
			return p;	
		}            
        else {
        	 return (createPrimeP()); 
        }         
	}

	public BigInteger createModulus(BigInteger p){
		
		BigInteger q =  createPrimeP();
		BigInteger n = p.multiply(q);
		return n;
		
	}
	
	public BigInteger createZnX(BigInteger n){
		
		BigInteger ZnX = new BigInteger(SetUp.Ln, Method.rnd);
		
		if(ZnX.gcd(n).equals(SetUp.BI1)&& ZnX.compareTo(n)<0){
			return ZnX;
		}else{
			return createZnX(n);
		}
	}
	
	public BigInteger createQRn(BigInteger n){
		  
		BigInteger ZnX = createZnX(n);
		BigInteger QRn = ZnX.modPow(SetUp.BI2, n);
		
		if(QRn.gcd(n).equals(SetUp.BI1)&& QRn.compareTo(n)<0){
			return QRn;
		}else{
			return createQRn(n);
		}	
	}
	
	public Key generate_Key_SNSP(){
		
		
		BigInteger p_SNSP  = createPrimeP();
		BigInteger n_SNSP  = createModulus(p_SNSP);
		BigInteger R0_SNSP = createQRn(n_SNSP);
		BigInteger R1_SNSP = createQRn(n_SNSP);
		BigInteger S_SNSP  = createQRn(n_SNSP);
		BigInteger Z_SNSP  = createQRn(n_SNSP);
		
		Key key_SNSP = new Key("SNSP", Method.getTime(), p_SNSP, n_SNSP, R0_SNSP, R1_SNSP, S_SNSP, Z_SNSP);
		return key_SNSP;
	}
	
	public Key generate_Key_Bob(){
		
		BigInteger p_Bob  = createPrimeP();
		BigInteger n_Bob  = createModulus(p_Bob);
		BigInteger R0_Bob = createQRn(n_Bob);
		BigInteger R1_Bob = createQRn(n_Bob);
		BigInteger S_Bob  = createQRn(n_Bob);
		BigInteger Z_Bob  = createQRn(n_Bob);
		
		Key key_Bob = new Key("Bob", Method.getTime(), p_Bob, n_Bob, R0_Bob, R1_Bob, S_Bob, Z_Bob);
		return key_Bob;
	}
	
	
	public void create_Keys(Key k1,Key k2){
		
		XML_Write xw = new XML_Write();
		try {
			xw.Build_Keys(k1, k2);
			System.out.println("Keys.xml文件已经生成。");
		} catch (Exception e) {
			System.out.println("Keys.xml文件生成过程异常。");
		}
	}
	
	public static void main(String[] args) {
		
		long time1 = System.currentTimeMillis();
		
		KeyGen keyGen = new KeyGen();
		Key k1 = keyGen.generate_Key_SNSP();
		Key k2 = keyGen.generate_Key_Bob();
		keyGen.create_Keys(k1, k2);
		
		long time2 = System.currentTimeMillis();
		System.out.println("该阶段耗时:"+(time2-time1)+"ms");
	}
}
