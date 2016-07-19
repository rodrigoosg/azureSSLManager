package com.sambatech;

import com.sambatech.controllers.RegristrationController;
import com.sambatech.RegistrationService;

import spock.lang.Specification;
import spock.lang.Ignore;

class RegistrationServiceSpec extends Specification {
	
	def setup() {
	}
	
	def "should list the contents of a directory"() {
		given:
		RegistrationService registrationService = new RegistrationService()
		
		when:
		String result = registrationService.executeSimpleCommand("ls", "src/test/resources/testDirectory/", "")
		
		then:
		result == "registerDomainPoc.sh\n"
	}
	
	def "should execute registerDomain script without parameters"() {
		given:
		RegistrationService registrationService = new RegistrationService()
		
		when:
		String result = registrationService.executeSimpleCommand("bash", "src/test/resources/testDirectory/registerDomainPoc.sh", "")
		
		then:
		result == "Usage: registerDomain.sh www.example.com 25\n"
	}
	
	def "should execute registerDomain script"() {
		given:
		RegistrationService registrationService = new RegistrationService()
		
		when:
		String result = registrationService.executeSimpleCommand("bash", "src/test/resources/testDirectory/registerDomainPoc.sh", "loiatan@gmail.com")
		
		then:
		result == 
"""Domain(s) to be registered: -d example.com -d www.example.com.br -d letsencrypt.example.com.br
Downloading certbot-auto...
Generating certificate for -d example.com -d www.example.com.br -d letsencrypt.example.com.br...
Done!
src/test/resources/testDirectory/registerDomainPoc.sh: line 23: wget: command not found
chmod: cannot access './certbot-auto': No such file or directory
src/test/resources/testDirectory/registerDomainPoc.sh: line 27: ./certbot-auto: No such file or directory
"""
		
	}
}
