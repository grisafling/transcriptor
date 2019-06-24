package org.grisaf.transcriptorandino;

import java.util.List;

public class Library {
    public boolean someLibraryMethod() {
        return true;
    }
    public Transcriptor getInstance() {
        return new Transcriptor();
    }

    public String transcripcion(String entrada) {
        Transcriptor transcriptor = this.getInstance();
        List<String> secuencias = transcriptor.transcripciones(entrada);
        return String.join(" ", secuencias);
    }

}
