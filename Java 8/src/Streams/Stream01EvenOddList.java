package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stream01EvenOddList {
    public static void main(String[] args){
        List<Integer> numbers = new ArrayList<>();
        for(int i=0;i<20;i++){
            numbers.add(i);
        }
        System.out.println(numbers);

        //Without Stream
        System.out.println("\nGENERAL APPROACH : TILL 1.7");
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        for(Integer i : numbers){
            if(i%2==0){
                even.add(i);
            }else {
                odd.add(i);
            }
        }
        System.out.println("EVEN -> "+even);
        System.out.println("ODD  -> "+odd);

        //With stream api
        System.out.println("\nWITH STREAM API : 1.8 ONWARD");
        List<Integer> even1 = numbers.stream().filter(i->i%2==0).collect(Collectors.toList());
        List<Integer> odd1 = numbers.stream().filter(i->i%2==1).collect(Collectors.toList());
        System.out.println("EVEN -> "+even1);
        System.out.println("ODD  -> "+odd1);
    }
}
