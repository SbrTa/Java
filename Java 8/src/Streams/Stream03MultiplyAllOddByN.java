package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stream03MultiplyAllOddByN {
    public static void main(String[] args){
        List<Integer> numbers = new ArrayList<>();
        for(int i=0;i<20;i++){
            numbers.add(i);
        }
        System.out.println(numbers);
        int n = 5;

        //Without Stream
        System.out.println("\nGENERAL APPROACH : TILL 1.7");
        List<Integer> multiple = new ArrayList<>();
        for(Integer i : numbers){
            if(i%2==1){
                multiple.add(i*n);
            }
        }
        System.out.println(multiple);

        //With stream api
        System.out.println("\nWITH STREAM API : 1.8 ONWARD");
        List<Integer> multiple1 = numbers.stream().filter(i->i%2==1).map(i->i*n).collect(Collectors.toList());
        System.out.println(multiple1);
    }
}
