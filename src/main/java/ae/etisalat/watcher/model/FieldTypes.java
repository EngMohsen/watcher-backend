package ae.etisalat.watcher.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FieldTypes {
	TEXT("text"),NUMBER("number"),OBJECT("object"),ARRAY("array");

	
	private String value;  
	 
	FieldTypes(String value) {
		this.value=value;
	}

	
	@JsonValue
    public String getValue() { return this.value; }
}
