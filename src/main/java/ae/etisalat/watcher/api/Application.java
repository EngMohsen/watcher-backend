package ae.etisalat.watcher.api;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ae.etisalat.watcher.model.ApiData;
import ae.etisalat.watcher.services.FileManagmentServices;

@CrossOrigin
@RestController
public class Application {

	@Autowired
	FileManagmentServices fileManagementServices;

//	@Autowired
//	SystemRepository systemRepository;

	@GetMapping(path = "/apps/urls", produces = "application/json")
	public ResponseEntity<ApiData[]> loadSystemConfiguration(@RequestParam String systemName) {

		return new ResponseEntity<ApiData[]>(fileManagementServices.loadSystemUrls(systemName),
				HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/apps", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Map<String, Object>> saveApps(@RequestParam String fileName, @RequestParam String fileType,
			@RequestParam String fileDir, @RequestParam String fileExt, @RequestBody String request) {

		System.out.println("Data need to retrieve configuration data" + fileName + fileType + fileDir + fileExt);
		fileManagementServices.setFileDirectory(fileDir);
		fileManagementServices.setFileExt(fileExt);
		fileManagementServices.setFileName(fileName);
		fileManagementServices.setFileType(fileType);
		return new ResponseEntity<Map<String, Object>>(fileManagementServices.loadConfigurationFile(),
				HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/appsUpdating")
	public ResponseEntity<Void> getData() throws Exception {

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand("test").toUri();
//		SystemEntity employee = systemRepository.findById(1L)
//				.orElseThrow(() -> new Exception("Employee not found for this id :: " + 1L));

		return ResponseEntity.created(uri).build();

	}

}
