package ae.etisalat.watcher.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ae.etisalat.watcher.app.AppConstant;
import ae.etisalat.watcher.model.MetaData;
import ae.etisalat.watcher.model.SystemDetails;

@Component
public class SystemConfigurationServices implements CommandLineRunner{

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private ResourceLoader resourceLoader;

	private ObjectMapper objectMapper = new ObjectMapper();

	private List<SystemDetails> systemDetails = null;

	private Map<String, MetaData> metaDatas = null;

	public SystemConfigurationServices() {
		System.out.println("loading while initializing the class");
		this.systemDetails = new ArrayList<>();
		this.metaDatas = new HashMap<>();
//		this.metaData = new HashMap<>();
	}

	public void loadSystemConfiguration() throws IOException {

		String configContent = loadConfigFileContent(AppConstant.APP_CONFIG);
		if (StringUtils.hasLength(configContent) && !configContent.isEmpty()) {
			systemDetails = objectMapper.readValue(configContent, new TypeReference<List<SystemDetails>>() {});
			if (Objects.nonNull(systemDetails)) {
				systemDetails.stream().filter(Objects::nonNull).forEach((system) -> {
					try {
						if (Objects.nonNull(system.getMetaData())) {
							String metaDataConfig = loadConfigFileContent(
									AppConstant.META_DATA_PATH + "/" + system.getMetaData());
							MetaData metaData = objectMapper.readValue(metaDataConfig,
									MetaData.class);
							if (metaData != null) {
								metaDatas.put(system.getName(), metaData);
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});

				System.out.println("metaData - " + metaDatas);
			} else {
				LOGGER.error("Can't parse the json to the object");
				throw new IOException("Can't parse the json to the object");
			}
		}

	}

	private String loadConfigFileContent(String filePath) throws IOException {
		String result = "";
		try (InputStream inputStream = resourceLoader.getClassLoader().getResourceAsStream(filePath)) {
			result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			LOGGER.error("Error in loading the configuration file");
			throw new IOException("Error in reading the configuration file");
		}
		return result;
	}

	public List<SystemDetails> getSystemDetails() {
		return systemDetails;
	}

	public void setSystemDetails(List<SystemDetails> systemDetails) {
		this.systemDetails = systemDetails;
	}

	public List<String> getSystemsName() {
		List<String> systemNames = new ArrayList<String>();
		if (!systemDetails.isEmpty()) {
			systemDetails.forEach(details -> systemNames.add(details.getName()));
		}
		return systemNames;
	}

	public SystemDetails getSystemDetailsByName(String systemName) {

		for (SystemDetails systemDet : systemDetails) {
			if (systemDet.getName().equalsIgnoreCase(systemName))
				return systemDet;
		}
		return null;
	}
	
//	public List<ConfigurationMetaData> retrieveMetaData(String systemName) {
//		return metaData.getOrDefault(systemName, null);
//	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		loadSystemConfiguration();
		
	}

	public Map<String, MetaData> getMetaDatas() {
		return metaDatas;
	}

	public void setMetaDatas(Map<String, MetaData> metaDatas) {
		this.metaDatas = metaDatas;
	}
	
	public MetaData loadMetaDataForSystem(String systemName) {
		return metaDatas.get(systemName);
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
