
package central;

public class Turma {
    
    String codigo, titulo, teoria, pratica, docenteTeoria, docentePratica;
    
    public Turma(String codigo, String titulo, String teoria, String pratica, String docenteTeoria, String docentePratica){
        this.codigo = codigo;
        this.titulo = titulo;
        this.teoria = teoria;
        this.pratica = pratica;
        this.docenteTeoria = docenteTeoria;
        this.docentePratica = docentePratica;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
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
