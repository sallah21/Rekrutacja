package com.example.Rekrutacja.service;

import com.example.Rekrutacja.entity.result;
import com.example.Rekrutacja.repository.resultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class CalculateService {




    public Set<result> saveResult(Set<String> s )
    {
        UUID g = UUID.randomUUID();
        Set<result> results = new HashSet<>();
        for(String i:s){
            final result r = new result();
            r.setValue(i);
            r.setGeneration(g);
           // System.out.println(" R " + r);

            results.add(r);
        }
        return results;
    }


    static int factorial(int n)
    {
        int fact = 1;
        for (int i = 2; i <= n; i++)
            fact = fact * i;
        return fact;
    }

    public static  long  posiblePermutations(String str){
        int len = str.length();
        System.out.println("Considered string in posible permutations " + str);
        final int MAX_CHAR = 50;
        double[] freq = new double[MAX_CHAR];
        for (int i = 0; i < len; i++)
            if (str.charAt(i) >= 'a')
                freq[str.charAt(i) - 'a']++;

        int fact = 1;
        for (int i = 0; i < MAX_CHAR; i++) {
                try {
                    fact = fact * factorial((int) freq[i]);
                }
                catch (ArithmeticException e){
                    System.out.println("Value exceeds range of long: " + fact);
                    return Long.MAX_VALUE/Math.abs(fact);
                }
                }
        if (factorial(len) <=0){
            System.out.println("Result of posssible perm " +factorial(len) / fact );
            return Long.MAX_VALUE/Math.abs(fact);
        }
        return   factorial(len) / fact;

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
    @Async
    public  CompletableFuture<Set<String>> task(String signs, int maxLen, int minLen, int amount) throws Exception{
        long possiblePerm =  posiblePermutations(signs);

        System.out.println("Possible permutations "+ possiblePerm);

        if (possiblePerm < amount){throw new Exception("Amount of strings wanted is bigger than the possible amount of them");}
        Set<String> substrings = subString(signs);
        Set<String> permutations = new HashSet<>();
        System.out.println("substrings " + substrings);
        System.out.println("sybstrings size " + substrings.size());
        Iterator<String> i = substrings.iterator();
        while (i.hasNext()) {
            String s = i.next();
            if (s.length()<minLen || s.length()>maxLen) {i.remove();}
            else{
                Set<String> gg = permutationFinder(s);
                permutations.addAll(gg);
               // System.out.println("Size of permutations "+ permutations.size());
                if (permutations.size() >= amount){
                    break;
                }
            }
        }

        substrings.clear();
       // System.out.println("Permutations size "+ permutations.size());
        if(amount > permutations.size()){
            throw new Exception("Amount of wanted strings is bigger than number of filtered strings");
        }
        List<String> part=new ArrayList<>(permutations);
        List<String> sub=part.subList(0, amount);
        permutations = new HashSet<>(sub);
        sub.clear();
        saveResult(permutations);
        System.out.println(" pemutatuions " + permutations.size());
        System.out.println("Active thread count during task " + Thread.activeCount());
            return CompletableFuture.completedFuture(permutations);
    }
}
