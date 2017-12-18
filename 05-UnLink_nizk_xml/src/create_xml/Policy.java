package create_xml;


public class Policy {
	
	private String policy_id;
	private String issuer;
	private String date;
	private String content_id;
	private String E;
	private String limit;
		
	public Policy(String issuer,String date,String policy_id,String content_id,String E,String limit){
		
		this.issuer = issuer;
		this.date = date;
		this.policy_id = policy_id;
		this.content_id = content_id;
		this.E = E;
		this.limit = limit;
	}
	
	public Policy(String policy_id,String content_id,String E,String limit){
		
		this.policy_id = policy_id;
		this.content_id = content_id;
		this.E = E;
		this.limit = limit;
	}
	
	public String getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(String policy_id) {
		this.policy_id = policy_id;
	}

	public String getIssuer() {
		return issuer;
	}
	
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getContent_id() {
		return content_id;
	}
	
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	
	public String getE() {
		return E;
	}
	
	public void setE(String e) {
		E = e;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
	
}
