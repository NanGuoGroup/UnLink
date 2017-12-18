package create_xml;

import java.math.BigInteger;
import java.security.SecureRandom;


public class Build_ID {
	
	SecureRandom rd = new SecureRandom();
	
	public String Build_Content_ID(){
		BigInteger id = new BigInteger(200,rd);
		String content_id = "content"+"-"+id;
		return content_id;
	}
	
	public String Build_Policy_ID(){
		BigInteger id = new BigInteger(200,rd);
		String policy_id = "policy"+"-"+id;
		return policy_id;
	}
	
	public String Build_Request_ID(){
		BigInteger id = new BigInteger(200,rd);
		String request_id = "request"+"-"+id;
		return request_id;
	}
	
	public String Build_Response_ID(){
		BigInteger id = new BigInteger(200,rd);
		String response_id = "response"+"-"+id;
		return response_id;
	}
	
}
