package ae.etisalat.watcher.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.FileLock;
import java.nio.channels.NonWritableChannelException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.json.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import ae.etisalat.watcher.exceptions.SystemNotFound;
import ae.etisalat.watcher.model.ApiData;
import ae.etisalat.watcher.model.SystemDetails;

@Service
public class FileManagmentServices {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	SystemConfigurationServices configurationServices;
	
	private String fileName;
	private String fileType;
	private String fileExt;
	private String fileDirectory;
	private ObjectMapper objectMapper;
	private Map<String, Object> configData = new HashMap<>();
	
	public FileManagmentServices() {

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getFileDirectory() {
		return fileDirectory;
	}

	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}

	public Map<String, Object> loadConfigurationFile(SystemDetails system) throws Exception {

		if (Objects.nonNull(system)) {
			setValuesOfFileServices(system);
			if (this.fileType.equals("yaml")) {
				objectMapper = new ObjectMapper(new YAMLFactory());
				try {
					File file = new File(this.fileDirectory + this.fileName + "." + this.fileExt);
					this.configData = (HashMap<String, Object>) objectMapper.readValue(file, Map.class);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new Exception("error occurred while reading the file"+ex);
				}
			} else if (this.fileType.equals("properties")) {
				try (InputStream input = new FileInputStream(this.fileDirectory + this.fileName + "." + this.fileExt)) {
					Properties prop = new Properties();
					prop.load(input);
					if (prop != null && !prop.isEmpty()) {
						for (final String name : prop.stringPropertyNames())
							this.configData.put(name, prop.getProperty(name));
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return this.configData;
	}
	
	public Map<String, Object> loadConfigurationFile() {

		if (!(StringUtils.isAllEmpty(this.fileName,this.fileExt,this.fileType,this.fileDirectory))) {
			if (this.fileType.equals("yaml")) {
				objectMapper = new ObjectMapper(new YAMLFactory());
				try {
					File file = new File(this.fileDirectory + this.fileName + "." + this.fileExt);
					this.configData = (HashMap<String, Object>) objectMapper.readValue(file, Map.class);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else if (this.fileType.equals("properties")) {
				try (InputStream input = new FileInputStream(this.fileDirectory + this.fileName + "." + this.fileExt)) {
					Properties prop = new Properties();
					prop.load(input);
					if (prop != null && !prop.isEmpty()) {
						for (final String name : prop.stringPropertyNames())
							this.configData.put(name, prop.getProperty(name));
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return this.configData;
	}
	
	public ApiData[] loadSystemUrls(String systemName) {

		if (!(StringUtils.isEmpty(systemName))) {
			SystemDetails details  = configurationServices.getSystemDetailsByName(systemName);
			if(Objects.nonNull(details)) {
				String urlCfgPath = details.getUrlsCfgFile();
				if(urlCfgPath != null ) {
					objectMapper = new ObjectMapper();
					try {
						File file = new File(details.getConfigDirectory() + urlCfgPath);
						ApiData urlData[]=   objectMapper.readValue(file, ApiData[].class);
						return urlData;
					} catch (JsonParseException e) {
						logger.error("Error in parsing the file {}",e);
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						logger.error("Error in mapping the file {}",e);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error("Error in reading the file {}",e);
					}
				}
			}else {
				throw new  SystemNotFound("No such system found in the configuration");
			}
		}
		return null;
	}

	public String saveNewConfigurationContent(String configurationContent) {

		if (StringUtils.isNoneEmpty(this.fileDirectory, this.fileName, this.fileExt, this.fileType)) {

			if (this.fileType.equals("yaml")) {
				objectMapper = new ObjectMapper(new YAMLFactory());
				Path file = Paths.get(this.fileDirectory + this.fileName + "." + this.fileExt);
				try (FileOutputStream fis = new FileOutputStream(file.toFile());
						FileLock lock = fis.getChannel().lock()) {
					// parse JSON
					JsonNode jsonNodeTree = objectMapper.readTree(configurationContent);
					String fileContent = new YAMLMapper().writeValueAsString(jsonNodeTree);
					fis.write(fileContent.getBytes());
				} catch (NonWritableChannelException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			} else if (this.fileType.equals("properties")) {
				Path file = Paths.get(this.fileDirectory + this.fileName + "." + this.fileExt);
				try (FileOutputStream fis = new FileOutputStream(file.toFile());
						FileLock lock = fis.getChannel().lock()) {
					Properties prop = new Properties();
					JSONObject jsonObject = new JSONObject(configurationContent);
					prop = Property.toProperties(jsonObject);
					// prop.store(fis, "configuration properties list");
					System.out.println("---" + prop.toString());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return "";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

//	public static void main(String[] args) {
//		FileManagmentServices fileManagmentServices = new FileManagmentServices();
//		fileManagmentServices.setFileDirectory("C:\\Users\\engmo\\yaml-confg\\");
//		fileManagmentServices.setFileExt("properties");
//		fileManagmentServices.setFileName("start-stop-service");
//		fileManagmentServices.setFileType("properties");
//		fileManagmentServices.saveNewConfigurationContent("{\"airCommandsRetryCount\":2,\"airCurrency\":\"aed\",\"appExecQueueSize\":2048,\"bivrostSms\":{\"password\":\"ersal$123\",\"timeout\":10000,\"url\":\"http://10.239.159.197:9088/xposure/api/sms\",\"username\":\"ersal\",\"sender\":\"etisalatinf\"},\"dpi\":{\"url\":\"http://tibcoprod.etisalat.corp.ae:9258/Middleware/DPIService?wsdl\",\"username\":\"in\",\"password\":\"in\",\"hours\":4,\"requestSys\":\"IN\"},\"queue\":{\"initialSize\":1000,\"waitingTime\":100},\"csDataCacheTimeout\":60000,\"ucSleepTime\":1000,\"diameterCommandsRetryCount\":2,\"inquiryQueryTimeout\":30,\"inquiryReturnSize\":30,\"prepaidServiceClasses\":[6,7,8,9,11,200,783],\"tossedServiceClasses\":[606,806,909,911,907,920],\"apps\":{\"default\":{\"allowedMsisdns\":null,\"allowedServiceClasses\":null,\"handleTethering\":false},\"tbv\":{\"diameterMoneyServiceId\":30014,\"exponent\":-9,\"hcOfferId\":213,\"initialFeeLendme\":0,\"initialFeeMain\":47619048,\"mainDedicatedAccount\":{\"7\":[1],\"6\":[251],\"8\":[251]},\"minVolume\":0,\"minimumMinutes\":15,\"minimumBalanceLendme\":-1,\"minimumBalanceMain\":0,\"moneyUC\":619403,\"offerId\":6194,\"refillProfileId\":\"tbcv\",\"runPamId\":6194,\"serviceIdentifier\":\"START_STOP_VIDEO\",\"timeUC\":619401,\"conflictingOffers\":[5709,5710]},\"tbd\":{\"initialFeeLendme\":20000000,\"initialFeeMain\":2,\"minimumMinutes\":15,\"minimumBalanceMain\":0,\"minimumBalanceLendme\":0,\"lendmeDa\":46,\"diameterVolumeServiceId\":30011,\"diameterMoneyServiceId\":30013,\"refillProfileId\":\"TBC\",\"refillExtData1\":\"TBC\",\"offerId\":6004,\"hcOfferId\":210,\"timeUC\":600401,\"moneyUC\":600402,\"minVolume\":200,\"allowedServiceClasses\":[6,7,8,9],\"conflictingOffers\":[5709,5710],\"startPromo\":{\"usageOfferId\":6014,\"black\":{\"begin\":201701111000,\"end\":201704101000,\"offerId\":914,\"refillExt1\":\"bpromo\"},\"white\":{\"begin\":201612100000,\"end\":201612100000,\"offerId\":915,\"refillExt1\":\"wpromo\"}}},\"super1\":{\"initialFeeLendme\":0,\"initialFeeMain\":47619048,\"minimumMinutes\":60,\"minimumBalanceMain\":0,\"minimumBalanceLendme\":-1,\"lendmeDa\":46,\"diameterVolumeServiceId\":30011,\"diameterMoneyServiceId\":30014,\"refillProfileId\":\"TBV\",\"offerId\":5709,\"hcOfferId\":213,\"timeUC\":600401,\"moneyUC\":600402,\"minVolume\":0,\"allowedServiceClasses\":[6,7,8,9,11],\"conflictingOffers\":[5709,6194,6004,5710],\"exponent\":-9,\"serviceIdentifier\":\"START_STOP_VIDEO\",\"externalParam4\":\"Super1Hour\",\"checkStartedOffer\":[5709,5710],\"dpiHours\":1},\"super3\":{\"initialFeeLendme\":0,\"initialFeeMain\":31746031,\"minimumMinutes\":180,\"minimumBalanceMain\":0,\"minimumBalanceLendme\":-1,\"lendmeDa\":46,\"diameterVolumeServiceId\":30011,\"diameterMoneyServiceId\":30014,\"refillProfileId\":\"TBCV\",\"offerId\":5710,\"hcOfferId\":213,\"timeUC\":600401,\"moneyUC\":600402,\"minVolume\":0,\"allowedServiceClasses\":[6,7,8,9,11],\"conflictingOffers\":[5709,5710],\"exponent\":-9,\"serviceIdentifier\":\"START_STOP_VIDEO\",\"externalParam4\":\"Super3Hour\",\"dpiHours\":3}}}");
//
//	}

	public Map<String, Object> getConfigData() {
		if (this.configData == null)
			return new HashMap<>();
		return configData;
	}

	public void setConfigData(Map<String, Object> configData) {
		this.configData = new HashMap<>(configData);
	}
	
	
	
	public void setValuesOfFileServices(SystemDetails system) {
		setFileName(system.getConfigFileName());
		setFileDirectory(system.getConfigDirectory());
		setFileExt(system.getConfigFileExt());
		setFileType(system.getConfigFileType());
	}

	
}
