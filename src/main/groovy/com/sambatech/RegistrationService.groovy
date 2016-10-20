package com.sambatech

import groovy.json.JsonSlurper

class RegistrationService {
	
	def request
	
	public RegistrationService(){
	}
	
	String registerNewDomainToCertificate(request, response){
		
		def requestBodyObject = parseRequestBody(request.body().toString())
		
		def params = ['src/test/resources/testDirectory/registerDomainPoc.sh', 'loiatan@gmail.com']
		
		assert params instanceof List<String>
		
		def registrationResult = executeSimpleCommand("bash", params)
		
		if(registrationResult[1]){
			response.status(500)
			println "Could not generate certificate:"
			println "Error: registrationResult[1]"
			return registrationResult[1]
		} else {
			response.status(200)
			// Install certificate at Azure service
			// DNSService.uploadCertificate()
			return registrationResult[0]
		}
	}
	
	List<String> executeSimpleCommand(String command, List<String> params){
		def sout = new StringBuilder(), serr = new StringBuilder()
		
		println params
		
		def proc = "$command ${params[0]} ${params[1]}".execute()
		proc.consumeProcessOutput(sout, serr)
		proc.waitForOrKill(1000)
		
		return [sout, serr]
	}
	
	def parseRequestBody(requestBody){
		
		def jsonSlurper = new JsonSlurper()
		def object = jsonSlurper.parseText(requestBody.toString())
		
		return object
	}
	
}
