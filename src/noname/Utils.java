/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noname;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phatv
 */
public class Utils {

    public static String HOST = "127.0.0.1";
    public static int PORT = 4321;

    public static String hashMD5(String input) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public void checkHosts(String subnet) {
        int timeout = 1000;
        System.out.println("Start scanning:\n");
        for (int i = 2; i < 255; i++) {
            String host = subnet + "." + i;
            new Thread(() -> {
                try {
                    InetAddress hostAddr = InetAddress.getByName(host);
                    if (hostAddr.isReachable(timeout)) {
                        NetworkInterface inf = NetworkInterface.getByInetAddress(hostAddr);
                        System.out.println(host + " [" + (inf != null ? Arrays.toString(inf.getHardwareAddress()) : "") + "]" + " is reachable... " + hostAddr.getHostName());

                    } else {
//                        System.out.println(host + " is not reachable...");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
            try {
                Thread.sleep(100);

            } catch (InterruptedException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("End scanning.\n");
    }

    public String getARPTable(String cmd) {
        Scanner s = null;
        try {
            s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s.hasNext() ? s.next() : "";
    }

    public String getARPTable() {
        return getARPTable("arp -a");
    }

    public static String ValidateSize(long size) {
        long FileSize = size; // in MiB
        long marker = 1024; // Change to 1000 if required
        int decimal = 3; // Change as required
        long kiloBytes = marker; // One Kilobyte is 1024 bytes
        long megaBytes = marker * marker; // One MB is 1024 KB
        long gigaBytes = marker * marker * marker; // One GB is 1024 MB
        long teraBytes = marker * marker * marker * marker; // One TB is 1024 GB

        // return bytes if less than a KB
        if (FileSize < kiloBytes) {
            return FileSize + " Bytes";
        } // return KB if less than a MB
        else if (FileSize < megaBytes) {
            long a = (FileSize / kiloBytes);
            return a + " KB";
        } // return MB if less than a GB
        else if (FileSize < gigaBytes) {
            long a = (FileSize / megaBytes);
            return a + " MB";
        } // return GB if less than a TB
        else {
            long a = (FileSize / gigaBytes);
            return a + " GB";
        }
    }
}
