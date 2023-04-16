package com.example.postgresql.demopostgre.mesobjets;

import java.net.InetAddress;

public class GetIpAddress {

    public static String getAddress(){

        try {
            InetAddress iP = InetAddress.getLocalHost();
            return iP.getHostAddress();
        }
        catch (Exception exc){

        }
        return "";
    }

}
