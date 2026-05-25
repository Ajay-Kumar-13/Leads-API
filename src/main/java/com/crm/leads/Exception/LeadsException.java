package com.crm.leads.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponseException;

@Getter
public class LeadsException extends ErrorResponseException {

    private final String errorCode;
    private final String errorMessage;

    public LeadsException(HttpStatusCode status, String errorCode, String errorMessage) {
        super(status);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
