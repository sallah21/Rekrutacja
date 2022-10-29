package com.example.Rekrutacja;

import netscape.javascript.JSObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
	public static String ist(String str, char a , int b){
		System.out.print(str.substring(0,b) + a + str.substring(b));
		return str.substring(0,b) + a + str.substring(b);
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

	public static HashSet<String>  Permut( String signs){
		HashSet<String> output = new HashSet<String>();
		System.out.println("string in function "+signs);
		if (signs == null){
			System.out.print("Returning null");
			return null;
		}
		else if (signs.length()==1){
			output.add(signs);
			//System.out.print("Returning empty");
			return output;
		}
		char first_let = signs.charAt(0);
		String s = signs.substring(1);
		HashSet<String> w = Permut(s);
		for(String str:output){
			for(int i = 0;i<=str.length();i++){
				output.add(ist(str,first_let,i));
			}
		}
		System.out.print(output);
		return output;
	}

    public static Set<String> task(String signs, int maxLen, int minLen,int amount) throws Exception{
        int possiblePerm =  posiblePermutations(signs);
        System.out.println("Possible permutations "+ possiblePerm);
        if (possiblePerm < amount){
            throw new Exception("Amount of strings wanted is bigger than the possible amount of them");
        }
        Set<String> permutations = permutationFinder(signs);
       // Set<String> filteredPermutations = new HashSet<>();
        for (String s:permutations){
            System.out.println(s);
            if (s.length()<minLen || s.length()>maxLen){
                //filteredPermutations.add(s);
                permutations.remove(s);
            }
        }
        return permutations;
    }
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	@GetMapping("/")
	public String start(@RequestParam(value = "id",defaultValue = "abc") String id){

		return String.format("Start id %s",id);
	}

    @GetMapping("/calculate")
    public Set<String> calculate(@RequestParam(name = "word", defaultValue = "") String word, @RequestParam(name = "maxLength")  int maxLen, @RequestParam(name = "minLength") int minLen,@RequestParam(name = "amount") int amount) throws Exception {
        if (maxLen < minLen){
            throw new Exception("Max length is smaller than min length");

        }

        Set<String> result = task(word,maxLen,minLen,amount);
        return result;
    }
}