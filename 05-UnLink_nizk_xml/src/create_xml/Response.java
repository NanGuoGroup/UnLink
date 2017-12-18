package create_xml;

public class Response {
	
	private String response_id;
	private String date;
	private String request_id;
	private String result;
	
	public Response(String id,String date,String request_id,String result){
		
		this.response_id = id;
		this.date = date;
		this.request_id = request_id;
		this.result = result;
	}
	
	public String getResponse_id() {
		return response_id;
	}

	public void setResponse_id(String response_id) {
		this.response_id = response_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
