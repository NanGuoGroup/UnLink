package initialization;

import java.math.BigInteger;

public class SetUp {
	//��Ϊ��������о����õ�1��2�����ｫ������Ϊ��̬����������ʹ�á�
	public static final BigInteger BI1 = new BigInteger("1"); 
	public static final BigInteger BI2 = new BigInteger("2"); 
	
	public static final int Ln = 1024; //ln����RSAģ���ĳ��ȣ�Ϊ1024-bit�ġ�
	public static final int Lu = 180;  //lu�����û������ĳ��ȣ��˴�����Ϊ324-bit��  
	public static final int Le = 182;  //le>=lu+2�����Դ˴�����Ϊ326-bit��
	public static final int Lr = 160; //Lr����ǩ��������ѡȡ��ȫ�����ĳ��ȣ�Ϊ160-bit��
	public static final int Lv = Ln+Lu+Lr; //Lv=1024+324+160=1508��Lv����ǩ��ʹv�ĳ��ȣ�Ϊ1508-bit��
	public static final int Lc= 80;   //Lc������֪ʶ֤����������ս�ĳ���Ϊ80-bit��
	public static final int Lz = 80;  //Lz����ͳ����֪ʶ֤�������а�ȫ�����ĵĳ��ȣ�Ϊ80-bit��
	
	public static final int Lu1 = Lu-Lc-Lz-5; //Lu1=180-80-80-5=15,Lu1��ʵ��ѡȡ�Ĵ����û������ĳ��ȣ�Ϊ160-bit��
	public static final int Lr1 = Ln; //Lr1����Alice���Լ�˽Կ����ŵʱѡȡ������ĳ��ȣ�Ϊ1024-bit��
	public static final int Lr2 = Lv; //Lr2����Bobǩ��������ѡȡv�ĳ��ȣ�Ϊ1508-bit��

	public static final int Lr3 = Ln+Lz; //Lr3=1024+80=1104,Lr3����Alice������Լ���ǩ��ʱ��ѡȡ������ĳ��ȣ�Ϊ1104-bit��
}
