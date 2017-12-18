package access_control;

import java.math.BigInteger;
import create_xml.*;
import initialization.*;


public class RequestGenerator {
	
	XML_Read xr = new XML_Read();
	XML_Write xw = new XML_Write();
	
	BigInteger[] key_para = xr.key_read();
	Key key_SNSP = xr.Key_SNSP(key_para);
	Key key_Bob = xr.Key_Bob(key_para);
	
	BigInteger[] cre_para = xr.cre_read();
	Credential cre_SNSP = xr.Cre_SNSP(cre_para);
	Credential cre_Bob = xr.Cre_Bob(cre_para);
	
	BigInteger[] user_para = xr.user_read();
	UserPrime user = xr.User_Alice(user_para);
	
	String[] policy_para = xr.policy_read();
	Policy policy = xr.getPolicy(policy_para);
	
	
	
	public BigInteger compute_a(BigInteger u1,BigInteger E){
		BigInteger a = E.divide(u1);
		return a;
	}	
	
	public BigInteger createRho(BigInteger n){
		
		BigInteger ro = new BigInteger(15,Method.rnd);
		if(ro.gcd(n).equals(SetUp.BI1)){
			return ro;
		}else{
			return createRho(n);
		}
	}
	
	public BigInteger compute_ro1(BigInteger ro,BigInteger a){
		BigInteger ro1 = (a.multiply(ro)).negate();
		return ro1;
	}
	
	public BigInteger compute_D(BigInteger x1,BigInteger x2,BigInteger g,BigInteger h,BigInteger n){

		BigInteger b1 = g.modPow(x1, n);
		BigInteger b2 = h.modPow(x2, n);		
		BigInteger D = (b1.multiply(b2)).mod(n);
		return D;
	}
	
	public Parameter compute_Para_ZKP(){
		
		KeyGen keyGen = new KeyGen();
		
		BigInteger n  = key_Bob.getN();
		BigInteger u1 = user.getU1_Bob();
		BigInteger E = new BigInteger(policy.getE());
		
		BigInteger a = compute_a(u1, E);
		BigInteger rho = createRho(n);
		BigInteger rho1 = compute_ro1(rho, a);
		BigInteger g  = keyGen.createQRn(n);
		BigInteger h  = keyGen.createQRn(n);
		BigInteger D = compute_D(u1, rho, g, h, n);
				
		Parameter para_Bob = new Parameter(a,rho,rho1,D,g,h);
		return para_Bob;
	}
	
	public BigInteger createRandomR3(){
		
		BigInteger r3 = new BigInteger(SetUp.Lr3, Method.rnd);
		return r3;
	}
	
	public BigInteger randomA(BigInteger n,BigInteger A, BigInteger S, BigInteger r){
		
		BigInteger S1 = S.modInverse(n);
		BigInteger A1 = A.multiply(S1.modPow(r,n)).mod(n);
		return A1;		
	}
	
	public BigInteger RandomV(BigInteger v,BigInteger e,BigInteger r){
		
		BigInteger v1 = v.add(e.multiply(r));		
		return v1; 	 
	}
	
	public CredentialRandom  compute_CreRan_SNSP(){
		
		String issuer = "SNSP";
		String date = Method.getTime();
				
		BigInteger e = cre_SNSP.getE();
		BigInteger v = cre_SNSP.getV();
		BigInteger A = cre_SNSP.getA();
		
		BigInteger n = key_SNSP.getN();
		BigInteger S = key_SNSP.getS();
		
		BigInteger r = createRandomR3();
		BigInteger v1 = RandomV(v, e, r);
		BigInteger A1 = randomA(n, A, S, r);
		
		CredentialRandom c1_1 = new CredentialRandom(issuer,date,e,v1,A1);
		return c1_1;
	}
	
	public CredentialRandom  compute_CreRan_Bob(){
		
		String issuer = "Bob";
		String date = Method.getTime();
				
		BigInteger e = cre_Bob.getE();
		BigInteger v = cre_Bob.getV();
		BigInteger A = cre_Bob.getA();
		
		BigInteger n = key_Bob.getN();
		BigInteger S = key_Bob.getS();
		
		BigInteger r = createRandomR3();
		BigInteger v1 = RandomV(v, e, r);
		BigInteger A1 = randomA(n, A, S, r);
		
		CredentialRandom c2_1 = new CredentialRandom(issuer,date,e,v1,A1);
		return c2_1;
	}
	
	public void create_CredentialsRandom(){
		
		CredentialRandom c1_1 = compute_CreRan_SNSP();
		CredentialRandom c2_1 = compute_CreRan_Bob();
		
		try {
			xw.Build_CredentialRandom(c1_1, c2_1);			
		} catch (Exception e) {
			System.out.println("CredentialsRandom.xml文件生成过程异常。");
		}
	} 
	
	public void create_Request(){
	
		Build_ID bd_id = new Build_ID();
		String request_id = bd_id.Build_Request_ID();
		String content_id = policy.getContent_id();
		Request r = new Request(Method.getTime(),request_id,"Bob",content_id,"read");

		try {
			xw.Build_Request(r);
			System.out.println("Request.xml文件已经生成。");
		} catch (Exception e) {
			System.out.println("Request.xml文件生成过程异常。");
		}
	}
	
	public void create_Parameter(Parameter p){
		
		try {
			xw.Build_Parameter(p);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Parameters.xml文件生成过程异常。");
		}
	}
	
	public static void main(String[] args) {
				
		long time1 = System.currentTimeMillis();
				
		RequestGenerator pg = new RequestGenerator();	
		Parameter p = pg.compute_Para_ZKP();
		pg.create_CredentialsRandom();
		pg.create_Parameter(p);
		pg.create_Request();
		
		long time2 = System.currentTimeMillis();
		System.out.println("该阶段耗时:"+(time2-time1)+"ms");
	}
}
