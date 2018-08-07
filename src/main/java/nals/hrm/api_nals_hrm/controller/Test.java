package nals.hrm.api_nals_hrm.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;


public class Test {


    public static void main(String[] args) {
        String pass = "123456";
        String s1 = "$2a$10$ixzSY.L9zeL2fbE41Qa/GuP4nDUEoBtQgXuoM1aTTglWrQPuyr5pe"; //java spring
        String s2 = "$2y$10$ixzSY.L9zeL2fbE41Qa/GuP4nDUEoBtQgXuoM1aTTglWrQPuyr5pe"; //php
        s2 = s2.replace("$2y$","$2a$");
        System.out.println("replace: "+s2);
        System.out.println("check: "+BCrypt.checkpw(pass,s2));
    }
}