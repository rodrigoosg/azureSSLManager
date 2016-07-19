package com.sambatech.controllers;

import static spark.Spark.*;

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
        
        RegistrationService exampleService = new RegistrationService();
        
        get("/registration/index", (req, res) -> exampleService.executeSimpleCommand("ls", "src/test/resources/testDirectory/", ""));
        
    }
}
