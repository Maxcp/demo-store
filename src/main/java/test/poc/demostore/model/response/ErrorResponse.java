package test.poc.demostore.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "error_code",
        "description"
})
public class ErrorResponse {

    private HttpStatus status;
    private String error_code;
    private String description;


    @JsonProperty("status")
    public HttpStatus getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @JsonProperty("error_code")
    public String getErrorCode() {
        return error_code;
    }

    @JsonProperty("error_code")
    public void setErrorCode(String errorCode) {
        this.error_code = errorCode;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    //Builder
    public static final class ErrorResponseBuilder {
        private HttpStatus status;
        private String error_code;
        private String description;

        public ErrorResponseBuilder() {
        }

        public static ErrorResponseBuilder anErrorResponse() {
            return new ErrorResponseBuilder();
        }

        public ErrorResponseBuilder withStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ErrorResponseBuilder withError_code(String error_code) {
            this.error_code = error_code;
            return this;
        }

        public ErrorResponseBuilder withDescription(String description) {
            this.description = description;
            return this;
        }


        public ErrorResponse build() {
            ErrorResponse apiErrorResponse = new ErrorResponse();
            apiErrorResponse.status = this.status;
            apiErrorResponse.error_code = this.error_code;
            apiErrorResponse.description = this.description;

            return apiErrorResponse;
        }
    }
}
