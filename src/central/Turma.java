
package central;

public class Turma {
    
    String codigo, sigla, titulo, teoria, pratica, docenteTeoria, docentePratica;
    
    public Turma(String codigo, String sigla, String titulo, String teoria, String pratica, String docenteTeoria, String docentePratica){
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
    
    public String getTeoria(){
        return teoria;
    }
    
    public String getPratica(){
        return pratica;
    }
        
    public String getDocenteTeoria(){
        return docenteTeoria;
    }
            
    public String getDocentePratica(){
        return docentePratica;
    }
}
