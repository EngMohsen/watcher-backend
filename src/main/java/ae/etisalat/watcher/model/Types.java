package ae.etisalat.watcher.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Types implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<ParameterData> parameters = new ArrayList<>();
	

	public Types() {
		// TODO Auto-generated constructor stub
	}


	public Types(String name, List<ParameterData> parameters) {
		super();
		this.name = name;
		this.parameters = parameters;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public List<ParameterData> getParameters() {
		return parameters;
	}


	public void setParameters(List<ParameterData> parameters) {
		this.parameters = parameters;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[name= %s , parameters=%s]", this.name,this.parameters);
	}



	

}
