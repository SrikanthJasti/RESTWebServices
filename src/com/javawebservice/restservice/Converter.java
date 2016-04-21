package com.javawebservice.restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("ConversionService")
public class Converter {
	@GET
	@Path("/InchToFeet/{i}")
	@Produces(MediaType.TEXT_XML)
	public String inchToFeet(@PathParam("i")int i){
		
		int inch = i;
		double feet = 0;
		feet = (double)inch/12;
		System.out.println("Hi");
		return "<InchToFeetService>"
				+"<Inch>"+inch+"</Inch>"
				+"<Feet>"+feet+"</Feet>"
				+"</InchToFeetService>";
		
	}
	
	@GET
	@Path("/FeetToInch/{f}")
	@Produces(MediaType.TEXT_XML)
	public String feetToInch(@PathParam("f") int f){
		
		int inch = 0;
		int feet = f;
		inch = feet*12;
		
		return "<FeetToInchService>"
				+"<Feet>"+feet+"</Feet>"	
				+"<Inch>"+inch+"</Inch>"
				+"</FeetToInchService>";
	}
	
}
