/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.uaa.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * 密码加密存储工具
 *
 * @author renfei
 */
public class PasswordUtils {
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final String PBKDF2_SHA_512_ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int SALT_BYTE_SIZE = 24;
    private static final int HASH_BYTE_SIZE = 18;
    private static final int PBKDF2_ITERATIONS = 18;
    private static final int HASH_SECTIONS = 5;
    private static final int HASH_ALGORITHM_INDEX = 0;
    private static final int ITERATION_INDEX = 1;
    private static final int HASH_SIZE_INDEX = 2;
    private static final int SALT_INDEX = 3;
    private static final int PBKDF2_INDEX = 4;

    public static String createHash(String password) throws CannotPerformOperationException {
        return createHash(password.toCharArray());
    }

    public static String createHash(char[] password) throws CannotPerformOperationException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, "PBKDF2WithHmacSHA512", HASH_BYTE_SIZE);
        int hashSize = hash.length;
        String parts = "sha256:" + PBKDF2_ITERATIONS + ":" + hashSize + ":" + toBase64(salt) + ":" + toBase64(hash);
        return parts;
    }

    public static boolean verifyPassword(String password, String correctHash) {
        try {
            return verifyPassword(password.toCharArray(), correctHash);
        } catch (Exception var3) {
            return false;
        }
    }

    public static boolean verifyPassword(char[] password, String correctHash) throws CannotPerformOperationException, InvalidHashException {
        String[] params = correctHash.split(":");
        if (params.length != 5) {
            throw new InvalidHashException("Fields are missing from the password hash.");
        } else {
            boolean var3 = false;

            int iterations;
            try {
                iterations = Integer.parseInt(params[1]);
            } catch (NumberFormatException var11) {
                throw new InvalidHashException("Could not parse the iteration count as an integer.", var11);
            }

            if (iterations < 1) {
                throw new InvalidHashException("Invalid number of iterations. Must be >= 1.");
            } else {
                Object var4 = null;

                byte[] salt;
                try {
                    salt = fromBase64(params[3]);
                } catch (IllegalArgumentException var10) {
                    throw new InvalidHashException("Base64 decoding of salt failed.", var10);
                }

                Object var5 = null;

                byte[] hash;
                try {
                    hash = fromBase64(params[4]);
                } catch (IllegalArgumentException var9) {
                    throw new InvalidHashException("Base64 decoding of pbkdf2 output failed.", var9);
                }

                boolean var6 = false;

                int storedHashSize;
                try {
                    storedHashSize = Integer.parseInt(params[2]);
                } catch (NumberFormatException var8) {
                    throw new InvalidHashException("Could not parse the hash size as an integer.", var8);
                }

                if (storedHashSize != hash.length) {
                    throw new InvalidHashException("Hash length doesn't match stored hash length.");
                } else {
                    byte[] testHash = null;
                    if ("sha1".equals(params[0])) {
                        testHash = pbkdf2(password, salt, iterations, "PBKDF2WithHmacSHA1", hash.length);
                    } else {
                        if (!"sha256".equals(params[0])) {
                            throw new CannotPerformOperationException("Unsupported hash type.");
                        }

                        testHash = pbkdf2(password, salt, iterations, "PBKDF2WithHmacSHA512", hash.length);
                    }

                    return slowEquals(hash, testHash);
                }
            }
        }
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;

        for (int i = 0; i < a.length && i < b.length; ++i) {
            diff |= a[i] ^ b[i];
        }

        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, String algorithm, int bytes) throws CannotPerformOperationException {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException var7) {
            throw new CannotPerformOperationException("Hash algorithm not supported.", var7);
        } catch (InvalidKeySpecException var8) {
            throw new CannotPerformOperationException("Invalid key spec.", var8);
        }
    }

    private static byte[] fromBase64(String hex) throws IllegalArgumentException {
        return Base64.getDecoder().decode(hex);
    }

    private static String toBase64(byte[] array) {
        return Base64.getEncoder().encodeToString(array);
    }

    public static class CannotPerformOperationException extends Exception {
        public CannotPerformOperationException(String message) {
            super(message);
        }

        public CannotPerformOperationException(String message, Throwable source) {
            super(message, source);
        }
    }

    public static class InvalidHashException extends Exception {
        public InvalidHashException(String message) {
            super(message);
        }

        public InvalidHashException(String message, Throwable source) {
            super(message, source);
        }
    }
}
