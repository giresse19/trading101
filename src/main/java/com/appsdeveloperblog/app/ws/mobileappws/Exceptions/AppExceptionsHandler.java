package com.appsdeveloperblog.app.ws.mobileappws.Exceptions;

import com.appsdeveloperblog.app.ws.mobileappws.persistence.exception.MyEntityNotFoundException;
import com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public AppExceptionsHandler() {
        super();
    }

    // 400
    @Override
    protected final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                                        final HttpHeaders headers,
                                                                        final HttpStatus status,
                                                                        final WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                        final HttpHeaders headers, final HttpStatus status,
                                                                        final WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class, MyBadRequestException.class})
    protected final ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ApiError message(final HttpStatus httpStatus, final Exception ex) {

        final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        final Throwable devMessage = explicitDevErrorMsg(ex);
        ErrorMessage errorMessage = new ErrorMessage(new Date(), message, devMessage);

        return new ApiError(httpStatus.value(), errorMessage.getMessage(), errorMessage.getDeveloperMessage(), errorMessage.getTimestamp());
    }

    public static Throwable explicitDevErrorMsg(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    // 403
    @ExceptionHandler({JwtExceptions.class})
    public ResponseEntity<Object> handleJwtException(JwtExceptions exception, WebRequest request) {
        return new ResponseEntity<>(message(HttpStatus.INTERNAL_SERVER_ERROR, exception), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadCredentialException.class})
    public ResponseEntity<Object> handleBadCredException(BadCredentialException exception, WebRequest request) {
        return new ResponseEntity<>(message(HttpStatus.INTERNAL_SERVER_ERROR, exception), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MyForbiddenException.class})
    protected ResponseEntity<Object> handleForbidden(final MyForbiddenException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    // 404
    @ExceptionHandler({ MyEntityNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final MyEntityNotFoundException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<Object> handleLibraryNotFound(final EntityNotFoundException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 409
    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class, MyConflictException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 412
    @ExceptionHandler({ MyPreconditionFailedException.class })
    protected ResponseEntity<Object> handlePreconditionFailed(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }

    //
    @ExceptionHandler({UnrecognisedPropException.class})
    public ResponseEntity<Object> handleUserServiceException(UnrecognisedPropException exception, WebRequest request) {
        return new ResponseEntity<>(message(HttpStatus.BAD_REQUEST, exception), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // 500
    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
