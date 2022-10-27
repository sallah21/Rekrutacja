package com.example.Rekrutacja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashSet;
@SpringBootApplication
@RestController
public class RekrutacjaApplication {

	public static void main(String[] args) {

		SpringApplication.run(RekrutacjaApplication.class, args);

	}
	public static void task(String signs, int length){
		int possiblePerm,maxLength,minLength,userLength;

	}
	public static HashSet<String>  Permut( String signs){
		HashSet<String> output = new HashSet<String>();
		if (signs == null){
			return null;
		}
		else if (signs.length()==0){
			output.add("");
			return output;
		}
		char first_let = signs.charAt(0);
		String s = signs.substring(1);
		HashSet<String> w = Permut(s);
		for(String str:output){
			for(int i = 0;i<=str.length();i++){
				output.add(charInsert(str,first_let,i));
			}
		}
		return output;
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	@GetMapping("/")
	public String start(@RequestParam(value = "id",defaultValue = "1") String id){
		return String.format("Start id %s",id);
	}


}