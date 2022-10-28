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
	static int factorial(int n)
	{
		int fact = 1;
		for (int i = 2; i <= n; i++)
			fact = fact * i;
		return fact;
	}

	public static int posiblePermutations(String str){
		int len = str.length();
		final int MAX_CHAR = 26;
		int[] freq = new int[MAX_CHAR];
		for (int i = 0; i < len; i++)
			if (str.charAt(i) >= 'a')
				freq[str.charAt(i) - 'a']++;

		int fact = 1;
		for (int i = 0; i < MAX_CHAR; i++)
			fact = fact * factorial(freq[i]);

		return factorial(len) / fact;
	}
	public static String insert(String str, char a , int b){
		return str.substring(0,b) + a + str.substring(b);
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
				output.add(insert(str,first_let,i));
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