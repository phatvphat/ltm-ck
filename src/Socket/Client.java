/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import noname.Utils;

/**
 *
 * @author phatv
 */
public class Client {

    public String TCPSend(String str, String host, int port) {
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            Socket s = new Socket(host, port);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream());

            pw.println(str);
            pw.flush();
            String res = br.readLine();
//            s.close();

            System.out.println("TCP Received: " + res);
            return res;
        } catch (Exception e) {
            System.out.println("Error TCP send: " + e.toString());
        }
        return null;
    }

    public String TCPSend(String data) {
        return TCPSend(data, Utils.HOST, Utils.PORT);
    }

    public String UDPSend(String sendData, String host, int port) {
        String sentence_from_server = null;
        try {
            System.out.println("----------\nUDP sendData: " + sendData);
            DatagramSocket udpSocket = new DatagramSocket();
            DatagramPacket sendPacket = new DatagramPacket(sendData.getBytes(), sendData.length(), InetAddress.getByName(host), port);
            udpSocket.send(sendPacket);

            byte[] buffer = new byte[1024];
            // Gói tin nhận
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
            udpSocket.receive(incoming);
            sentence_from_server = new String(incoming.getData(), 0, incoming.getLength());
            System.out.println("UDP Received: " + sentence_from_server);

            udpSocket.close();
        } catch (Exception e) {
            System.out.println("Error UDP send: " + e.toString());
        }
        return sentence_from_server;
    }

    public String UDPSend(String data) {
        return UDPSend(data, Utils.HOST, Utils.PORT);
    }

}
