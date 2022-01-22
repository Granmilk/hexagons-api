package com.gtbr.hexapi.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Validator {

    private Validator(){}

    private static final String VALIDATOR_TOKEN = "0JFA98SDJF2LMFD";

    public static String crypt(String string) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] messageDigest = algorithm.digest(string.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%X", 0xFF & b));
        }
        string = hexString.toString();
        return string;
    }

    public static String createMatchValidator(UUID userId, Long coin, Long score, String gameMode, Integer difficulty) {
        System.out.println(crypt(userId + score.toString() + gameMode + coin.toString() + difficulty + VALIDATOR_TOKEN));
        return crypt(userId + score.toString() + gameMode + coin.toString() + difficulty + VALIDATOR_TOKEN);
    }
}
