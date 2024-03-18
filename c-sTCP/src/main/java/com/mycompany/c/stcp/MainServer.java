/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.c.stcp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Utente
 */
public class MainServer {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket serverSocket = new DatagramSocket(6789);
        boolean attivo = true;
        byte[] bufferIN = new byte[1024];
        byte[] bufferOUT = new byte[1024];
        
        System.out.println("---IL SERVER E' AVVIATO---");
        while(attivo);   
              
        DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length); 
        
        //attesa della ricezione
        serverSocket.receive(receivePacket);
        
        //analisi del pacchetto ricevuto
        String ricevuto = new String(receivePacket.getData());
        int numCaratteri = receivePacket.getLength();
        System.out.println("---IL MESSAGGIO RICEVUTO E'---     " + ricevuto);
        
        //riconoscimento dei parametri del socket del client
        InetAddress IPClient = receivePacket.getAddress();
        int portaClient = receivePacket.getPort();
        
        //preparo il dato da spedire
        String daSpedire = ricevuto.toUpperCase();
        bufferOUT = daSpedire.getBytes();
        
        //invio del datagramma
        DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPClient, portaClient);
        serverSocket.send(sendPacket);
        
        //controllo del termine dell'esecuzione
        if (ricevuto.equals("fine")) {
            System.out.println("---SERVER IN CHIUSURA---");
            attivo=false;
        }
        serverSocket.close(); 
    }   
}
