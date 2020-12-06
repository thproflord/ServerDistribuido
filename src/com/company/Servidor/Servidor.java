package com.company.Servidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    //Ruta del Archivo XML
    static String path = "./src/BBA.xml";
    private Scanner scanner;
    private String ip = "192.168.0.104";
    private int port = 10578;

    /*Funcion Principal del Servidor
    * */
    public void run()throws IOException {
        InetAddress inet = InetAddress.getByName(ip);
        ServerSocket ss;
        System.out.println("Inicializando servidor... ");
        System.out.println("Para conectarse al servidor utilizar IP: " + this.ip + " y Puerto: " + port);
        try {

            ss = new ServerSocket(port,50,inet);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: "+socket);
                new ServidorHilo(socket, idSession).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Lector lector = new Lector(this.path);
        lector.open();
        scanner = new Scanner(System.in);
        while(true){
            System.out.println("Que desea hacer");
            String input = scanner.nextLine();
            String name = deletingWords(input);
            if (input.equals("QUIT")) break;
            if(input.contains("get author") && name != ""){
                System.out.println(lector.getBooksbyAuthor(name));
            }else if(input.contains("get title") && name != "+"){
                System.out.println(lector.getBookbyTitle(name));
            }else{
                System.out.println("400");
            }

            //System.out.println(lector.getBooksbyAuthor(input));
        }*/
    }

    private String deletingWords(String s){
        if(s.length() > 2) {
            String response = "";
            String[] split = s.split(" ");
            for (int i = 2; i < split.length; i++)
                response = response + split[i] + " ";

            return response.substring(0, response.length() - 1);
        }
        return "";
    }
}
