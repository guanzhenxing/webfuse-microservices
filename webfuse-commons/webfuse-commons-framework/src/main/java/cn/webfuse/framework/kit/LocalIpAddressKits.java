package cn.webfuse.framework.kit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 获取本地ip的工具。
 * <p>
 * 参考：https://my.oschina.net/u/2342969/blog/1359123
 */
public class LocalIpAddressKits {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalIpAddressKits.class);


    /**
     * 获取本地IP地址
     *
     * @throws SocketException
     */
    public static String getLocalAddress() {
        try {
            if (isWindowsOS()) {
                return getLocalHostName();
            } else {
                return getLinuxLocalIp();
            }
        } catch (UnknownHostException | SocketException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 判断操作系统是否是Windows
     *
     * @return
     */
    private static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本地Host名称
     */
    public static String getLocalHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     * @throws SocketException
     */
    private static String getLinuxLocalIp() throws SocketException {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface networkInterface = en.nextElement();
                String name = networkInterface.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipAddress = inetAddress.getHostAddress();
                            if (!ipAddress.contains("::") && !ipAddress.contains("0:0:") && !ipAddress.contains("fe80")) {
                                ip = ipAddress;
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            ip = "127.0.0.1";
            LOGGER.error(ex.getMessage());
        }
        return ip;
    }


}
