package ae.etisalat.watcher.model;

import java.util.List;
import java.util.Objects;

public class MetaData {

	private List<ParameterData> mainParameters;
	private List<Types> types;

	public MetaData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ParameterData> getMainParameters() {
		return mainParameters;
	}

	public void setMainParameters(List<ParameterData> mainParameters) {
		this.mainParameters = mainParameters;
	}

	public List<Types> getTypes() {
		return types;
	}

	public void setTypes(List<Types> types) {
		this.types = types;
	}

	public Types getTypesByName(String Name) {
		if (Objects.nonNull(types)) {
			for(Types type : types){
				if (type.getName().equals(Name)) {
					return type;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
