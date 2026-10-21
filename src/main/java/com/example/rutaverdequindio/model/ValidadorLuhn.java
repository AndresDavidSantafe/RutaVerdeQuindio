package com.example.rutaverdequindio.model;

public class ValidadorLuhn {
    public static boolean verificarTarjeta(String tarjeta) {
        if (tarjeta == null) {
            return false;
        }

        String numLimpio = tarjeta.replace(" ", "");
        if (numLimpio.length() < 13 || numLimpio.length() > 19) {return false;}
        int suma = 0;
        boolean duplicar = false;
        for (int i = numLimpio.length() - 1; i >= 0; i--) {
            char letra = numLimpio.charAt(i);
            if (letra < '0' || letra > '9') {return false;}
            int digito = letra - '0';
            if (duplicar) {
                digito = digito * 2;
                if (digito > 9) digito = digito - 9;
            }
            suma = suma + digito;
            duplicar = !duplicar;
        }
        return suma % 10 == 0;
    }
}
