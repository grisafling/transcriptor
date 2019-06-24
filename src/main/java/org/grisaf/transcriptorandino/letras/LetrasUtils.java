package org.grisaf.transcriptorandino.letras;

import org.grisaf.transcriptorandino.letras.consonantes.ConsonanteModo;
import org.grisaf.transcriptorandino.letras.consonantes.ConsonantePunto;

public class LetrasUtils {

    private static String vocales = "aeiouáéíóú";

    private static String bilabiales = "pbβm";
    private static String labiodentales = "fvɱ";
    private static String interdentales = "ðṇ";
    private static String dentales = "tdṉ";
    private static String alveolares = "sznrln";
    private static String palatales = "ʎɲ";
    private static String velares = "kgxɣŋ";

    private static String oclusivas = "pbtdkg";
    private static String fricativas = "βfvðszxɣ";
    private static String vibrantes = "r";
    private static String laterales = "lʎ";
    private static String nasales = "mɱṇṉnɲŋ";

    public static boolean esVocal(char caracter) {
        return esVocal(caracter + "");
    }

    public static boolean esVocal(String caracter) {
        return vocales.contains(caracter);
    }

    public static LetraTipo letraTipo(char caracter) {
        return letraTipo(caracter + "");
    }

    public static LetraTipo letraTipo(String caracter) {
        if (esVocal(caracter)) {
            return LetraTipo.VOCAL;
        } else {
            return LetraTipo.CONSONANTE;
        }
    }

    public static ConsonanteModo consonanteModo(char caracter) {
        return consonanteModo(caracter + "");
    }

    public static ConsonanteModo consonanteModo(String caracter) {
        if (oclusivas.contains(caracter)) {
            return ConsonanteModo.OCLUSIVA;
        } else if (fricativas.contains(caracter)) {
            return ConsonanteModo.FRICATIVA;
        } else if (vibrantes.contains(caracter)) {
            return ConsonanteModo.VIBRANTE_SIMPLE;
        } else if (laterales.contains(caracter)) {
            return ConsonanteModo.LATERAL;
        } else if (nasales.contains(caracter)) {
            return ConsonanteModo.NASAL;
        } else {
            return null;
        }
    }

    public static ConsonantePunto consonantePunto(char caracter) {
        return consonantePunto(caracter + "");
    }

    public static ConsonantePunto consonantePunto(String caracter) {
        if (bilabiales.contains(caracter)) {
            return ConsonantePunto.BILABIAL;
        } else if (labiodentales.contains(caracter)) {
            return ConsonantePunto.LABIODENTAL;
        } else if (interdentales.contains(caracter)) {
            return ConsonantePunto.INTERDENTAL;
        } else if (dentales.contains(caracter)) {
            return ConsonantePunto.DENTAL;
        } else if (alveolares.contains(caracter)) {
            return ConsonantePunto.ALVEOLAR;
        } else if (palatales.contains(caracter)) {
            return ConsonantePunto.PALATAL;
        } else if (velares.contains(caracter)) {
            return ConsonantePunto.VELAR;
        } else {
            return null;
        }
    }

}
