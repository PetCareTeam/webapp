package mk.ukim.finki.wp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.wp.model.PlaceResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NearByController {

	
	@RequestMapping(value = "/nearby", method = RequestMethod.GET)
	@ResponseBody
	public void test(	HttpServletRequest request, HttpServletResponse response){
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<String> response1 = rest.getForEntity(
				"https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.0013%2C21.41069&radius=500&types=pet_store&key=AIzaSyAPQbbyrT4UygVGDCY2aoxCcT0D2hhpb9M",
				 String.class);
		
		System.out.println("****---**** " + response1);
		System.out.println("****---**** " + response);
		
//		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.0013%2C21.41069&radius=500&types=pet_store&key=AIzaSyAPQbbyrT4UygVGDCY2aoxCcT0D2hhpb9M&callback=JSON_CALLBACK";
//
//	    RestTemplate tmpl = new RestTemplate();
//
//	    List<HttpMessageConverter<?>> converters = 
//	        new ArrayList<HttpMessageConverter<?>>();
//	    converters.add(new MappingJacksonHttpMessageConverter());
//	    tmpl.setMessageConverters(converters);
//
//	    PlaceResponse response = tmpl.getForObject(url, PlaceResponse.class);
//
//	    System.out.println(response);
//		
	}
}
