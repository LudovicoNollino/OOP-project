package it.univpm.TwitterHashtagAnalytics.controller;

import org.springframework.web.bind.annotation.RestController;

/*import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(value = "/tweets", method = RequestMethod.GET, produces = "applications/json")
public class AppRestController {

}
