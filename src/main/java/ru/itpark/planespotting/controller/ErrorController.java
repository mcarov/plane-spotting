package ru.itpark.planespotting.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import ru.itpark.planespotting.dto.FieldDto;
import ru.itpark.planespotting.dto.ResponseDto;
import ru.itpark.planespotting.exception.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
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
        Locale locale = LocaleContextHolder.getLocale();

        int status = getStatus(request).value();
        String reason = getStatus(request).getReasonPhrase();
        String message = messageSource.getMessage("api.error.default", null, locale);
        List<FieldDto> fields = null;

        if(error instanceof PhotoNotFoundException) {
            status = HttpStatus.NOT_FOUND.value();
            reason = HttpStatus.NOT_FOUND.getReasonPhrase();
            message = messageSource.getMessage("api.error.no-photo", null, LocaleContextHolder.getLocale());
        }
        else if(error instanceof UsernameNotFoundException | error instanceof BadCredentialsException) {
            status = HttpStatus.UNAUTHORIZED.value();
            reason = HttpStatus.UNAUTHORIZED.getReasonPhrase();
            message = messageSource.getMessage("api.error.login-pass", null, locale);
        }
        else if(error instanceof AuthTokenException) {
            status = HttpStatus.UNAUTHORIZED.value();
            reason = HttpStatus.UNAUTHORIZED.getReasonPhrase();
            message = messageSource.getMessage(error.getMessage(), null, locale);
        }
        else if(error instanceof ImageNotFoundException) {
            status = HttpStatus.NOT_FOUND.value();
            reason = HttpStatus.NOT_FOUND.getReasonPhrase();
            message = messageSource.getMessage("api.error.no-image", null, locale);
        }
        else if(error instanceof FileUploadException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value();
            reason = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            message = messageSource.getMessage("api.error.upload", null, locale);
        }
        else if(error instanceof BindException) {
            BindingResult bindingResutlt =  ((BindException) error).getBindingResult();
            status = HttpStatus.BAD_REQUEST.value();
            reason = HttpStatus.BAD_REQUEST.getReasonPhrase();
            message = messageSource.getMessage("api.error.binding", null, locale);
            fields = bindingResutlt.getFieldErrors().stream()
                    .map(e -> new FieldDto(e.getField(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
        else if(error instanceof UsernameAlreadyExistsException) {
            status = HttpStatus.FORBIDDEN.value();
            reason = HttpStatus.FORBIDDEN.getReasonPhrase();
            message = String.join("",
                    messageSource.getMessage("api.error.username-exists", null, locale),
                    " (", error.getMessage(), ")");
        }
        else if(error instanceof TooManyRegisterAttemptsException) {
            status = HttpStatus.FORBIDDEN.value();
            reason = HttpStatus.FORBIDDEN.getReasonPhrase();
            message = messageSource.getMessage(error.getMessage(), null, locale);
        }
        else if(error instanceof DisabledException) {
            status = HttpStatus.UNAUTHORIZED.value();
            reason = HttpStatus.UNAUTHORIZED.getReasonPhrase();
            message = messageSource.getMessage(error.getMessage(), null, locale);
        }

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(status);
        responseDto.setReason(reason);
        responseDto.setMessage(message);
        responseDto.setTimestamp(ZonedDateTime.now());
        responseDto.setFields(fields);

        return ResponseEntity.status(status).body(responseDto);
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
