package ae.etisalat.watcher.model;

import java.util.List;

public class ArrayObjectParameter extends ParameterData {

	private List<Types> childParameters;

	public ArrayObjectParameter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayObjectParameter(ParameterData copyValues) {
		super(copyValues);
		// TODO Auto-generated constructor stub
	}

	public List<Types> getChildParameters() {
		return childParameters;
	}

	public void setChildParameters(List<Types> childParameters) {
		this.childParameters = childParameters;
	}

}
