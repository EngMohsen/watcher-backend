package ae.etisalat.watcher.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
//@JsonIgnoreProperties({"urlsCfgFile","usersCfgFile","libPath"})
public class SystemDetails implements Comparable<SystemDetails> {

	private String name;
	private String abbreviation;
	private String description;
	private String appDirectory;
	private String configDirectory;
	private String configFileType;
	private String configFileName;
	private String configFileExt;
	private String urlsCfgFile;
	private String usersCfgFile;
	private String libPath;
	private String runAppUrl;
	private String restartAppUrl;
	private String gracefullShutDownUrl;
	private String metaData;
	private List<SystemDetails> instances;

	public SystemDetails() {

	}

	public SystemDetails(String name, String abbreviation, String description, String appDirectory,
			String configDirectory, String configFileType, String configFileName, String libPath, String runAppUrl,
			String restartAppUrl, String gracefullShutDownUrl) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
		this.description = description;
		this.appDirectory = appDirectory;
		this.configDirectory = configDirectory;
		this.configFileType = configFileType;
		this.configFileName = configFileName;
		this.libPath = libPath;
		this.runAppUrl = runAppUrl;
		this.restartAppUrl = restartAppUrl;
		this.gracefullShutDownUrl = gracefullShutDownUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAppDirectory() {
		return appDirectory;
	}

	public void setAppDirectory(String appDirectory) {
		this.appDirectory = appDirectory;
	}

	public String getConfigDirectory() {
		return configDirectory;
	}

	public void setConfigDirectory(String configDirectory) {
		this.configDirectory = configDirectory;
	}

	public String getConfigFileType() {
		return configFileType;
	}

	public void setConfigFileType(String configFileType) {
		this.configFileType = configFileType;
	}

	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	public String getLibPath() {
		return libPath;
	}

	public void setLibPath(String libPath) {
		this.libPath = libPath;
	}

	public String getRunAppUrl() {
		return runAppUrl;
	}

	public void setRunAppUrl(String runAppUrl) {
		this.runAppUrl = runAppUrl;
	}

	public String getRestartAppUrl() {
		return restartAppUrl;
	}

	public void setRestartAppUrl(String restartAppUrl) {
		this.restartAppUrl = restartAppUrl;
	}

	public String getGracefullShutDownUrl() {
		return gracefullShutDownUrl;
	}

	public void setGracefullShutDownUrl(String gracefullShutDownUrl) {
		this.gracefullShutDownUrl = gracefullShutDownUrl;
	}

	public List<SystemDetails> getInstances() {
		return instances;
	}

	public void setInstances(List<SystemDetails> instances) {
		this.instances = instances;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[name = %s , abbreviation = %s]", this.name, this.abbreviation);
	}

	public String getConfigFileExt() {
		return configFileExt;
	}

	public void setConfigFileExt(String configFileExt) {
		this.configFileExt = configFileExt;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemDetails other = (SystemDetails) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(SystemDetails o) {
		// TODO Auto-generated method stub
		return o.compareTo(this);
	}

	public String getUrlsCfgFile() {
		return urlsCfgFile;
	}

	public void setUrlsCfgFile(String urlsCfgFile) {
		this.urlsCfgFile = urlsCfgFile;
	}

	public String getUsersCfgFile() {
		return usersCfgFile;
	}

	public void setUsersCfgFile(String usersCfgFile) {
		this.usersCfgFile = usersCfgFile;
	}

}
