package com.basic;

import java.sql.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BattleShipGame {
    static char[][] map = new char[10][10];


    //ocean map


    public static void oceanMap() {
        System.out.println("***** Welcome to Battle Ships game *****");
        System.out.println("  0123456789  ");


        for (int i = 0; i < 10; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == '1')
                    System.out.print("@ ");
                else if (map[i][j] == '2')
                    System.out.print("  ");
                else if (map[i][j] == '#')
                    System.out.print("  ");
                else
                    System.out.print(map[i][j] + " ");
            }
            System.out.print("|" + i + "\n");
        }
        System.out.println("  0123456789 ");
    }


    public static void shipsDeploy() {
        Scanner input = new Scanner(System.in);

        int x, y;
        boolean IFshipRight;
        //for user
        for (int i = 0; i < 5; i++) {
            do {
                IFshipRight = true;
                System.out.print("Enter your X coordinate " + (i + 1) + ":");
                x = input.nextInt();
                System.out.print("Enter your Y coordinate " + (i + 1) + ":");
                y = input.nextInt();
                if (x < 0 || x > 9 || y < 0 || y > 9) {
                    System.out.println("Please enter a number between 0 and 9");
                    IFshipRight = false;
                } else if (map[x][y] == '1') {
                    System.out.println("you can not have two ships at same place");
                    IFshipRight = false;
                }
            } while (!IFshipRight);
            map[x][y] = '1';
        }

        //computer deploying

        System.out.println(" Computer is deploying ships");

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (map[x][y] == 1);
            System.out.println((i + 1) + ". ship has deployed");
        }
    }


    //main class  here
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        oceanMap();
        shipsDeploy();


        //battle

        int playerShips = 5, compShips = 5, x, y;

        while (playerShips != 0 && compShips != 0) {
            do {
                System.out.println("Your turn");
                System.out.print("enter X coordinate: ");
                x = input.nextInt();
                System.out.print("enter Y coordinate: ");
                y = input.nextInt();
                if (x < 0 || x > 9 || y < 0 || y > 9) {
                    System.out.println(" you have entered wrong number, please consider entering numbers between 0 and 9");
                } else if (map[x][y] == '-') {
                    System.out.println("you have already selected this");
                } else if (map[x][y] == 'x') {
                    System.out.println("Your ship has sunk here");
                } else if (map[x][y] == '!') {
                    System.out.println("You have sunk computer's ship here.");
                }
            } while (x < 0 || x > 9 || y < 0 || y > 9 || map[x][y] == '-' || map[x][y] == 'x' || map[x][y] == '!');

            if (map[x][y] == '2') {
                System.out.println("Boom! You sunk the ship!");
                map[x][y] = '!';
                compShips--;
            } else if (map[x][y] == '1') {
                System.out.println("Oh, you sunk your own ship");
                map[x][y] = 'x';
                playerShips--;
            } else {
                System.out.println("Oh bad, you missed");
                map[x][y] = '-';
            }


            System.out.println("\nCOMPUTER'S TURN");
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (map[x][y] == '#' || map[x][y] == '!' || map[x][y] == 'x');

            if (map[x][y] == '1') {
                System.out.println("The Computer sunk one of your ships!");
                playerShips--;
                map[x][y] = 'x';
            } else if (map[x][y] == '2') {
                System.out.println("The Computer sunk one of its own ships");
                compShips--;
                map[x][y] = '!';
            } else {
                System.out.println("Computer missed");
                map[x][y] = '#';
            }
            System.out.println("Player ships: " + playerShips + " | Computer ships: " + compShips);
        }
        if (playerShips == 0)
            System.out.println("oh, You lose... Better luck next time :) ");
        else
            System.out.println("yayyy! You have won the battle :)");

    }


}





