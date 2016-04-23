package com.blackswan.elastic.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.blackswan.elastic.entity.HelloMessage;
import com.blackswan.elastic.mapping.routing.MappingRoutes;
import com.blackswan.elastic.mapping.routing.MappingTopics;
import com.blackswan.elastic.message.Greeting;


@Controller
public class GreetingController {
	@MessageMapping(MappingRoutes.GREETING_ROUTE)
	@SendTo(MappingTopics.TOPIC_GREETINGS)
	public Greeting sendGreeting(HelloMessage  message) throws Exception{
		 Thread.sleep(3000); // simulated delay
	        return new Greeting("Hello, " + message.getName() + "!");
	}
}
