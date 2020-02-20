package com.SSE2020.WannaTry.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHashing {
SecureRandom random = new SecureRandom();
public byte[] hashPassword(String pwd) throws NoSuchAlgorithmException {
    byte[] salt = new byte[16];
    random.nextBytes(salt);

    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.update(salt);
    byte[] hashed = md.digest(pwd.getBytes(StandardCharsets.UTF_8));
    return hashed;
}
}
