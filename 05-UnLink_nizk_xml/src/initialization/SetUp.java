package initialization;

import java.math.BigInteger;

public class SetUp {
	//因为计算过程中经常用到1和2，这里将其设置为静态常量，方便使用。
	public static final BigInteger BI1 = new BigInteger("1"); 
	public static final BigInteger BI2 = new BigInteger("2"); 
	
	public static final int Ln = 1024; //ln代表RSA模数的长度，为1024-bit的。
	public static final int Lu = 180;  //lu代表用户素数的长度，此处设置为324-bit。  
	public static final int Le = 182;  //le>=lu+2，所以此处设置为326-bit。
	public static final int Lr = 160; //Lr代表签名过程中选取安全参数的长度，为160-bit。
	public static final int Lv = Ln+Lu+Lr; //Lv=1024+324+160=1508，Lv代表签名使v的长度，为1508-bit。
	public static final int Lc= 80;   //Lc代表零知识证明过程中挑战的长度为80-bit。
	public static final int Lz = 80;  //Lz代表统计零知识证明过程中安全参数的的长度，为80-bit。
	
	public static final int Lu1 = Lu-Lc-Lz-5; //Lu1=180-80-80-5=15,Lu1是实际选取的代表用户素数的长度，为160-bit。
	public static final int Lr1 = Ln; //Lr1代表Alice对自己私钥作承诺时选取随机数的长度，为1024-bit。
	public static final int Lr2 = Lv; //Lr2代表Bob签名过程中选取v的长度，为1508-bit。

	public static final int Lr3 = Ln+Lz; //Lr3=1024+80=1104,Lr3代表Alice随机化自己的签名时，选取随机数的长度，为1104-bit。
}
