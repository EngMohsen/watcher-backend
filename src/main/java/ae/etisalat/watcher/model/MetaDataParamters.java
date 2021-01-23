package ae.etisalat.watcher.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaDataParamters implements Serializable {


	private List<ParameterData> mainParams;
	private List<ParameterData> arrayPrimitiveParams;
	private List<ArrayObjectParameter> arrayObjectParams;

	public MetaDataParamters() {
		this.mainParams = new ArrayList<>();
		this.arrayPrimitiveParams = new ArrayList<>();
		this.arrayObjectParams =new ArrayList<>();
	}



	public MetaDataParamters(List<ParameterData> mainParams, List<ParameterData> arrayPrimitiveParams,
			List<ArrayObjectParameter> arrayObjectParams) {
		super();
		this.mainParams = mainParams;
		this.arrayPrimitiveParams = arrayPrimitiveParams;
		this.arrayObjectParams = arrayObjectParams;
	}



	public List<ParameterData> getMainParams() {
		if (mainParams == null && !mainParams.isEmpty()) {
			return new ArrayList<>();
		}
		return mainParams;
	}

	public void setMainParams(List<ParameterData> mainParams) {
		this.mainParams = mainParams;
	}

	public List<ParameterData> getArrayPrimitiveParams() {
		if (arrayPrimitiveParams == null && !arrayPrimitiveParams.isEmpty()) {
			return new ArrayList<>();
		}
		return arrayPrimitiveParams;
	}

	public void setArrayPrimitiveParams(List<ParameterData> arrayPrimitiveParams) {
		this.arrayPrimitiveParams = arrayPrimitiveParams;
	}

	

	public List<ArrayObjectParameter> getArrayObjectParams() {
		return arrayObjectParams;
	}



	public void setArrayObjectParams(List<ArrayObjectParameter> arrayObjectParams) {
		this.arrayObjectParams = arrayObjectParams;
	}



	public void addNewMainParameters(ParameterData data) {

		if (this.mainParams != null) {
			this.mainParams.add(data);
		} else {
			this.mainParams = new ArrayList<>();
			this.mainParams.add(data);
		}

	}

	public void addNewArrayParameters(ParameterData data) {

		if (this.arrayPrimitiveParams != null) {
			this.arrayPrimitiveParams.add(data);
		} else {
			this.arrayPrimitiveParams = new ArrayList<>();
			this.arrayPrimitiveParams.add(data);
		}

	}

}
