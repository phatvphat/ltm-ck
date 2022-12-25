/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noname;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;

/**
 *
 * @author phatv
 */
public class TCPClient {

    String hostname = "192.168.1.70";
    int port = 18;

    JSONObject json = new JSONObject();
    String str_temp = null;

    PrintWriter pw = null;
    BufferedReader br = null;

    public void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public String transmission(String msg) {
        try {
            Socket s = new Socket(hostname, port);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream());
            System.out.println("Đã kết nối... Đang gửi dữ liệu...");

            long start = System.currentTimeMillis();
            pw.print(msg);
            pw.flush();
            String res = br.readLine();

            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println("Đã nhận dữ liệu. Đóng kết nối. Time elapsed: " + timeElapsed + "ms.");
            return res;
        } catch (IOException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (pw != null) {
                    pw.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
