/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.Scanner;

/**
 *
 * @author Steven-PC
 */
public class Practice {

    /**
     * @param args the command line arguments
     */
    // Another way to comment your code.
    // Let's do another change.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // TEST
        System.out.print("Name: ");
        String Name = scanner.nextLine().trim();
        System.out.println("You are " + Name);
    }
    
}
