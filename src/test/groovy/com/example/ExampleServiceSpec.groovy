package com.example;

import com.example.controllers.ExampleController;

import spock.lang.Specification;
import spock.lang.Ignore;

class ExampleServiceSpec extends Specification {

	ExampleController exampleController = new ExampleController()
	
	def setup() {
	}
	
	def "should append 'processed' word to the parameter"() {
		given:
		ExampleService exampleService = new ExampleService()
		
		when:
		String result = exampleService.consumeExampleService("Some parameter")
		
		then:
		result == "Some parameter processed at " + new Date()
	}
	
}
