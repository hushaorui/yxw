package com.hsr.yxw.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public abstract class IpUtils {
    private static String localIp;
    static {
        localIp = getLocalIp();
    }
    public static String getLocalIp() {
        if (localIp != null) {
            return localIp;
        }
        Enumeration<NetworkInterface> networks = null;
        try {
            // 获取网卡设备
            networks = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            //logger.info(e.getMessage());
            e.printStackTrace();
        }
        InetAddress ip;
        Enumeration<InetAddress> address;
        // 遍历网卡设备
        while (networks.hasMoreElements()){
            address = networks.nextElement().getInetAddresses();
            while (address.hasMoreElements()){
                ip = address.nextElement();
                if (ip != null && ip instanceof InetAddress && ip.isSiteLocalAddress()){
                    if (ip.getHostAddress()==null || "".equals(ip.getHostAddress())){
                        //logger.info("获取到的客户端内网ip为空，从配置文件读取本地ip。");
                        return null;
                    }
                    return ip.getHostAddress(); // 客户端ip
                }
            }
        }
        return null;
    }
}
