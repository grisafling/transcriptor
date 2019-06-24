package org.grisaf.transcriptorandino;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Transcriptor {

    public List<String> transcripciones(String entrada) {
        List<String> resultado = paso1Separar(entrada);
        resultado = paso2EliminarCaracteres(resultado);
        resultado = paso3Minusculas(resultado);
        resultado = paso4EliminarHSinC(resultado);
        List<Texto> textos = paso5_1StringATexto(resultado);
        textos = paso5Z(textos);
        textos = paso6GrafiasDobles(textos);
        textos = paso7GueGui(textos);
        textos = paso8Africadas(textos);
        textos = paso9C(textos);
        textos = paso10V(textos);
        textos = paso11JGeGi(textos);
        textos = paso12Y(textos);
        textos = paso13Nn(textos);
        textos = paso14IdentificarLetras(textos);
        textos = paso15IdentificarConsonantes(textos);
        textos = paso16B(textos);
        textos = paso17D(textos);
        textos = paso18G(textos);
        textos = paso19Nasales(textos);
        textos = paso20EliminarTildes(textos);
        textos = paso21NasalizarVocales(textos);
        resultado.clear();
        for (Texto texto: textos) {
            resultado.add(texto.getTranscrito());
        }
        return resultado;
    }

    public List<String> paso1Separar(String entrada) {
        List<String> resultado = subpaso1xSeparar(entrada, ",");
        resultado = subpaso1xSeparar(resultado, ";");
        resultado = subpaso1xSeparar(resultado, "\\.");
        resultado = subpaso1xSeparar(resultado, "¡");
        resultado = subpaso1xSeparar(resultado, "!");
        resultado = subpaso1xSeparar(resultado, "¿");
        resultado = subpaso1xSeparar(resultado, "\\?");
        resultado = subpaso1x2x4xLimpiarVacios(resultado);
        return resultado;
    }

    public List<String> subpaso1xSeparar(String entrada, String caracter) {
        List<String> entradas = new LinkedList<>();
        entradas.add(entrada);
        return subpaso1xSeparar(entradas, caracter);
    }

    public List<String> subpaso1xSeparar(List<String> entradas, String caracter) {
        List<String> resultado = new LinkedList<>();
        for (String entrada: entradas) {
            String[] mensajes = entrada.split(caracter);
            for (String mensaje: mensajes) {
                resultado.add(mensaje.trim());
            }
        }
        return resultado;
    }

    public List<String> subpaso1x2x4xLimpiarVacios(List<String> entradas) {
        return entradas.stream().filter(entrada -> entrada.length() != 0).collect(Collectors.toList());
    }

    public List<String> paso2EliminarCaracteres(List<String> entradas) {
        List<String> resultado = subpaso2xEliminarCaracter(entradas, "-");
        resultado = subpaso2xEliminarCaracter(resultado, "_");
        resultado = subpaso2xEliminarCaracter(resultado, "\"");
        resultado = subpaso2xEliminarCaracter(resultado, "'");
        resultado = subpaso2xEliminarCaracter(resultado, " ");
        return resultado;
    }

    public List<String> subpaso2xEliminarCaracter(List<String> entradas, String caracter) {
        return entradas.stream().map(entrada -> entrada.replaceAll(caracter, "")).collect(Collectors.toList());
    }

    public List<String> paso3Minusculas(List<String> entradas) {
        return entradas.stream().map(entrada -> entrada.toLowerCase(new Locale("es", "BO"))).collect(Collectors.toList());
    }

    public List<String> paso4EliminarHSinC(List<String> entradas) {
        List<String> resultado = entradas.stream().map(entrada -> {
            while (entrada.startsWith("h")) {
                entrada = entrada.substring(1);
            }
            if (entrada.length() == 0) {
                return entrada;
            }
            char ultimoCaracter = entrada.charAt(0);
            String nuevaEntrada = "" + ultimoCaracter;
            for (int i = 1; i < entrada.length(); i++) {
                char caracter = entrada.charAt(i);
                if (caracter != 'h' || (caracter == 'h' && ultimoCaracter == 'c')) {
                    nuevaEntrada = nuevaEntrada + caracter;
                }
                ultimoCaracter = caracter;
            }
            return nuevaEntrada;
        }).collect(Collectors.toList());
        resultado = subpaso1x2x4xLimpiarVacios(resultado);
        return resultado;
    }

    public List<Texto> paso5_1StringATexto(List<String> entradas) {
        return entradas.stream().map(entrada -> new Texto(entrada)).collect(Collectors.toList());
    }

    public List<Texto> paso5Z(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarZ();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso6GrafiasDobles(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarGrafiasDobles();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso7GueGui(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarGueGui();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso8Africadas(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarAfricadas();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso9C(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarC();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso10V(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarV();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso11JGeGi(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarJGeGi();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso12Y(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarY();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso13Nn(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarNn();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso14IdentificarLetras(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.identificarLetras();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso15IdentificarConsonantes(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.identificarConsonantes();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso16B(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarB();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso17D(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarD();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso18G(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarG();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso19Nasales(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.reemplazarNasales();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso20EliminarTildes(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.eliminarTildes();
            return entrada;
        }).collect(Collectors.toList());
    }

    public List<Texto> paso21NasalizarVocales(List<Texto> entradas) {
        return entradas.stream().map(entrada -> {
            entrada.nasalizarVocales();
            return entrada;
        }).collect(Collectors.toList());
    }

}
