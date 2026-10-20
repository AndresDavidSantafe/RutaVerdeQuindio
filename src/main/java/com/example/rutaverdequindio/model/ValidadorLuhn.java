package com.example.rutaverdequindio.model;

public class ValidadorLuhn {
    public static boolean verificarTarjeta(String tarjeta) {
        if (tarjeta == null) {
            return false;
        }

        String numLimpio = tarjeta.replace(" ", "");

        if (numLimpio.length() >= 13 && numLimpio.length() <= 19) {
            int acumulador = 0;

            for (int i = numLimpio.length() - 2; i >= 0; i -= 2) {
                int val = Character.getNumericValue(numLimpio.charAt(i));
                int result = val * 2;
                if (result > 9) {
                    acumulador += result - 9;
                } else {
                    acumulador += result;
                }
            }

            for (int i = numLimpio.length() - 1; i >= 0; i -= 2) {
                int val = Character.getNumericValue(numLimpio.charAt(i));
                acumulador += val;
            }

            if (acumulador % 10 == 0) {
                return true;
            }
        }
        return false;
    }
}
