/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.Hospital.Negocio;

import br.edu.ifnmg.Hospital.Dados.PacienteDAO;
import br.edu.ifnmg.Hospital.Entidade.Paciente;
import br.edu.ifnmg.Hospital.Negocio.Exception.PacienteIdadeInsuficienteException;
import br.edu.ifnmg.Hospital.Negocio.Exception.PacienteSalarioException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author WellisonTiago
 */
public class PacienteBO {

    /**
     * Regras de negócio a se considerar: 1 - Não pode haver 2 funcionários com
     * a mesma matrícula 2 - Técnico não pode ter um salário superior a 3 mil 3
     * - Analista não pode ter um salário superior a 6 mil
     */
    public void criar(Paciente paciente) throws SQLException, PacienteSalarioException, PacienteIdadeInsuficienteException {
        PacienteDAO pacienteDAO = new PacienteDAO();
       
        if (paciente.getSalario() > 5000) {

            if (paciente.getIdade() < 12 || paciente.getIdade() > 65 ){
                throw new PacienteIdadeInsuficienteException();
            } else {
//Somente cria o funcionário se não existir nenhuma exeção à
//regra de negócio
                pacienteDAO.criar(paciente);
            }
        } else {
            throw new PacienteSalarioException();
        }
    }

    public List<Paciente> buscarTodos() throws SQLException {
        PacienteDAO pacienteDAO = new PacienteDAO();

        return pacienteDAO.buscarTodos();
    }
    
    public  List<Paciente> delete(String CPF) throws SQLException {
     PacienteDAO pacienteDAO = new PacienteDAO();
     
      return (List<Paciente>) pacienteDAO.delete(CPF);

}

}


