package ae.etisalat.watcher.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ae.etisalat.watcher.model.MetaData;
import ae.etisalat.watcher.model.MetaDataParamters;
import ae.etisalat.watcher.model.SystemDetails;
import ae.etisalat.watcher.services.MetaDataService;
import ae.etisalat.watcher.services.SystemConfigurationServices;

@CrossOrigin
@RestController
@RequestMapping(path = "/watcher")
public class WatcherPortal {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	SystemConfigurationServices systemService;
	
	@Autowired
	MetaDataService metaDataServie;

	@GetMapping(path = "/systems", produces = "application/json")
	public List<SystemDetails> loadSystemDetails() {
		return systemService.getSystemDetails();
	}

	@GetMapping(path = "/systemNames", produces = "application/json")
	public List<String> loadSystemnamess() {
		return systemService.getSystemsName();
	}

	@GetMapping(path = "/systems/{systemName}", produces = "application/json")
	public SystemDetails loadSystemDetails(@PathVariable String systemName) {
		return systemService.getSystemDetailsByName(systemName);
	}

	@GetMapping(path = "/systems/{systemName}/metadata", produces = "application/json")
	public MetaDataParamters loadMetaData(@PathVariable String systemName) throws Exception {
		return metaDataServie.loadMetaDataValues(systemName);
		
	}
	
	@PostMapping(path = "/systems/{systemName}/saveMetadata", produces  = "application/json")
	public void loadMetaData(@PathVariable String systemName,@RequestBody String newMetaData) throws Exception {
		logger.info("system name {}, requestBody {}",systemName,newMetaData);
		metaDataServie.saveMetaData(systemName,newMetaData);
	}

}
