/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.c.stcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Utente
 */
public class MainClient {
    
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        int portaServer = 6789; //porta del server
        InetAddress IPServer = InetAddress.getByName("localhost");
        
        byte[] bufferOUT = new byte[1024]; //buffer du spedizione
        byte[] bufferIN = new byte[1024]; //buffer di ricezione
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        //creazione del socket
        DatagramSocket clientSocket = new DatagramSocket();
        System.out.println("---IL CLIENT E' PRONTO--- *inserisci la stringa da mettere in maiuscolo* ");
        
        //preparazione del messaggio da inviare
        String daSpedire = input.readLine();
        bufferOUT = daSpedire.getBytes();
        
        //trasmissione del messaggio al server
        DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPServer, portaServer);
        clientSocket.send(sendPacket);
        
        //ci prepareiamo alla ricezione
        DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
        clientSocket.receive(receivePacket);
        String ricevuto = new String(receivePacket.getData());
        
        //elaborazione dei dati ricevuti
        int numCaratteri = receivePacket.getLength();
        System.out.println("---LA RISPOSTA DAL SERVER E'---     " + ricevuto);
        
        //termine elaborazione
        clientSocket.close();
        
    }
}
