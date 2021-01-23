package ae.etisalat.watcher.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ae.etisalat.watcher.model.ArrayObjectParameter;
import ae.etisalat.watcher.model.MetaData;
import ae.etisalat.watcher.model.MetaDataParamters;
import ae.etisalat.watcher.model.ParameterData;
import ae.etisalat.watcher.model.SystemDetails;
import ae.etisalat.watcher.model.Types;

@Service
public class MetaDataService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FileManagmentServices fileManagmentServices;

	@Autowired
	SystemConfigurationServices configurationServices;

	



	public MetaDataService() {
		super();
	}

	public MetaDataParamters loadMetaDataValues(String systemName) throws Exception {

		if (Objects.nonNull(fileManagmentServices) && Objects.nonNull(configurationServices)) {

			SystemDetails system = configurationServices.getSystemDetailsByName(systemName);

			if (Objects.nonNull(system)) {

				Map<String, Object> configData = fileManagmentServices.loadConfigurationFile(system);

				System.out.println(configData);

				MetaData metaData = configurationServices.loadMetaDataForSystem(systemName);

				if (Objects.nonNull(configData) && Objects.nonNull(metaData)) {
					return fillMetaDataValues(metaData, configData);
				}

			}
		}
		return null;
	}

	private MetaDataParamters fillMetaDataValues(MetaData metaData, Map<String, Object> configData) {
		// TODO Auto-generated method stub
		MetaDataParamters paramData = new MetaDataParamters();
		List<ArrayObjectParameter> arrayObjectParams = new ArrayList<>();
		if (Objects.nonNull(metaData)) {
			List<ParameterData> parameters = metaData.getMainParameters();
			parameters.stream().forEach((data) -> {
				if (data.getType().equals("text") || data.getType().equals("number")) {
					paramData.addNewMainParameters(data);
				} else if (data.getType().equals("ArrayInt") || data.getType().equals("ArrayString")) {
					paramData.addNewArrayParameters(data);
				} else if (data.getType().equals("Array") || data.getType().equals("Object")) {
					ArrayObjectParameter arrayObjectParameter = new ArrayObjectParameter(data);
					List<Types> childTypes = new ArrayList<>();
					setChildTypes(data, metaData,childTypes);
					 if(!childTypes.isEmpty()) {
						 System.out.println(childTypes);
						 arrayObjectParameter.setChildParameters(childTypes);
					 }
					 arrayObjectParams.add(arrayObjectParameter);
				}
			});
			if(!arrayObjectParams.isEmpty()) {
				paramData.setArrayObjectParams(arrayObjectParams);
			}
		}
		return paramData;
	}

	private void setChildTypes(ParameterData data, MetaData metaData, List<Types> childTypes) {
		if (Objects.nonNull(data)) {
			if (StringUtils.isNotEmpty(data.getArrayElementType())) {
				String arrayElementName = data.getArrayElementType();
				Types type = metaData.getTypesByName(arrayElementName);
				if (Objects.nonNull(type)) {
					childTypes.add(type);
					type.getParameters().forEach((param) -> {
						if(StringUtils.isNotEmpty(param.getArrayElementType())) {
							setChildTypes(param, metaData,childTypes);
						}
					});
				}
			}
		}
	}

	public void saveMetaData(String systemName, String newMetaData) {
		if(!StringUtils.isAllEmpty(systemName,newMetaData)) {
			
			SystemDetails system = configurationServices.getSystemDetailsByName(systemName);

			if(Objects.nonNull(system)) {
				system.setConfigFileName(system.getConfigFileName()+1);
				fileManagmentServices.setValuesOfFileServices(system);
				fileManagmentServices.saveNewConfigurationContent(newMetaData);
			}
			
		}
	}
}
