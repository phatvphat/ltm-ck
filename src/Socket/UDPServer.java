/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import DB.DBQuery;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;
import noname.Utils;
import org.json.JSONObject;

/**
 *
 * @author phatv
 */
public class UDPServer {

    public boolean stop = false;

    public UDPServer() {
        run();
    }

    public void run() {
        Client client = new Client();

        DatagramSocket socket;
        try {
            String HOST_WEMOS = "192.168.1.70";
            int PORT_WEMOS = 18;

            byte[] buffer = new byte[1024];
            socket = new DatagramSocket(Utils.PORT);
            DatagramPacket receivePacket;
            String stReceive;

            System.out.println("UDP Server started.");
            while (!stop) {
                receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);
                stReceive = new String(receivePacket.getData(), 0, receivePacket.getLength());

                try {
                    System.out.println("----------\nReceived: " + stReceive);
                    JSONObject jsonObj = new JSONObject(stReceive);
                    String action = String.valueOf(jsonObj.get("action"));

                    switch (action.toLowerCase()) {
                        case "sensor":
                            stReceive = client.UDPSend(stReceive, HOST_WEMOS, PORT_WEMOS);
                            break;
                        default:
                            break;
                    }

                    InetAddress address = receivePacket.getAddress();
                    int port = receivePacket.getPort();

                    DatagramPacket outsending2 = new DatagramPacket(stReceive.getBytes(), stReceive.length(), address, port);
                    socket.send(outsending2);
                } catch (Exception e) {
                    System.out.println("Error: " + e.toString());
                }
            }
            System.out.println("UDP Server stopped.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    public static void main(String[] args) {
//        run();
//    }
}
