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
	
	def "should execute registerDomain script"() {
		given:
		RegistrationService registrationService = new RegistrationService()
		
		when:
		String result = registrationService.executeSimpleCommand("bash", ["src/test/resources/testDirectory/registerDomainPoc.sh", "loiatan@gmail.com"])
		
		then:
		result == 
"""[Domain(s) to be registered: -d example.com -d www.example.com.br -d letsencrypt.example.com.br
Downloading certbot-auto...
Setting executable permissions for certbot-auto...
Generating certificate for -d example.com -d www.example.com.br -d letsencrypt.example.com.br...
Converting certificate to Azure format...
Done!
, src/test/resources/testDirectory/registerDomainPoc.sh: line 23: sudo: command not found
src/test/resources/testDirectory/registerDomainPoc.sh: line 26: sudo: command not found
src/test/resources/testDirectory/registerDomainPoc.sh: line 30: sudo: command not found
src/test/resources/testDirectory/registerDomainPoc.sh: line 33: sudo: command not found
]"""
		
	}
	
	def "should parse a JSON object"() {
		given:
		RegistrationService registrationService = new RegistrationService()
		String requestBodyString = 
"""{
"newDomainName": "letsencrypt-5.agileoperations.com.br"
}"""
		
		when:
		def object = registrationService.parseRequestBody(requestBodyString)
		
		then:
		assert object instanceof Map
		object.newDomainName == 'letsencrypt-5.agileoperations.com.br'
	}
	
	@Ignore
	def "should execute registerDomain script and return proper response"() {
		given:
		def request = GroovyMock(RequestWrapper) {
			body() >> """{
"newDomainName": "letsencrypt-5.agileoperations.com.br"
}"""
        }

		RegistrationService registrationService = new RegistrationService()
		
		when:
		String result = registrationService.registerNewDomainToCertificate("bash", ["src/test/resources/testDirectory/registerDomainPoc.sh", "loiatan@gmail.com"])
		
		then:
		1 * request.body()
		
		result ==
"""Domain(s) to be registered: -d example.com -d www.example.com.br -d letsencrypt.example.com.br
Downloading certbot-auto...
"""

	}
	
}
