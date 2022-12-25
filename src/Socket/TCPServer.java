/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import noname.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author phatv
 */
public class TCPServer {

    Client client = new Client();

    public boolean stop = false;

    public TCPServer() {
        run();
    }

    public void run() {
        Utils utils = new Utils();

        String HOST_WEMOS = "192.168.1.70";
        int PORT_WEMOS = 18;
        while (!stop) {
            try {
                System.out.println("TCP Server started.");
                ServerSocket ss = new ServerSocket(Utils.PORT);
                Socket s = ss.accept();
                PrintWriter pw;
                BufferedReader br;
                pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));

                String str = br.readLine();
//                        pw.println(upper);
//                        pw.flush();

                JSONObject jsonObj = new JSONObject(str);
                String action = String.valueOf(jsonObj.get("action"));

                JSONObject jsonRes = new JSONObject();
                jsonRes.put("success", false);
                switch (action.toLowerCase()) {
                    case "scan-lan":
                        String a = utils.getARPTable("arp -a");
                        ArrayList list = new ArrayList();
                        a = a.replaceAll("\\s{2,}", " ").trim();
//                            System.out.println(a);
                        Pattern pattern = Pattern.compile("([0-9.]+) ([a-z0-9-]+) dynamic");
                        Matcher matcher = pattern.matcher(a);
                        while (matcher.find()) {
                            list.add(matcher.group());
                        }
                        jsonRes.put("success", true);
                        JSONArray arr = new JSONArray(list);
                        jsonRes.put("data", arr);
                        str = jsonRes.toString();
                        break;
                    case "get-led-state":
                        str = client.TCPSend(str, HOST_WEMOS, PORT_WEMOS);
                        break;
                    case "set-led-state":
                        str = client.TCPSend(str, HOST_WEMOS, PORT_WEMOS);
                        break;
                    case "get-led":
                        str = client.TCPSend(str, HOST_WEMOS, PORT_WEMOS);
                        break;
                    case "set-led":
                        str = client.TCPSend(str, HOST_WEMOS, PORT_WEMOS);
                        break;
                    default:
//                        str = str + " (Server accepted!)";
                }

                pw.println(str);
                pw.close();
                s.close();
                ss.close();
                System.out.println("TCP Server stopped.");
            } catch (Exception e) {
                System.out.println("Error TCP: " + e.toString());
            }
        }
    }

//    public static void main(String argv[]) {
//        run();
//    }
}
