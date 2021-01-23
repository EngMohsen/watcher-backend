package ae.etisalat.watcher.model;

import java.io.Serializable;

public class ParameterData  implements Serializable{
	
	private int id;
	private String name;
	private String description;
	private String displayName;
	private String arrayElementType;
	private String type;
	private Object value;
	private boolean editable;
	private Constrains constrains;
	private boolean requireRestart;
	private boolean instanceVariable;
	private String defaultValue;

	public ParameterData() {

	}

	public ParameterData(int id, String name, String description, String displayName,boolean editable,String arrayElementType, String type, Object value,
			Constrains constrains, boolean requireRestart, boolean instanceVariable, String defaultValue) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.displayName = displayName;
		this.editable= editable;
		this.arrayElementType= arrayElementType;
		this.type = type;
		this.value = value;
		this.constrains = constrains;
		this.requireRestart = requireRestart;
		this.instanceVariable = instanceVariable;
		this.defaultValue = defaultValue;
	}
	public ParameterData(ParameterData copyValues) {
		  this(copyValues.getId(),copyValues.getName(),copyValues.getDescription(),copyValues.getDisplayName(),copyValues.isEditable(),copyValues.getArrayElementType(),copyValues.getType(), copyValues.getValue(),
				copyValues.getConstrains(),copyValues.isRequireRestart(), copyValues.isInstanceVariable(), copyValues.getDefaultValue());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public boolean isRequireRestart() {
		return requireRestart;
	}

	public void setRequireRestart(boolean requireRestart) {
		this.requireRestart = requireRestart;
	}

	public boolean isInstanceVariable() {
		return instanceVariable;
	}

	public void setInstanceVariable(boolean instanceVariable) {
		this.instanceVariable = instanceVariable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}



	public Constrains getConstrains() {
		return constrains;
	}



	public void setConstrains(Constrains constrains) {
		this.constrains = constrains;
	}



	public String getArrayElementType() {
		return arrayElementType;
	}

	public void setArrayElementType(String arrayElementType) {
		this.arrayElementType = arrayElementType;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public String toString() {
		return "ParameterData [name=" + name + ", description=" + description
				+ ", displayName=" + displayName + ", arrayElementType=" + arrayElementType + ", type=" + type + "]";
	}

	
	
}
