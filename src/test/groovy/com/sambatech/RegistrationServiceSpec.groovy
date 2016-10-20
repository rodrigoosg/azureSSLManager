package com.sambatech;

import spock.lang.Ignore
import spock.lang.Specification

class RegistrationServiceSpec extends Specification {
	
	def setup() {
	}
	
	def "should list the contents of a directory"() {
		given:
		RegistrationService registrationService = new RegistrationService()
		List<String> params = ["src/test/resources/testDirectory/", ""]
		
		when:
		String result = registrationService.executeSimpleCommand("ls", params)
		
		then:
		result == "[registerDomainPoc.sh\n, ]"
	}
	
	def "should execute registerDomain script without parameters"() {
		given:
		RegistrationService registrationService = new RegistrationService()
		
		when:
		String result = registrationService.executeSimpleCommand("bash", ["src/test/resources/testDirectory/registerDomainPoc.sh", ""])
		
		then:
		result == "[Usage: registerDomain.sh www.example.com 25\n, ]"
	}
	
}
