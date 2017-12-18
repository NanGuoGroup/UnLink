package create_xml;

public class Request {
		
	private String date;
	private String request_id;
	private String object;
	private String content_id;
	private String requirement;
	
	public Request(String date,String request_id,String object,String content_id,String requirement){
		
		this.request_id = request_id;
		this.date = date;
		this.object = object;
		this.content_id = content_id;
		this.requirement = requirement;
	}
	
	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getObject() {
		return object;
	}
	
	public void setObject(String object) {
		this.object = object;
	}
	
	public String getContent_id() {
		return content_id;
	}
	
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	
	public String getRequirement() {
		return requirement;
	}
	
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	
}
