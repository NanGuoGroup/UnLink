package create_xml;

import java.io.FileOutputStream;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XML_Write {
	
    public void Build_Keys(Key k1,Key k2) throws IOException, JDOMException {     
        
        Element root = new Element("keys");         
        Document Doc = new Document(root);     
          
        Element e1 = new Element("key"); 
        e1.setAttribute("owner", k1.getOwner());
        e1.setAttribute("date", k1.getDate());
        Element e1_1 = new Element("sk");
        e1_1.addContent(new Element("p").setText(k1.getP().toString())); 
        Element e1_2 = new Element("pk");
        e1_2.addContent(new Element("n").setText(k1.getN().toString()));
        e1_2.addContent(new Element("R0").setText(k1.getR1().toString()));
        e1_2.addContent(new Element("R1").setText(k1.getR0().toString()));
        e1_2.addContent(new Element("S").setText(k1.getS().toString()));
        e1_2.addContent(new Element("Z").setText(k1.getZ().toString()));
        e1.addContent(e1_1);
        e1.addContent(e1_2);
        root.addContent(e1);   
           
        Element e2 = new Element("key"); 
        e2.setAttribute("owner", k2.getOwner());
        e2.setAttribute("date", k2.getDate());
        Element e2_1 = new Element("sk");
        e2_1.addContent(new Element("p").setText(k2.getP().toString())); 
        Element e2_2 = new Element("pk");
        e2_2.addContent(new Element("n").setText(k2.getN().toString()));
        e2_2.addContent(new Element("R0").setText(k2.getR1().toString()));
        e2_2.addContent(new Element("R1").setText(k2.getR0().toString()));
        e2_2.addContent(new Element("S").setText(k2.getS().toString()));
        e2_2.addContent(new Element("Z").setText(k2.getZ().toString()));
        e2.addContent(e2_1);
        e2.addContent(e2_2);
        root.addContent(e2); 
       
        Format format = Format.getPrettyFormat();  
        XMLOutputter XMLOut = new XMLOutputter(format);  
        XMLOut.output(Doc, new FileOutputStream("./src/xml_file/Keys.xml"));  
    } 
     
    public void Build_Credential(Credential c1,Credential c2) throws IOException, JDOMException {     
        
        Element root = new Element("Credentials");         
        Document Doc = new Document(root);     
          
        Element credential1 = new Element("Credential"); 
        credential1.setAttribute("issuer", c1.getIssuer());
        credential1.setAttribute("date", c1.getDate()); 
        credential1.addContent(new Element("e").setText(c1.getE().toString()));
        credential1.addContent(new Element("v").setText(c1.getV().toString()));     
        credential1.addContent(new Element("A").setText(c1.getA().toString()));
        root.addContent(credential1);   
           
        Element credential2 = new Element("Credential");  
        credential2.setAttribute("issuer", c2.getIssuer());
        credential2.setAttribute("date", c2.getDate()); 
        credential2.addContent(new Element("e").setText(c2.getE().toString()));
        credential2.addContent(new Element("v").setText(c2.getV().toString()));     
        credential2.addContent(new Element("A").setText(c2.getA().toString()));
        root.addContent(credential2);   
       
        Format format = Format.getPrettyFormat();  
        XMLOutputter XMLOut = new XMLOutputter(format);  
        XMLOut.output(Doc, new FileOutputStream("./src/xml_file/Credentials.xml"));  
    }   
    
    public void Build_CredentialRandom(CredentialRandom c1,CredentialRandom c2) throws IOException, JDOMException {     
        
        Element root = new Element("Credentials");         
        Document Doc = new Document(root);     
          
        Element credential1 = new Element("Credential"); 
        credential1.setAttribute("issuer", c1.getIssuer());
        credential1.setAttribute("date", c1.getDate()); 
        credential1.addContent(new Element("e").setText(c1.getE().toString()));
        credential1.addContent(new Element("v1").setText(c1.getV1().toString()));       
        credential1.addContent(new Element("A1").setText(c1.getA1().toString()));
        root.addContent(credential1);   
           
        Element credential2 = new Element("Credential");  
        credential2.setAttribute("issuer", c2.getIssuer());
        credential2.setAttribute("date", c2.getDate());
        credential2.addContent(new Element("e").setText(c2.getE().toString()));
        credential2.addContent(new Element("v1").setText(c2.getV1().toString()));       
        credential2.addContent(new Element("A1").setText(c2.getA1().toString()));
        root.addContent(credential2);   
       
        Format format = Format.getPrettyFormat();  
        XMLOutputter XMLOut = new XMLOutputter(format);  
        XMLOut.output(Doc, new FileOutputStream("./src/xml_file/CredentialsRandom.xml"));  
    } 
    
    public void Build_Policy(Policy p) throws IOException, JDOMException {     
            
        Element root = new Element("Policy");          
        Document Doc = new Document(root);     
                                
        root.setAttribute("issuer", p.getIssuer());
        root.setAttribute("date", p.getDate()); 
        root.addContent(new Element("policy_id").setText(p.getPolicy_id()));
        root.addContent(new Element("content_id").setText(p.getContent_id()));
        root.addContent(new Element("E").setText(p.getE()));
        root.addContent(new Element("limit").setText(p.getLimit()));
        
        Format format = Format.getPrettyFormat();  
        XMLOutputter XMLOut = new XMLOutputter(format);  
        XMLOut.output(Doc, new FileOutputStream("./src/xml_file/Policy.xml"));  
    } 
    
    public void Build_UserPrime(UserPrime up) throws IOException, JDOMException {     
        
        Element root = new Element("prime");          
        Document Doc = new Document(root);     
              
        root.addContent(new Element("u0").setText(up.getU0().toString())); 
        root.addContent(new Element("u1_SNSP").setText(up.getU1_SNSP().toString()));
        root.addContent(new Element("u1_Bob").setText(up.getU1_Bob().toString()));
          
        Format format = Format.getPrettyFormat();  
        XMLOutputter XMLOut = new XMLOutputter(format);  
        XMLOut.output(Doc, new FileOutputStream("./src/xml_file/UserPrime.xml"));  
    }
    
    public void Build_Parameter(Parameter p) throws IOException, JDOMException {     
            	
    	Element root = new Element("Parameters");       
        Document Doc = new Document(root); 
            
        Element Alice = new Element("Alice");
        Alice.addContent(new Element("a").setText(p.getA().toString())); 
        Alice.addContent(new Element("rho").setText(p.getRho().toString()));
        Alice.addContent(new Element("rho1").setText(p.getRho1().toString()));
        root.addContent(Alice);
        
        Element common = new Element("common");           
        common.addContent(new Element("D").setText(p.getD().toString()));
        common.addContent(new Element("g").setText(p.getG().toString()));
        common.addContent(new Element("h").setText(p.getH().toString()));
        root.addContent(common); 
          
        Format format = Format.getPrettyFormat();       
        XMLOutputter XMLOut = new XMLOutputter(format);  
        XMLOut.output(Doc, new FileOutputStream("./src/xml_file/Parameters.xml"));
    	 
    }
    
    public void Build_Request(Request r) throws IOException, JDOMException {     
        
        Element root = new Element("Request");       
        Document Doc = new Document(root);     
                      
        root.setAttribute("date", r.getDate()); 
        root.addContent(new Element("request_id").setText(r.getRequest_id()));
        root.addContent(new Element("object").setText(r.getObject()));
        root.addContent(new Element("content_id").setText(r.getContent_id()));
        root.addContent(new Element("requirement").setText(r.getRequirement()));
       
        Format format = Format.getPrettyFormat();  
        XMLOutputter XMLOut = new XMLOutputter(format);  
        XMLOut.output(Doc, new FileOutputStream("./src/xml_file/Request.xml"));  
    } 
    
    public void Build_Response(Response r) throws IOException, JDOMException {     
        
        Element root = new Element("Response");        
        Document Doc = new Document(root);     
                      
        root.setAttribute("date", r.getDate()); 
        root.addContent(new Element("response_id").setText(r.getResponse_id()));
        root.addContent(new Element("request_id").setText(r.getRequest_id()));
        root.addContent(new Element("result").setText(r.getResult()));
       
        Format format = Format.getPrettyFormat();  
        XMLOutputter XMLOut = new XMLOutputter(format);  
        XMLOut.output(Doc, new FileOutputStream("./src/xml_file/Response.xml"));  
    } 
    	
}
