package ae.etisalat.watcher.exceptions;

public class SystemNotFound extends RuntimeException {

	public SystemNotFound(String errorMessage) {
		super(errorMessage);
	}

	public SystemNotFound(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
