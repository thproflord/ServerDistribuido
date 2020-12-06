package com.company.Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorHilo extends Thread{
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSession;

    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSession = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        String accion = "";
        int i = 1;
        try {
            dos.writeUTF("Conexion establecida");
            while(i == 1) {
                accion = dis.readUTF();
                System.out.println("Mensaje del cliente: " + accion);
                dos.writeUTF("ok");
                if (accion.equals("QUIT")) {
                    desconnectar();
                    i = 2;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
