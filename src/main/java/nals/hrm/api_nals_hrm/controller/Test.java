package nals.hrm.api_nals_hrm.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;


public class Test {
//    private static int workload = 14;
//
//    public static String hashPassword(String password_plaintext) {
//        String salt = BCrypt.gensalt(workload);
//        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
//
//        return(hashed_password);
//    }
//
//    public static boolean checkPassword(String password_plaintext, String stored_hash) {
//        boolean password_verified = false;
//
//        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
//            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
//
//        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
//
//        return(password_verified);
//    }

    public static void main(String[] args) {
        String pass = "123456";
        String s1 = "$2a$10$ixzSY.L9zeL2fbE41Qa/GuP4nDUEoBtQgXuoM1aTTglWrQPuyr5pe"; //java spring
        String s2 = "$2y$10$FtIuJDw1cCgjbmMwYXu3CuFU06U6BrzjfKA9oh4Gox6E13I2sW6Ue"; //php
        s2 = s2.replace("$2y$","$2a$");
        System.out.println("replace: "+s2);
        System.out.println("check: "+BCrypt.checkpw(pass,s2));
    }
}