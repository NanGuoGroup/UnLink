package issue_credential;

import java.math.BigInteger;

import create_xml.Credential;
import create_xml.Key;
import create_xml.UserPrime;
import create_xml.XML_Write;
import create_xml.XML_Read;
import initialization.Method;

public class Issue {
		
	CLSigner cl = new CLSigner();
	ZKP_sign zkp = new ZKP_sign();

	XML_Read xr = new XML_Read();
	XML_Write xw = new XML_Write();
	
	BigInteger[] key_para = xr.key_read();
	Key key_SNSP = xr.Key_SNSP(key_para);
	Key key_Bob = xr.Key_Bob(key_para);
	
	BigInteger u0_Alice  = zkp.createRandomU0();
	BigInteger u1_SNSP_Alice = cl.createRandomU();//SNSPѡȡһ����������Alice
	BigInteger u1_Bob_Alice = cl.createRandomU();//Bobѡȡһ������������Alice��
	
	public Credential Issue1(){
		
		BigInteger p_SNSP  = key_SNSP.getP();
		BigInteger n_SNSP  = key_SNSP.getN();
		BigInteger R0_SNSP = key_SNSP.getR0();
		BigInteger R1_SNSP  = key_SNSP.getR1();
		BigInteger S_SNSP  = key_SNSP.getS();
		BigInteger Z_SNSP  = key_SNSP.getZ();
		
		//ALice���Լ���˽Կ��һ����ŵ������SNSP��֪ʶ֤��
		BigInteger r1_Alice_SNSP = zkp.createR1(n_SNSP);		
		BigInteger C_Alice_SNSP = zkp.computeC(R0_SNSP, S_SNSP, u0_Alice, r1_Alice_SNSP, n_SNSP);		
		boolean result_1 = zkp.prove(C_Alice_SNSP,R0_SNSP, S_SNSP, u0_Alice, r1_Alice_SNSP, n_SNSP);
		
		//SNSPѡȡǩ��ʱ�õ��Ĳ���		
		BigInteger e_SNSP = cl.createRandomE();//����һ���������e
		BigInteger r2_SNSP = cl.createRandomV();//����ָ�����ȵ������v
				
		//SNSP�Դ���Alice������ǩ����
		BigInteger A_SNSP = cl.sign(p_SNSP,n_SNSP,e_SNSP,u1_SNSP_Alice,r2_SNSP,C_Alice_SNSP,R1_SNSP,S_SNSP,Z_SNSP);//��˽Կp������u1ǩ����
		BigInteger v_SNSP = r1_Alice_SNSP.add(r2_SNSP);
		
		//Alice��SNSP�䷢���Լ���ǩ��������֤��
		boolean result_2 = cl.verify(Z_SNSP,A_SNSP,R0_SNSP,R1_SNSP,S_SNSP,e_SNSP,u0_Alice,u1_SNSP_Alice,v_SNSP,n_SNSP);
				
		//����һ��ƾ֤
		Credential cdt_SNSP = new Credential("SNSP", Method.getTime(), e_SNSP, v_SNSP, A_SNSP);
		
		if(result_1&&result_2){
			System.out.println("SNSP��Alice�䷢һ��ƾ֤��");
			return cdt_SNSP;
		}else{
			System.out.println("SNSP�䷢ƾ֤�����쳣��");
			return null;
		}
		
	}
	
	public Credential Issue2(){
		
		BigInteger p_Bob  = key_Bob.getP();	
		BigInteger n_Bob  = key_Bob.getN();//�����û���˽Կp�������û��Ĺ�Կn��
		BigInteger R0_Bob = key_Bob.getR0();//��QRn�У�ѡȡ��������õ�R0��
		BigInteger R1_Bob  = key_Bob.getR1();//��QRn�У�ѡȡ��������õ�R1��
		BigInteger S_Bob  = key_Bob.getS();//��QRn�У�ѡȡ��������õ�S��
		BigInteger Z_Bob  = key_Bob.getZ();//��QRn�У�ѡȡ��������õ�Z��	
						
		//Bobѡȡ��Ӧ�Ĳ�����׼��ǩ����
		BigInteger r1_Alice_Bob = zkp.createR1(n_Bob);		
		BigInteger C_Alice_Bob = zkp.computeC(R0_Bob, S_Bob, u0_Alice, r1_Alice_Bob, n_Bob);
		
		boolean result_1 = zkp.prove(C_Alice_Bob,R0_Bob, S_Bob, u0_Alice, r1_Alice_Bob, n_Bob);
						
		BigInteger e_Bob = cl.createRandomE();//����һ���������e
		BigInteger r2_Bob = cl.createRandomV();//����ָ�����ȵ������v
					
		//Bob�Դ���Alice������ǩ����
		BigInteger A_Bob = cl.sign(p_Bob,n_Bob,e_Bob,u1_Bob_Alice,r2_Bob,C_Alice_Bob,R1_Bob,S_Bob,Z_Bob);//��˽Կp������u1ǩ����
		BigInteger v_Bob = r1_Alice_Bob.add(r2_Bob);
				
		//Alice��Bob�䷢���Լ���ǩ��������֤��
		boolean result_2 = cl.verify(Z_Bob,A_Bob,R0_Bob,R1_Bob,S_Bob,e_Bob,u0_Alice,u1_Bob_Alice,v_Bob,n_Bob);
				
		Credential cdt_Bob = new Credential("Bob", Method.getTime(), e_Bob, v_Bob, A_Bob);
		
		if(result_1&&result_2){
			System.out.println("Bob��Alice�䷢һ��ƾ֤��");
			return cdt_Bob;
		}else{
			System.out.println("Bob�䷢ƾ֤�����쳣��");
			return null;
		}
	}
	
	public void create_Parameters(){
		
		UserPrime up = new UserPrime(u0_Alice, u1_SNSP_Alice, u1_Bob_Alice);
		
		try {
			xw.Build_UserPrime(up);
		} catch (Exception e) {
			System.out.println("Parameters.xml�ļ����ɹ����쳣��");
		}
	}
	
	public void create_Credentials(Credential c1,Credential c2){
	
		try {
			xw.Build_Credential(c1, c2);
			System.out.println("Credentials.xml�ļ��Ѿ����ɡ�");
		} catch (Exception e) {
			System.out.println("Credentials.xml�ļ����ɹ����쳣��");
		}
	} 
	
	public static void main(String[] args) {
		
		long time1 = System.currentTimeMillis();
		
		Issue ic = new Issue();
		Credential c1 = ic.Issue1();
		Credential c2 = ic.Issue2();
		ic.create_Credentials(c1, c2);
		ic.create_Parameters();
		
		long time2 = System.currentTimeMillis();
		System.out.println("�ý׶κ�ʱ:"+(time2-time1)+"ms");
	}
}
