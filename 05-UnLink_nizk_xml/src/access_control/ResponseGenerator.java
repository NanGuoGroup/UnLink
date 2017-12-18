package access_control;

import java.math.BigInteger;

import create_xml.*;
import initialization.Method;

public class ResponseGenerator {
	
	XML_Read xr = new XML_Read();
	BigInteger[] key_para = xr.key_read();
	Key key_SNSP = xr.Key_SNSP(key_para);
	Key key_Bob = xr.Key_Bob(key_para);
	
	BigInteger[] cre_para = xr.creran_read();
	CredentialRandom creran_SNSP = xr.CreRan_SNSP(cre_para);
	CredentialRandom creran_Bob = xr.CreRan_Bob(cre_para);
	
	BigInteger[] user_para = xr.user_read();
	UserPrime user = xr.User_Alice(user_para);
	
	BigInteger[] par_para = xr.para_read();
	Parameter par_zkp = xr.Par_ZKP(par_para);
	
	String[] policy_para = xr.policy_read();
	Policy policy = xr.getPolicy(policy_para);
	
	String[] request_para = xr.request_read();
	Request request = xr.getRequest(request_para);
	
	ZKP_verify zkp = new ZKP_verify();
	
	public String result;
	
	public ResponseGenerator(){
		
		BigInteger n_SNSP  = key_SNSP.getN();
		BigInteger R0_SNSP = key_SNSP.getR0();
		BigInteger R1_SNSP  = key_SNSP.getR1();
		BigInteger S_SNSP  = key_SNSP.getS();
		BigInteger Z_SNSP  = key_SNSP.getZ();
		BigInteger e_SNSP = creran_SNSP.getE();
		BigInteger v1_SNSP = creran_SNSP.getV1();
		BigInteger u0_SNSP = user.getU0();
		BigInteger u1_SNSP = user.getU1_SNSP();
		BigInteger A1_SNSP = creran_SNSP.getA1();
		
		
		BigInteger n_Bob  = key_Bob.getN();
		BigInteger R0_Bob = key_Bob.getR0();
		BigInteger R1_Bob  = key_Bob.getR1();
		BigInteger S_Bob  = key_Bob.getS();
		BigInteger Z_Bob  = key_Bob.getZ();
		BigInteger e_Bob = creran_Bob.getE();
		BigInteger v1_Bob = creran_Bob.getV1();
		BigInteger u0_Bob = user.getU0();
		BigInteger u1_Bob = user.getU1_Bob();
		BigInteger A1_Bob = creran_Bob.getA1();
		
		BigInteger a = par_zkp.getA();
		BigInteger rho = par_zkp.getRho();
		BigInteger rho1 = par_zkp.getRho1();
		BigInteger D = par_zkp.getD();
		BigInteger g = par_zkp.getG();
		BigInteger h = par_zkp.getH();
		BigInteger E = new BigInteger(policy.getE());
			
		boolean b1 = zkp.prove_SNSP(e_SNSP, v1_SNSP, u0_SNSP, u1_SNSP, Z_SNSP, R0_SNSP, R1_SNSP, A1_SNSP, S_SNSP, n_SNSP);
		boolean b2 = zkp.prove_Bob(e_Bob, v1_Bob, u0_Bob, u1_Bob, a, rho, rho1, Z_Bob, R0_Bob, R1_Bob, A1_Bob, S_Bob, D, g, h, E, n_Bob);
		result = String.valueOf(b1&&b2);
	}
	
	public void create_Response(){
		
		String request_id = request.getRequest_id();
		Build_ID bd_id = new Build_ID();
		String response_id = bd_id.Build_Response_ID();
		Response response = new Response(response_id,Method.getTime(),request_id, result);
		
		XML_Write xw = new XML_Write();
		try {
			xw.Build_Response(response);
			System.out.println("Response.xml文件已经生成。");			
		} catch (Exception e) {	
			System.out.println("Response.xml文件生成过程异常。");
		}
	}
	
	public static void main(String[] args) {
		
		long time1 = System.currentTimeMillis();
		
		ResponseGenerator ac = new ResponseGenerator();
		ac.create_Response();
		System.out.println(ac.result);
	
		long time2 = System.currentTimeMillis();
		System.out.println("该阶段耗时:"+(time2-time1)+"ms");
	}
}
