package spring.grpc.service.rest;

import javax.annotation.Nonnull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.grpc.entry.Entry;
import spring.grpc.exception.CustomException;
import spring.grpc.manager.Manager;
import spring.grpc.response.Response;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
@Controller
@RequestMapping(value = "/test")
public class RestService {

    private static final Logger LOGGER = LogManager.getLogger(RestService.class);

    private Manager manager;

    @Autowired
    public RestService(@Qualifier(value = "managerImpl") Manager manager) {
        this.manager = manager;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> test(@RequestBody @Nonnull Entry entry) throws Exception {
        try {
            manager.test(entry);
            return new ResponseEntity<>(Response.builder().entry(entry).status(Boolean.TRUE).build(), HttpStatus.OK);
        } catch (CustomException ex) {
            LOGGER.error("Failed, entry: {} :: message: {}", entry, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            LOGGER.error("Failed, entry: {}", entry, ex);
            throw ex;
        }
    }

}
