package com.qy.sdk_rx.Utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:10
 */

public class AESUtils {
    private static final String VIPARA = "1269571569321021";
    private static final String bm = "utf-8";

    public AESUtils() {
    }

    public static String aesDecrypt(String var0, String var1) {
        try {
            byte[] var3 = str2ByteArray(var0);
            IvParameterSpec var11 = new IvParameterSpec("1269571569321021".getBytes());
            SecretKeySpec var2 = new SecretKeySpec(var1.getBytes(), "AES");
            Cipher var12 = Cipher.getInstance("AES/CBC/PKCS5Padding");
            var12.init(2, var2, var11);
            byte[] var13 = var12.doFinal(var3);
            var0 = new String(var13, "utf-8");
            return var0;
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        } catch (NoSuchPaddingException var5) {
            var5.printStackTrace();
        } catch (InvalidKeyException var6) {
            var6.printStackTrace();
        } catch (IllegalBlockSizeException var7) {
            var7.printStackTrace();
        } catch (BadPaddingException var8) {
            var8.printStackTrace();
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        } catch (InvalidAlgorithmParameterException var10) {
            var10.printStackTrace();
        }

        var0 = null;
        return var0;
    }

    public static String aesEncrypt(String var0, String var1) {
        try {
            IvParameterSpec var3 = new IvParameterSpec("1269571569321021".getBytes());
            SecretKeySpec var2 = new SecretKeySpec(var1.getBytes(), "AES");
            Cipher var11 = Cipher.getInstance("AES/CBC/PKCS5Padding");
            var11.init(1, var2, var3);
            var0 = byte2HexStr(var11.doFinal(var0.getBytes("utf-8")));
            return var0;
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        } catch (NoSuchPaddingException var5) {
            var5.printStackTrace();
        } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
        } catch (InvalidKeyException var7) {
            var7.printStackTrace();
        } catch (IllegalBlockSizeException var8) {
            var8.printStackTrace();
        } catch (BadPaddingException var9) {
            var9.printStackTrace();
        } catch (InvalidAlgorithmParameterException var10) {
            var10.printStackTrace();
        }

        var0 = null;
        return var0;
    }

    private static String byte2HexStr(byte[] var0) {
        StringBuilder var2 = new StringBuilder();

        for (int var1 = 0; var1 < var0.length; ++var1) {
            String var3 = Integer.toHexString(var0[var1] & 255);
            if (var3.length() == 1) {
                var2.append("0");
            }

            var2.append(var3.toUpperCase());
        }

        return var2.toString();
    }

    private static byte[] str2ByteArray(String var0) {
        int var2 = var0.length() / 2;
        byte[] var3 = new byte[var2];

        for (int var1 = 0; var1 < var2; ++var1) {
            var3[var1] = Byte.valueOf(var0.substring(var1 * 2, var1 * 2 + 2), 16);
        }

        return var3;
    }
}

