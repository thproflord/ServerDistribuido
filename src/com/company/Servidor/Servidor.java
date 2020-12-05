package com.company.Servidor;

import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Servidor {

    //Ruta del Archivo XML
    static String path = "./src/BBA.xml";
    private Scanner scanner;

    /*Funcion Principal del Servidor
    * */
    public void run(){
        Lector lector = new Lector(this.path);
        lector.open();
        scanner = new Scanner(System.in);
        while(true){
            System.out.println("Que desea hacer");
            String input = scanner.nextLine();
            String name = deletingWords(input);
            if (input.equals("QUIT")) break;
            if(input.contains("get author")){
                System.out.println(lector.getBooksbyAuthor(name));
            }else if(input.contains("get title")){
                System.out.println(lector.getBookbyTitle(name));
            }else{
                System.out.println("400");
            }

            //System.out.println(lector.getBooksbyAuthor(input));
        }
    }

    private String deletingWords(String s){
        String response = "";
        String[] split = s.split(" ");
        for(int i = 2; i < split.length; i++)
            response = response + split[i] + " ";

        return response.substring(0, response.length() - 1);
    }
}
