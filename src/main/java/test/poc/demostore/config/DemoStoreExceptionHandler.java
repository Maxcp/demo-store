package test.poc.demostore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.poc.demostore.exception.DemoStoreException;
import test.poc.demostore.model.response.ErrorResponse;

@ControllerAdvice
public class DemoStoreExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoStoreExceptionHandler.class);
	
	@ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex) {
		LOGGER.info("Exception: " + ex.getLocalizedMessage() );
        ErrorResponse response = new ErrorResponse.ErrorResponseBuilder()
		        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
		        .withError_code("ERR_TRG_0001")
		        .withDescription("Internal Exception")
		        .build();
    
        return new ResponseEntity<>(response, response.getStatus());
    }
	
	@ExceptionHandler({DemoStoreException.class})
	protected ResponseEntity<Object> handlePatientDataNotReady(DemoStoreException ex) {
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}






