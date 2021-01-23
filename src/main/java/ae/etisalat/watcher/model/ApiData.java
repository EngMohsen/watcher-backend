package ae.etisalat.watcher.model;

import java.io.Serializable;
import java.util.Map;


/*
 *  "name": "FDA",
"description": "first delivery attempt for sending sms",
"url": "http://10.37.188.18:7070/processRequest",
"method": "GET",
"timeOutInterval": 5000,
"RequestParams": [
  "msisdn",
  "fdaOnly"
],
"headerParams": {
  "content-type": "json"
},
"userName": "admin",
"password": "admin",
"clientType": "REST"
 * */
public class ApiData implements Serializable{
	
	
	private String name;
	private String description;
	private String url;
	private String methodType;
	private String requestFormat;
	private Long timeOutInterval;
	private Map<String,Object> requestParams;
	private Map<String,Object> requestHeaders;
	private String userName;
	private String password;
	private String clientType;
	
	
	
	public ApiData() {
		
	}

	public ApiData(String name, String description, String url, String methodType, Long timeOutInterval,
			 String userName, String password) {
		this.name = name;
		this.description = description;
		this.url = url;
		this.methodType = methodType;
		this.timeOutInterval = timeOutInterval;
		this.userName = userName;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public Long getTimeOutInterval() {
		return timeOutInterval;
	}

	public void setTimeOutInterval(Long timeOutInterval) {
		this.timeOutInterval = timeOutInterval;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getRequestFormat() {
		return requestFormat;
	}

	public void setRequestFormat(String requestFormat) {
		this.requestFormat = requestFormat;
	}

	public Map<String, Object> getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(Map<String, Object> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public Map<String, Object> getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(Map<String, Object> requestParams) {
		this.requestParams = requestParams;
	}
	
	
	
}
