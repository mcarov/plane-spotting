package ru.itpark.planespotting.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import ru.itpark.planespotting.dto.ResponseDto;
import ru.itpark.planespotting.exception.AuthTokenException;
import ru.itpark.planespotting.exception.FileUploadException;
import ru.itpark.planespotting.exception.ImageNotFoundException;
import ru.itpark.planespotting.exception.PhotoNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

@RestController
public class ErrorController extends AbstractErrorController {
    private ErrorAttributes errorAttributes;
    private MessageSource messageSource;

    public ErrorController(ErrorAttributes errorAttributes, MessageSource messageSource) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
        this.messageSource = messageSource;
    }

    @RequestMapping
    public ResponseEntity<ResponseDto> handleError(HttpServletRequest request) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Throwable error = errorAttributes.getError(webRequest);

        int code = getStatus(request).value();
        String reason = getStatus(request).getReasonPhrase();
        String message = messageSource.getMessage("api.error.default", null, LocaleContextHolder.getLocale());

        if(error instanceof PhotoNotFoundException) {
            code = 404;
            reason = "Not found";
            message = messageSource.getMessage("api.error.no-photo", null, LocaleContextHolder.getLocale());
        }
        else if(error instanceof UsernameNotFoundException | error instanceof BadCredentialsException) {
            code = 401;
            reason = "Unauthorized";
            message = messageSource.getMessage("api.error.login-pass", null, LocaleContextHolder.getLocale());
        }
        else if(error instanceof AuthTokenException) {
            code = 401;
            reason = "Unauthorized";
            message = messageSource.getMessage(error.getMessage(), null, LocaleContextHolder.getLocale());
        }
        else if(error instanceof ImageNotFoundException) {
            code = 404;
            reason = "Not found";
            message = messageSource.getMessage("api.error.no-image", null, LocaleContextHolder.getLocale());
        }
        else if(error instanceof FileUploadException) {
            code = 500;
            reason = "Internal server error";
            message = messageSource.getMessage("api.error.upload", null, LocaleContextHolder.getLocale());
        }

        return ResponseEntity.status(code).body(
                new ResponseDto(code, reason, message, ZonedDateTime.now()));
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
