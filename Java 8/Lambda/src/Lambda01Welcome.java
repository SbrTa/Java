
@FunctionalInterface
interface Abc{
    void print();
}


public class Lambda01Welcome {
    public static void main(String arg[]){
        /*Abc abc = new Abc() {
            @Override
            public void print() {
                System.out.println("Welcome to Lambda Land...");
            }
        };*/
        Abc abc = () -> System.out.println("Welcome to Lambda Land...");

        abc.print();
    }
}
