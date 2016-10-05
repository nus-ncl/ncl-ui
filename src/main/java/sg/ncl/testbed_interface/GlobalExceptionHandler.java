package sg.ncl.testbed_interface;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sg.ncl.exceptions.TeamNotFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Te Ye
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    public String handleForbiddenException(HttpServletRequest request, Exception ex) {
        log.warn("ForbiddenException Occured : {} Exception : {}", request.getRequestURL(), ex);
        return "nopermission";
    }
}
