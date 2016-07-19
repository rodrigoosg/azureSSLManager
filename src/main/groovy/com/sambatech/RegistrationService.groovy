package com.sambatech

class RegistrationService {
	
	String executeSimpleCommand(String command, String[] params){
		def sout = new StringBuilder(), serr = new StringBuilder()
		
		def proc = "$command ${params[0]} ${params[1]}".execute()
		proc.consumeProcessOutput(sout, serr)
		proc.waitForOrKill(1000)
		
		println "out > $sout"
		println "err > $serr"
		
		return sout + serr
	}
	
}
