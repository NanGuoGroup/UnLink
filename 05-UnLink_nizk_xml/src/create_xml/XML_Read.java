package create_xml;

import java.io.File;
import java.math.BigInteger;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XML_Read {	
		
	public BigInteger[] key_read(){
		
		 BigInteger[] key_para = new BigInteger[12];
		 SAXReader reader = new SAXReader();
	        int index = 0;
	        try {            
	            Document document = reader.read(new File("./src/xml_file/Keys.xml"));
	            Element keys = document.getRootElement();
	            Iterator<?> it1 = keys.elementIterator();
	           
	            while (it1.hasNext()) {
	                Element key = (Element) it1.next();
	                Iterator<?> it2 = key.elementIterator();
	                while (it2.hasNext()) {
	                	Element e1 = (Element) it2.next();
	                    Iterator<?> it3 = e1.elementIterator();
	                    while(it3.hasNext()){            
	                    	Element e2 = (Element) it3.next();
	                        key_para[index] = new BigInteger(e2.getStringValue());
	                        index++;
	                    }                    
	                }
	            }
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	    return key_para;
	}
	
	public Key Key_SNSP(BigInteger[] key_para){
		
		BigInteger p = key_para[0];
		BigInteger n = key_para[1];
		BigInteger R0 = key_para[2];
		BigInteger R1 = key_para[3];
		BigInteger S = key_para[4];
		BigInteger Z = key_para[5];
		Key key_SNSP = new Key(p, n, R0, R1, S, Z);
		return key_SNSP;
	}
	
	public Key Key_Bob(BigInteger[] key_para){
		
		BigInteger p = key_para[6];
		BigInteger n = key_para[7];
		BigInteger R0 = key_para[8];
		BigInteger R1 = key_para[9];
		BigInteger S = key_para[10];
		BigInteger Z = key_para[11];
		Key key_Bob = new Key(p, n, R0, R1, S, Z);
		return key_Bob;
	}
	
	public BigInteger[] user_read(){
		
		 BigInteger[] user_para = new BigInteger[6];
		 SAXReader reader = new SAXReader();
	        int index = 0;
	        try {            
	            Document document = reader.read(new File("./src/xml_file/UserPrime.xml"));
	            Element para = document.getRootElement();
	            Iterator<?> it1 = para.elementIterator();
	           
	            while (it1.hasNext()) {
	                Element e1 = (Element) it1.next();	              
	                user_para[index] = new BigInteger(e1.getStringValue());
	                index++;	                                      
	            }
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	    return user_para;
	}
	
	
	public UserPrime User_Alice(BigInteger[] par_para){
			
		BigInteger u0 = par_para[0];
		BigInteger u1_SNSP = par_para[1];
		BigInteger u1_Bob = par_para[2];
					
		UserPrime user = new UserPrime(u0,u1_SNSP,u1_Bob);
		return user;
	}
	
	public BigInteger[] cre_read(){
		
		 BigInteger[] cre_para = new BigInteger[6];
		 SAXReader reader = new SAXReader();
	        int index = 0;
	        try {            
	            Document document = reader.read(new File("./src/xml_file/Credentials.xml"));
	            Element cres = document.getRootElement();
	            Iterator<?> it1 = cres.elementIterator();
	           
	            while (it1.hasNext()) {
	                Element key = (Element) it1.next();
	                Iterator<?> it2 = key.elementIterator();
	                while (it2.hasNext()) {
	                	Element e1 = (Element) it2.next();
	                    cre_para[index] = new BigInteger(e1.getStringValue());
	                    index++;                   
	                }
	            }
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	    return cre_para;
	}
	
	public Credential Cre_SNSP(BigInteger[] cre_para){
		
		BigInteger e = cre_para[0];
		BigInteger v = cre_para[1];
		BigInteger A = cre_para[2];
		Credential cre_SNSP = new Credential(e, v, A);
		return cre_SNSP;
	}
	
	public Credential Cre_Bob(BigInteger[] cre_para){
		
		BigInteger e = cre_para[3];
		BigInteger v = cre_para[4];
		BigInteger A = cre_para[5];
		Credential cre_Bob = new Credential(e, v, A);
		return cre_Bob;
	}
	
	public BigInteger[] creran_read(){
		
		 BigInteger[] cre_para = new BigInteger[6];
		 SAXReader reader = new SAXReader();
	        int index = 0;
	        try {            
	            Document document = reader.read(new File("./src/xml_file/CredentialsRandom.xml"));
	            Element cres = document.getRootElement();
	            Iterator<?> it1 = cres.elementIterator();
	           
	            while (it1.hasNext()) {
	                Element key = (Element) it1.next();
	                Iterator<?> it2 = key.elementIterator();
	                while (it2.hasNext()) {
	                	Element e1 = (Element) it2.next();
	                    cre_para[index] = new BigInteger(e1.getStringValue());
	                    index++;
	                                     
	                }
	            }
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	    return cre_para;
	}
	
	public CredentialRandom CreRan_SNSP(BigInteger[] cre_para){
		
		BigInteger e = cre_para[0];
		BigInteger v1 = cre_para[1];
		BigInteger A1 = cre_para[2];
		CredentialRandom creran_SNSP = new CredentialRandom(e, v1, A1);
		return creran_SNSP;
	}
	
	public CredentialRandom CreRan_Bob(BigInteger[] cre_para){
		
		BigInteger e = cre_para[3];
		BigInteger v1 = cre_para[4];
		BigInteger A1 = cre_para[5];
		CredentialRandom creran_Bob = new CredentialRandom(e, v1, A1);
		return creran_Bob;
	}
	
	public BigInteger[] para_read(){
		
		 BigInteger[] par_para = new BigInteger[6];
		 SAXReader reader = new SAXReader();
	        int index = 0;
	        try {            
	            Document document = reader.read(new File("./src/xml_file/Parameters.xml"));
	            Element para = document.getRootElement();
	            Iterator<?> it1 = para.elementIterator();
	           
	            while (it1.hasNext()) {
	                Element key = (Element) it1.next();
	                Iterator<?> it2 = key.elementIterator();
	                while (it2.hasNext()) {
	                	Element e1 = (Element) it2.next();
	                    par_para[index] = new BigInteger(e1.getStringValue());
	                    index++;	                                      
	                }
	            }
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	    return par_para;
	}
	
	
	public Parameter Par_ZKP(BigInteger[] par_para){
			
		BigInteger a = par_para[0];
		BigInteger rho = par_para[1];
		BigInteger rho1 = par_para[2];
		
		BigInteger D = par_para[3];
		BigInteger g = par_para[4];
		BigInteger h = par_para[5];
				
		Parameter par_zkp = new Parameter(a,rho,rho1,D,g,h);
		return par_zkp;
	}

	public String[] policy_read(){
	
		String[] policy_para = new String[4];
		SAXReader reader = new SAXReader();
		int index = 0;
		try {            
			Document document = reader.read(new File("./src/xml_file/Policy.xml"));
			Element para = document.getRootElement();
			Iterator<?> it1 = para.elementIterator();
          
			while (it1.hasNext()) {
               Element e1 = (Element) it1.next();                                                                
                policy_para[index] = e1.getStringValue();
                index++;                              
              
           }
       } catch (DocumentException e) {
           e.printStackTrace();
       }
       return policy_para;
	}
	
	public Policy getPolicy(String[] policy_para){
		
		String policy_id = policy_para[0];
		String content_id = policy_para[1];
		String E = policy_para[2];
		String limit = policy_para[3];
					
		Policy policy = new Policy(policy_id, content_id, E, limit);
		return policy;
	}
	
	public String[] request_read(){
		
		String[] request_para = new String[4];
		SAXReader reader = new SAXReader();
		int index = 0;
		try {            
			Document document = reader.read(new File("./src/xml_file/Request.xml"));
			Element para = document.getRootElement();
			Iterator<?> it1 = para.elementIterator();
          
			while (it1.hasNext()) {
               Element e1 = (Element) it1.next();                                                                
               request_para[index] = e1.getStringValue();
                index++;                              
              
           }
       } catch (DocumentException e) {
           e.printStackTrace();
       }
       return request_para;
	}
	
	public Request getRequest(String[] request_para){
		
		String request_id = request_para[0];
		String object = request_para[1];
		String content_id = request_para[2];
		String requirement = request_para[3];
					
		Request request = new Request(null,request_id,object, content_id, requirement);
		return request;
	}
}
