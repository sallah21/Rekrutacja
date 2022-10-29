package com.example.Rekrutacja;

import netscape.javascript.JSObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
@RestController
public class RekrutacjaApplication {

	public static void main(String[] args) {

		SpringApplication.run(RekrutacjaApplication.class, args);

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

    public static Set<String> subString(String s){
        Set<String> substrings = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            String subStr="";
            for (int j = i; j < s.length(); j++) {
                subStr += s.charAt(j);
                substrings.add(subStr);
            }
        }
        return substrings;
    }

	public static Set<String> permutationFinder(String str) {
		Set<String> perm = new HashSet<String>();
		//Handling error scenarios
		if (str == null) {
			return null;
		} else if (str.length() == 0) {
			perm.add("");
			return perm;
		}
		char initial = str.charAt(0); // first character
		String rem = str.substring(1); // Full string without first character
		Set<String> words = permutationFinder(rem);
		for (String strNew : words) {
			for (int i = 0;i<=strNew.length();i++){
				perm.add(charInsert(strNew, initial, i));
			}
		}
		return perm;
	}

	public static String charInsert(String str, char c, int j) {
		String begin = str.substring(0, j);
		String end = str.substring(j);
		return begin + c + end;
	}

    public static Set<String> task(String signs, int maxLen, int minLen,int amount) throws Exception{
        int possiblePerm =  posiblePermutations(signs);
        System.out.println("Possible permutations "+ possiblePerm);
        if (possiblePerm < amount){throw new Exception("Amount of strings wanted is bigger than the possible amount of them");}
        Set<String> substrings = subString(signs);
        Set<String> permutations = new HashSet<>();
        Iterator<String> i = substrings.iterator();
        while (i.hasNext()) {
            String s = i.next();
            if (s.length()<minLen || s.length()>maxLen) {i.remove();}
            else{
                Set<String> gg = permutationFinder(s);
                System.out.println("gg "+gg);
                permutations.addAll(gg);
            }
        }
        List<String> part=new ArrayList<>(permutations);
        List<String> sub=part.subList(0, amount);
        permutations = new HashSet<>(sub);
        return permutations;
    }
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	@GetMapping("/")
	public String start(){
        return String.format("To start write in url word, maxlength, minlength amount of strings");
	}

    @PostMapping("/calculate")
    public Set<String> calculate(@RequestParam(name = "word", defaultValue = "") String word, @RequestParam(name = "maxLength")  int maxLen, @RequestParam(name = "minLength") int minLen,@RequestParam(name = "amount") int amount) throws Exception {
        if (maxLen < minLen){
            throw new Exception("Max length is smaller than min length");

        }
        Set<String> result = task(word,maxLen,minLen,amount);
        return result;
    }
}