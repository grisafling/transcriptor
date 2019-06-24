package org.grisaf.transcriptorandino;

import java.util.LinkedList;
import java.util.List;

import org.grisaf.transcriptorandino.letras.LetraTipo;
import org.grisaf.transcriptorandino.letras.LetrasUtils;
import org.grisaf.transcriptorandino.letras.consonantes.ConsonanteModo;
import org.grisaf.transcriptorandino.letras.consonantes.ConsonantePunto;

public class Texto {

    private String original;
    private String transcrito;
    private List<LetraTipo> letraTipos;
    private List<ConsonanteModo> consonanteModos;
    private List<ConsonantePunto> consonantePuntos;
    private List<Boolean> procesado;

    public Texto(String original) {
        this.original = original;
        this.letraTipos = new LinkedList<>();
        this.procesado = new LinkedList<>();
        this.consonanteModos = new LinkedList<>();
        this.consonantePuntos = new LinkedList<>();
    }

    // Getters y setters

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranscrito() {
        return transcrito;
    }

    public void setTranscrito(String transcrito) {
        this.transcrito = transcrito;
    }

    public List<LetraTipo> getLetraTipos() {
        return letraTipos;
    }

    public void setLetraTipos(List<LetraTipo> letraTipos) {
        this.letraTipos = letraTipos;
    }

    public List<ConsonanteModo> getConsonanteModos() {
        return consonanteModos;
    }

    public void setConsonanteModos(List<ConsonanteModo> consonanteModos) {
        this.consonanteModos = consonanteModos;
    }

    public List<ConsonantePunto> getConsonantePuntos() {
        return consonantePuntos;
    }

    public void setConsonantePuntos(List<ConsonantePunto> consonantePuntos) {
        this.consonantePuntos = consonantePuntos;
    }

    public List<Boolean> getProcesado() {
        return procesado;
    }

    public void setProcesado(List<Boolean> procesado) {
        this.procesado = procesado;
    }

    // Metodos

    public boolean estaProcesado() {
        if (this.procesado.size() == 0) {
            return false;
        }
        for (int i = 0; i < this.procesado.size(); i++) {
            if (!this.procesado.get(i)) {
                return false;
            }
        }
        return true;
    }

    public String getProcesadoString() {
        String resultado = "";
        for (boolean proc: this.procesado) {
            if (proc) {
                resultado = resultado + "V";
            } else {
                resultado = resultado + "F";
            }
        }
        return resultado;
    }

    public void reemplazarZ() {
        this.transcrito = this.original + "";
        this.transcrito = this.transcrito.replaceAll("z", "s");
    }

    public void reemplazarGrafiasDobles() {
        this.transcrito = this.transcrito.replaceAll("ll", "ʎ");
        this.transcrito = this.transcrito.replaceAll("rr", "z");
        this.transcrito = this.transcrito.replaceAll("qu", "k");
        for (int i = 0; i < this.transcrito.length(); i++) {
            char caracter = this.transcrito.charAt(i);
            if (caracter == 'ʎ' || caracter == 'z' || caracter == 'k') {
                this.procesado.add(true);
            } else {
                this.procesado.add(false);
            }
        }
    }

    public void reemplazarGueGui() {
        this.transcrito = this.transcrito.replaceAll("gue", "ge");
        this.transcrito = this.transcrito.replaceAll("gui", "gi");
    }

    public void reemplazarAfricadas() {
        this.transcrito = this.transcrito.replaceAll("ch", "tʃ");
        this.transcrito = this.transcrito.replaceAll("tr", "tʳ");
        for (int i = 0; i < this.transcrito.length(); i++) {
            if (i + 1 < this.transcrito.length()) {
                char caracter = this.transcrito.charAt(i);
                char caracterSiguiente = this.transcrito.charAt(i + 1);
                if (caracter == 't' && (caracterSiguiente == 'ʃ' || caracterSiguiente == 'ʳ')) {
                    this.procesado.set(i, true);
                    this.procesado.set(i + 1, true);
                }
            }
        }
    }

    public void reemplazarC() {
        String nuevoTranscrito = "";
        for (int i = 0; i < this.transcrito.length(); i++) {
            char caracter = this.transcrito.charAt(i);
            if (i + 1 < this.transcrito.length()) {
                char caracterSiguiente = this.transcrito.charAt(i + 1);
                if (caracter == 'c') {
                    if (caracterSiguiente == 'e' || caracterSiguiente == 'i') {
                        caracter = 's';
                    } else {
                        caracter = 'k';
                    }
                    this.procesado.set(i, true);
                }
            } else if (caracter == 'c') {
                caracter = 'k';
                this.procesado.set(i, true);
            }
            if (caracter == 's') {
                this.procesado.set(i, true);
            }
            nuevoTranscrito = nuevoTranscrito + caracter;
        }
        this.transcrito = nuevoTranscrito;
    }

    public void reemplazarV() {
        this.transcrito = this.transcrito.replaceAll("v", "b");
    }

    public void reemplazarJGeGi() {
        this.transcrito = this.transcrito.replaceAll("j", "x");
        this.transcrito = this.transcrito.replaceAll("ge", "xe");
        this.transcrito = this.transcrito.replaceAll("gi", "xi");
    }

    public void reemplazarY() {
        this.transcrito = this.transcrito.replaceAll("y", "j");
    }

    public void reemplazarNn() {
        this.transcrito = this.transcrito.replaceAll("ñ", "ɲ");
    }

    public void identificarLetras() {
        this.letraTipos.clear();
        for (char caracter: this.transcrito.toCharArray()) {
            this.letraTipos.add(LetrasUtils.letraTipo(caracter));
        }
    }

    public String getLetraTiposString() {
        String resultado = "";
        for (LetraTipo tipo: this.letraTipos) {
            if (tipo == LetraTipo.VOCAL) {
                resultado = resultado + "V";
            } else {
                resultado = resultado + "C";
            }
        }
        return resultado;
    }

    public void identificarConsonantes() {
        for (int i = 0; i < this.transcrito.length(); i++) {
            char caracter = this.transcrito.charAt(i);
            if (letraTipos.get(i) == LetraTipo.VOCAL) {
                consonanteModos.add(null);
                consonantePuntos.add(null);
            } else {
                consonanteModos.add(LetrasUtils.consonanteModo(caracter));
                consonantePuntos.add(LetrasUtils.consonantePunto(caracter));
            }
        }
    }

    public String getConsonanteModosString() {
        String resultado = "";
        for (ConsonanteModo modo: this.consonanteModos) {
            if (modo == null) {
                resultado = resultado + "-";
            } else if (modo == ConsonanteModo.OCLUSIVA) {
                resultado = resultado + "O";
            } else if (modo == ConsonanteModo.FRICATIVA) {
                resultado = resultado + "F";
            } else if (modo == ConsonanteModo.VIBRANTE_SIMPLE) {
                resultado = resultado + "V";
            } else if (modo == ConsonanteModo.LATERAL) {
                resultado = resultado + "L";
            } else if (modo == ConsonanteModo.NASAL) {
                resultado = resultado + "N";
            } else {
                resultado = resultado + "-";
            }
        }
        return resultado;
    }

    public String getConsonantePuntosString() {
        String resultado = "";
        for (ConsonantePunto punto: this.consonantePuntos) {
            if (punto == null) {
                resultado = resultado + "-";
            } else if (punto == ConsonantePunto.BILABIAL) {
                resultado = resultado + "B";
            } else if (punto == ConsonantePunto.LABIODENTAL) {
                resultado = resultado + "L";
            } else if (punto == ConsonantePunto.INTERDENTAL) {
                resultado = resultado + "I";
            } else if (punto == ConsonantePunto.DENTAL) {
                resultado = resultado + "D";
            } else if (punto == ConsonantePunto.ALVEOLAR) {
                resultado = resultado + "A";
            } else if (punto == ConsonantePunto.PALATAL) {
                resultado = resultado + "P";
            } else if (punto == ConsonantePunto.VELAR) {
                resultado = resultado + "V";
            } else {
                resultado = resultado + "-";
            }
        }
        return resultado;
    }

    public void reemplazarB() {
        String nuevoTranscrito = "";
        char caracterAnterior = ' ';
        ConsonanteModo modoAnterior = ConsonanteModo.NASAL;
        for (int i = 0; i < this.transcrito.length(); i++) {
            char caracter = this.transcrito.charAt(i);
            if (caracter == 'b') {
                if (caracterAnterior != ' ' && modoAnterior != ConsonanteModo.NASAL) {
                    caracter = 'β';
                }
            }
            caracterAnterior = caracter;
            modoAnterior = this.consonanteModos.get(i);
            nuevoTranscrito = nuevoTranscrito + caracter;
        }
        this.transcrito = nuevoTranscrito;
    }

    public void reemplazarD() {
        String nuevoTranscrito = "";
        char caracterAnterior = ' ';
        ConsonanteModo modoAnterior = ConsonanteModo.NASAL;
        for (int i = 0; i < this.transcrito.length(); i++) {
            char caracter = this.transcrito.charAt(i);
            if (caracter == 'd') {
                if (caracterAnterior != ' ' && modoAnterior != ConsonanteModo.NASAL) {
                    caracter = 'ð';
                }
            }
            caracterAnterior = caracter;
            modoAnterior = this.consonanteModos.get(i);
            nuevoTranscrito = nuevoTranscrito + caracter;
        }
        this.transcrito = nuevoTranscrito;
    }

    public void reemplazarG() {
        String nuevoTranscrito = "";
        char caracterAnterior = ' ';
        ConsonanteModo modoAnterior = ConsonanteModo.NASAL;
        for (int i = 0; i < this.transcrito.length(); i++) {
            char caracter = this.transcrito.charAt(i);
            if (caracter == 'g') {
                if (caracterAnterior != ' ' && modoAnterior != ConsonanteModo.NASAL) {
                    caracter = 'ɣ';
                }
            }
            caracterAnterior = caracter;
            modoAnterior = this.consonanteModos.get(i);
            nuevoTranscrito = nuevoTranscrito + caracter;
        }
        this.transcrito = nuevoTranscrito;
    }

    public void reemplazarNasales() {
        String nuevoTranscrito = "";
        ConsonantePunto puntoSiguiente = null;
        for (int i = 0; i < this.transcrito.length(); i++) {
            char caracter = this.transcrito.charAt(i);
            if (i + 1 < this.transcrito.length()) {
                puntoSiguiente = this.consonantePuntos.get(i + 1);
            } else {
                puntoSiguiente = null;
            }
            if (this.consonanteModos.get(i) == ConsonanteModo.NASAL) {
                if (puntoSiguiente == ConsonantePunto.BILABIAL) {
                    caracter = 'm';
                } else if (puntoSiguiente == ConsonantePunto.LABIODENTAL) {
                    caracter = 'ɱ';
                } else if (puntoSiguiente == ConsonantePunto.INTERDENTAL) {
                    caracter = 'ṇ';
                } else if (puntoSiguiente == ConsonantePunto.DENTAL) {
                    caracter = 'ṉ';
                } else if (puntoSiguiente == ConsonantePunto.ALVEOLAR) {
                    caracter = 'n';
                } else if (puntoSiguiente == ConsonantePunto.PALATAL) {
                    caracter = 'ɲ';
                } else if (puntoSiguiente == ConsonantePunto.VELAR) {
                    caracter = 'ŋ';
                }
            }
            nuevoTranscrito = nuevoTranscrito + caracter;
        }
        this.transcrito = nuevoTranscrito;
    }

    public void eliminarTildes() {
        String nuevoTranscrito = "";
        for (int i = 0; i < this.transcrito.length(); i++) {
            char caracter = this.transcrito.charAt(i);
            if (this.letraTipos.get(i) == LetraTipo.VOCAL) {
                if (caracter == 'á') {
                    caracter = 'a';
                } else if (caracter == 'é') {
                    caracter = 'e';
                } else if (caracter == 'í') {
                    caracter = 'i';
                } else if (caracter == 'ó') {
                    caracter = 'o';
                } else if (caracter == 'ú') {
                    caracter = 'u';
                }
            }
            nuevoTranscrito = nuevoTranscrito + caracter;
        }
        this.transcrito = nuevoTranscrito;
    }

    public void nasalizarVocales() {
        String nuevoTranscrito = "";
        ConsonanteModo modoAnterior = ConsonanteModo.NASAL;
        ConsonanteModo modoSiguiente = null;
        for (int i = 0; i < this.transcrito.length(); i++) {
            char caracter = this.transcrito.charAt(i);
            if (i + 1 < this.transcrito.length()) {
                modoSiguiente = this.consonanteModos.get(i + 1);
            } else {
                modoSiguiente = null;
            }
            if (this.letraTipos.get(i) == LetraTipo.VOCAL && modoAnterior == ConsonanteModo.NASAL && modoSiguiente == ConsonanteModo.NASAL) {
                if (caracter == 'a') {
                    caracter = 'ã';
                } else if (caracter == 'e') {
                    caracter = 'ẽ';
                } else if (caracter == 'i') {
                    caracter = 'ĩ';
                } else if (caracter == 'o') {
                    caracter = 'õ';
                } else if (caracter == 'u') {
                    caracter = 'ũ';
                }
            }
            modoAnterior = this.consonanteModos.get(i);
            nuevoTranscrito = nuevoTranscrito + caracter;
        }
        this.transcrito = nuevoTranscrito;
    }

}
