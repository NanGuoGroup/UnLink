package access_control;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;

import create_xml.*;
import initialization.Method;
import issue_credential.CLSigner;

public class PolicyGenerator {
	
	XML_Read xr = new XML_Read();	
	BigInteger[] user_para = xr.user_read();
	UserPrime user = xr.User_Alice(user_para);

	public BigInteger create_E(BigInteger u1){
		
		CLSigner cLSigner = new CLSigner();
		BigInteger u2 = cLSigner.createRandomU();//用户u2，即生成一个随机的素数代表u2。
		BigInteger u3 = cLSigner.createRandomU();//用户u3，即生成一个随机的素数代表u3。
		BigInteger u4 = cLSigner.createRandomU();//用户u4，即生成一个随机的素数代表u4。
		BigInteger u5 = cLSigner.createRandomU();//用户u5，即生成一个随机的素数代表u5。
		BigInteger u6 = cLSigner.createRandomU();//用户u6，即生成一个随机的素数代表u6。
		BigInteger u7 = cLSigner.createRandomU();//用户u7，即生成一个随机的素数代表u7。
		BigInteger u8 = cLSigner.createRandomU();//用户u8，即生成一个随机的素数代表u8。
		BigInteger u9 = cLSigner.createRandomU();//用户u9，即生成一个随机的素数代表u9。
//		BigInteger u10 = cLSigner.createRandomU();
//		BigInteger u11 = cLSigner.createRandomU();
//		BigInteger u12 = cLSigner.createRandomU();
//		BigInteger u13 = cLSigner.createRandomU();
//		BigInteger u14 = cLSigner.createRandomU();
//		BigInteger u15 = cLSigner.createRandomU();
//		BigInteger u16 = cLSigner.createRandomU();
//		BigInteger u17 = cLSigner.createRandomU();
//		BigInteger u18 = cLSigner.createRandomU();
//		BigInteger u19 = cLSigner.createRandomU();
//		BigInteger u20 = cLSigner.createRandomU();
//		BigInteger u21 = cLSigner.createRandomU();
//		BigInteger u22 = cLSigner.createRandomU();
//		BigInteger u23 = cLSigner.createRandomU();
//		BigInteger u24 = cLSigner.createRandomU();
//		BigInteger u25 = cLSigner.createRandomU();
//		BigInteger u26 = cLSigner.createRandomU();
//		BigInteger u27 = cLSigner.createRandomU();
//		BigInteger u28 = cLSigner.createRandomU();
//		BigInteger u29 = cLSigner.createRandomU();
//		BigInteger u30 = cLSigner.createRandomU();
//		BigInteger u31 = cLSigner.createRandomU();
//		BigInteger u32 = cLSigner.createRandomU();
//		BigInteger u33 = cLSigner.createRandomU();
//		BigInteger u34 = cLSigner.createRandomU();
//		BigInteger u35 = cLSigner.createRandomU();
//		BigInteger u36 = cLSigner.createRandomU();
//		BigInteger u37 = cLSigner.createRandomU();
//		BigInteger u38 = cLSigner.createRandomU();
//		BigInteger u39 = cLSigner.createRandomU();
//		BigInteger u40 = cLSigner.createRandomU();
//      ,u10,u11,u12,u13,u14,u15,u16,u17,u18,u19,u20,u21,u22,u23,u24,u25,u26,u27,u28,u29,u30,u31,u32,u33,u34,u35,u36,u37,u38,u39,u40
		BigInteger E = compute_E(u1,u2,u3,u4,u5,u6,u7,u8,u9);
		return E;
	}
	
	public BigInteger compute_E(BigInteger...U){
		HashSet<BigInteger> set = new HashSet<>();
		int len = U.length;
		for(int i=0;i<len;i++){
			set.add(U[i]);
		}
		Iterator<BigInteger> it =  set.iterator();
		BigInteger E = new BigInteger("1");
		while(it.hasNext()){
			BigInteger b = it.next();
			E = E.multiply(b);
		}
		return E;
	}
	
	public Policy compute_Policy(){
		
		Build_ID bd_id = new Build_ID();
		BigInteger u1 = user.getU1_Bob();
		BigInteger E = create_E(u1);
		String content_id = bd_id.Build_Content_ID();
		String policy_id = bd_id.Build_Policy_ID();
		Policy policy = new Policy("Bob", Method.getTime(),policy_id, content_id, E.toString(),"read");
		return policy;
	}
				
	public void create_Policy(Policy policy){
	
		XML_Write xw = new XML_Write();
		try {
			xw.Build_Policy(policy);
			System.out.println("Policy.xml文件已经生成。");
		} catch (Exception e) {
			System.out.println("Policy.xml文件生成过程异常。");
		}
	}
		
	public static void main(String[] args) {
		
		long time1 = System.currentTimeMillis();
		
		PolicyGenerator policygen = new PolicyGenerator();
		Policy policy = policygen.compute_Policy();
		policygen.create_Policy(policy);
		
		long time2 = System.currentTimeMillis();
		System.out.println("该阶段耗时:"+(time2-time1)+"ms");
	}
	
}
