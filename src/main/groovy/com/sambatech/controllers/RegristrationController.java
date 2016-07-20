package com.sambatech.controllers;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;

import java.util.ArrayList;
import java.util.List;

import com.sambatech.RegistrationService;

/**
 * This is the Spark`s main class and in our context it acts as a controller, routing the HTTP requests
 * to actions and functions that stays in lower layers. You can have more information about spark`s framework
 * at:
 * <br>
 * http://sparkjava.com/documentation.html
 * <br>
 * @author Rodrigo Guimaraes
 * 
 */
public class RegristrationController {
	
    public static void main(String[] args) {
    	// Sets HTTP port that spark server will listen to 
        port(8080);
        
        RegistrationService registrationService = new RegistrationService();
        
        List<String> params = new ArrayList<String>();
        
        params.add("src/test/resources/testDirectory/");
        params.add("");
        
        get("/registration/index", (req, res) -> registrationService.executeSimpleCommand("ls", params));
        
        post("/registration/certificate", (request, response) -> {
            return registrationService.registerNewDomainToCertificate(request, response);
        });
    }
}
