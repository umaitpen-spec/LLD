package org.example;

import java.util.Scanner;

public class TicGame {
    char[][] board;
    char currPlayer = 'X';
    Scanner sc  = new Scanner(System.in);
    int n;
    public TicGame(int n)
    {
        board = new char[n][n];
        this.n = n;
        printBoard();
        System.out.println("Current Player: " +currPlayer+ ". Enter the move(row,col):");
        int row = sc.nextInt();
        int col = sc.nextInt();

        playGame(row,col);
    }
    public void playGame(int row,int col)
    {
        if(row >= 0 && row < n && col >=0 && col < n)
        {
            if(board[row][col] == 'X' || board[row][col] == 'O')
            {
                System.out.println("Wrong Move!");
                System.exit(0);
            }
            board[row][col] = currPlayer;
            printBoard();
            if(board[row][col] == 'E')
            {
                System.exit(0);
            }

            if(isWinner(row,col))
            {
                System.out.println( currPlayer+ " Won the Game!");
                System.exit(0);
            }

            currPlayer = (currPlayer == 'X')?'O':'X';
            System.out.println("Current Player: " +currPlayer+ ". Enter the move(row,col):");
            int currRow = sc.nextInt();
            int currCol = sc.nextInt();
            playGame(currRow,currCol);
        }
        else
        {
            System.out.println("Invalid Move!");
            System.exit(0);
        }
    }
    public boolean isWinner(int row,int col)
    {
        boolean isRow = true, isCol = true, isPDiagonal = true, isSDiagonal = true;
        //check Row
        for(int j=0;j<n;j++)
            if(board[row][j] != currPlayer)
                isRow = false;
        //check Column
        for(int i=0;i<n;i++)
            if(board[i][col] != currPlayer)
                isCol = false;
        //Check Diagonal
        for(int i=0;i<n;i++)
        {
            if(board[i][i] != currPlayer)
                isPDiagonal = false;
            if(board[i][n-i-1] != currPlayer)
                isSDiagonal = false;
        }

        return isRow || isCol || isPDiagonal || isSDiagonal;
    }
    public void printBoard()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
