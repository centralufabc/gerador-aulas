/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package central;

import java.util.ArrayList;

/**
 *
 * @author rodrigo98rm
 */
public class Aluno {
    
    private String ra;
    private ArrayList<String> codigos = new ArrayList<>();
    
    
    public Aluno (String ra) {
        this.ra = ra;
        this.codigos = codigos;
    }

    public String getRa() {
        return ra;
    }

    public ArrayList<String> getCodigos() {
        return codigos;
    }
    
    public void addCodigo(String codigo){
        codigos.add(codigo);
    }
    
}
