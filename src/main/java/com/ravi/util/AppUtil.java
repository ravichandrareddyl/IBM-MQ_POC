package com.ravi.util;

import static com.ravi.constants.AppConstants.SHA256;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.prowidesoftware.swift.io.parser.SwiftParser;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.ravi.model.SwiftMsg;

import org.apache.commons.io.IOUtils;

public class AppUtil {

    private static String encodeHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }


    /**
     * 
     * @param alg support both SHA-256 and SHA1
     * @param input
     * @return
     */
    public static String digest(String alg, String input) {
        try {
            MessageDigest md = MessageDigest.getInstance(alg);
            byte[] buffer = input.getBytes("UTF-8");
            md.update(buffer);
            byte[] digest = md.digest();
            return encodeHex(digest);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static SwiftMsg getSwiftMsg(String fin, String source) throws IOException {
        SwiftMsg model = new SwiftMsg();

        final SwiftParser parser = new SwiftParser();
        SwiftMessage sm = parser.parse(fin);

        model.setSwiftMessage(fin);
        model.setRefNum(sm.getSender());
        model.setSource(source);
        model.setHashValue(AppUtil.digest(SHA256, fin));
        return model;
    }


    public static String readFileByName (String name) throws IOException {
        String msg = IOUtils.toString(AppUtil.class.getResourceAsStream(name),"UTF-8");
        return msg;
  }
}