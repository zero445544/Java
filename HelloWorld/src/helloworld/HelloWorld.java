package helloworld;

public class HelloWorld {

    public static void main(String[] args) {
        String message = "  Hello World" + "!!  ";
        
        System.out.println(message.replace("!" , "*"));
        System.out.println(message.trim());
        System.out.println(message);
    }
     
}
