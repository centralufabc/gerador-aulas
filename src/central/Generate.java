/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package central;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author rodrigo98rm
 */
public class Generate {

    private static String MATRICULAS = "";
    private static String TURMAS = "";
    private static ArrayList<Aluno> alunos = new ArrayList<>();
    private static ArrayList<Turma> turmas = new ArrayList<>();

    public static void main(String[] args) {

        MATRICULAS = convertFileToString("res/matriculas.txt");
        TURMAS = convertFileToString("res/turmas.txt");

        JSONParser parser = new JSONParser();

        // Alunos
        try {
            Object obj = parser.parse(MATRICULAS);
            JSONObject data = (JSONObject) obj;
            JSONArray matriculas = (JSONArray) data.get("respostas");

            String ra;
            String codigo;

            // Percorrer a lista de matriculas
            for (Object o : matriculas) {
                JSONObject matricula = (JSONObject) o;
                ra = matricula.get("RA").toString();
                codigo = matricula.get("CÓDIGO TURMA").toString();

                Aluno aluno = findAluno(matricula.get("RA").toString());

                // Aluno encontrado, adicionar codigo de turma
                if (aluno != null) {
                    aluno.addCodigo(codigo);
                } else {
                    // Aluno ainda não criado
                    // Criar aluno, adicionar codigo de turma e inserir no array de alunos
                    aluno = new Aluno(ra);
                    aluno.addCodigo(codigo);
                    alunos.add(aluno);
                }
            }

            System.out.println("Número de alunos: " + alunos.size());

            // Verificacao de alunos criados no array
//            for (int i = 0; i < 10; i++) {
//                System.out.println(alunos.get(i).getRa());
//                System.out.println(alunos.get(i).getCodigos().toString());
//                System.out.println();
//            }
        } catch (Exception e) {
            System.out.println("Alunos error");
            e.printStackTrace();
        }

        // Turmas
        try {
            Object obj = parser.parse(TURMAS);
            JSONObject data = (JSONObject) obj;
            JSONArray classes = (JSONArray) data.get("respostas");

            for (Object aClass : classes) {
                String codigo, sigla, titulo, teoria, pratica, docenteTeoria = "", docentePratica = "";
                JSONObject classe = (JSONObject) aClass;

                // Dados da turma
                codigo = classe.get("Código").toString();
                titulo = classe.get("Disicplina - turma").toString();
                teoria = classe.get("teoria").toString();
                pratica = classe.get("prática").toString();

                if (classe.get("docente  teoria") != null) {
                    docenteTeoria = classe.get("docente  teoria").toString();
                }

                if (classe.get("docente prática") != null) {
                    docentePratica = classe.get("docente prática").toString();
                }

                if(codigo.length() == 14) {
                    sigla = codigo.substring(2, codigo.length() - 2);
                } else {
                    sigla = codigo.substring(3, codigo.length() - 2);
                }

                turmas.add(new Turma(codigo, sigla, titulo, teoria, pratica, docenteTeoria, docentePratica));
            }

            System.out.println("Número de turmas: " + turmas.size());

        } catch (Exception e) {
            System.out.println("Turmas error");
            e.printStackTrace();
        }

        // Verificacao de turmas criados no array
//        for (int i = 0; i < 10; i++) {
//            System.out.println(turmas.get(i).getTitulo());
//            System.out.println(turmas.get(i).getCodigo());
//            System.out.println();
//        }


        // Gerar resultado com lista de turmas e RAs dos alunos matriculas em cada uma
        JSONArray root = new JSONArray();

        // Para cada turma, criar um objeto dentro do JSONArray e
        // encontrar todos os alunos que estão matriculados nela
        for (Turma turma : turmas) {

            JSONObject turmaJson = new JSONObject();
            JSONArray listaRAs = new JSONArray();

            // Dados da turma
            turmaJson.put("codigo", turma.getCodigo());
            turmaJson.put("sigla", turma.getSigla());
            turmaJson.put("classe", turma.getTitulo());
            turmaJson.put("teoria", turma.getTeoria());
            turmaJson.put("pratica", turma.getPratica());
            turmaJson.put("docenteTeoria", turma.getDocenteTeoria());
            turmaJson.put("docentePratica", turma.getDocentePratica());

            // Obter lista de alunos matriculados na turma

            // Para cada aluno
            for (int j = 0; j < alunos.size(); j++) {
                Aluno aluno = alunos.get(j);

                // Disciplinas do aluno
                ArrayList<String> codigos = aluno.getCodigos();


                // Para cada disciplina em que o aluno esta matriculado
                for (int k = 0; k < codigos.size(); k++) {
                    String codigo = codigos.get(k);
                    if (codigo.equals(turma.getCodigo())) {
                        listaRAs.add(aluno.getRa());
                    }
                }
            }

            // Adicionar array de RAs na turma
            turmaJson.put("lista_ra", listaRAs);

            // Adicionar turma na lista final de turmas
            root.add(turmaJson);
        }

        // Gerar o arquivo final
        try (FileWriter file = new FileWriter("res/result.txt")) {
            file.write(root.toJSONString());
            file.flush();
//            System.out.println(root.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Aluno findAluno(String ra) {
        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);

            if (aluno.getRa().equals(ra)) {
                return aluno;
            }
        }

        return null;
    }

    //Convert the file that contains th data into a String, so it can be parsed
    //The code inside this method was obtained from the following question on StackOverflow:
    //https://stackoverflow.com/questions/4226360/reading-a-text-file-in-java-and-making-it-a-string
    private static String convertFileToString(String file) {

        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            char[] buf = new char[1024];
            int len;
            while ((len = in.read(buf, 0, buf.length)) > 0) {
                sb.append(buf, 0, len);
            }
            in.close();
        } catch (IOException e) {
        }
        return sb.toString();
    }

}
