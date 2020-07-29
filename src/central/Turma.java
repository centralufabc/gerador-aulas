
package central;

import org.json.simple.JSONArray;

import java.util.ArrayList;

public class Turma {
    
    private final String codigo, sigla, titulo, docenteTeoria, docentePratica;
    private final ArrayList<Aula> teoria, pratica;
    
    public Turma(String codigo, String sigla, String titulo, ArrayList<Aula> teoria, ArrayList<Aula> pratica, String docenteTeoria, String docentePratica){
        this.codigo = codigo;
        this.sigla = sigla;
        this.titulo = titulo;
        this.teoria = teoria;
        this.pratica = pratica;
        this.docenteTeoria = docenteTeoria;
        this.docentePratica = docentePratica;
    }
    
    public String getCodigo(){
        return codigo;
    }

    public String getSigla() { return sigla; }
    
    public String getTitulo(){
        return titulo;
    }
    
    public ArrayList<Aula> getTeoria(){
        return teoria;
    }
    
    public ArrayList<Aula> getPratica(){
        return pratica;
    }
        
    public String getDocenteTeoria(){
        return docenteTeoria;
    }
            
    public String getDocentePratica(){
        return docentePratica;
    }

    public JSONArray getTeoriaAsJSONArray() {
        return getAulasAsJSONArray(this.teoria);
    }

    public JSONArray getPraticaAsJSONArray() {
        return getAulasAsJSONArray(this.pratica);
    }

    private JSONArray getAulasAsJSONArray(ArrayList<Aula> aulas) {
        JSONArray array = new JSONArray();

        for(Aula aula : aulas) {
            array.add(aula.getJSONObject());
        }

        return array;
    }
}
