package org.example;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args)
    {
         Scanner sc = new Scanner(System.in);
        System.out.print("Enter the board Size:");
        int n = sc.nextInt();
        TicGame ticgame = new TicGame(n);


    }
}
