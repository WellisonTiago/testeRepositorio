/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.Hospital.Dados;

import br.edu.ifnmg.Hospital.Entidade.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WellisonTiago
 */
public class PacienteDAO {

    private static final String SQL_DELETE = "DELETE FROM PACIENTE WHERE CPF = ? ";
    private static final String SQL_INSERT = "INSERT INTO PACIENTE (CPF, NOME,IDADE, SALARIO) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_TODOS = "SELECT CPF, NOME, IDADE, SALARIO FROM PACIENTE ";
    private static final String SQL_SELECT_POR_CPF = "SELECT CPF, NOME,IDADE,SALARIO FROM PACIENTE WHERE CPF  =  ?";

    public void criar(Paciente paciente) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
//Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
//Cria o comando de inserir dados
            comando = conexao.prepareStatement(SQL_INSERT);
//Atribui os parâmetros (Note que no BD o index inicia por 1)
            comando.setString(1, paciente.getCPF());
            comando.setString(2, paciente.getNome());
            comando.setInt(3, paciente.getIdade());
            comando.setDouble(4, paciente.getSalario());

//Executa o comando
            comando.execute();
//Persiste o comando no banco de dados
            conexao.commit();
        } catch (Exception e) {
//Caso aconteça alguma exeção é feito um rollback para o banco de
//dados retornar ao seu estado anterior.
            if (conexao != null) {
                conexao.rollback();
            }
//Lança a exceção do tipo sem verificação
            throw new RuntimeException(e);
        } finally {
//Todo objeto que referencie o banco de dados deve ser fechado
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }

    public List<Paciente> buscarTodos() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Paciente> listaPaciente = new ArrayList<Paciente>();

        try {
//Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
//Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_SELECT_TODOS);
//Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
//O método next retornar boolean informando se existe um próximo
//elemento para iterar
            while (resultado.next()) {
//Instancia um novo objeto e atribui os valores vindo do BD
//(Note que no BD o index inicia por 1)
                Paciente paciente = new Paciente();
                comando.setString(1, paciente.getCPF());
                comando.setString(2, paciente.getNome());
                comando.setInt(3, paciente.getIdade());
                comando.setDouble(4, paciente.getSalario());
//Adiciona um item à lista que será retornada
                listaPaciente.add(paciente);
            }
        } catch (Exception e) {
//Lança a exceção do tipo sem verificação
            throw new RuntimeException(e);
        } finally {
//Todo objeto que referencie o banco de dados deve ser fechado
            if (resultado != null && !resultado.isClosed()) {
                resultado.close();
            }
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        return listaPaciente;
    }

    public Paciente buscarPorCPF(String cpf) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Paciente paciente = null;
        try {
//Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
//Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_SELECT_POR_CPF);
            comando.setString(1, cpf);
//Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
//Se tiver algum retorno
            if (resultado.next()) {
//Instancia um novo objeto e atribui os valores vindo do BD
//(Note que no BD o index inicia por 1)

                comando.setString(1, paciente.getCPF());
                comando.setString(2, paciente.getNome());
                comando.setInt(3, paciente.getIdade());
                comando.setDouble(4, paciente.getSalario());

            }
        } catch (Exception e) {
//Lança a exceção do tipo sem verificação
            throw new RuntimeException(e);
        } finally {
//Todo objeto que referencie o banco de dados deve ser fechado
            if (resultado != null && !resultado.isClosed()) {
                resultado.close();

            }
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        return paciente;
    }

    public Paciente delete(String cpf) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Paciente paciente = null;
        try {
//Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
//Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_DELETE);
            comando.setString(1, cpf);
//Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
//Se tiver algum retorno
            /*            if (resultado.next()) {
            //Instancia um novo objeto e atribui os valores vindo do BD
            //(Note que no BD o index inicia por 1)
            
            comando.setString(1, paciente.getCPF());
            comando.setString(2, paciente.getNome());
            comando.setInt(3, paciente.getIdade());
            comando.setDouble(4, paciente.getSalario());
            
            }*/
        } catch (Exception e) {
//Lança a exceção do tipo sem verificação
            throw new RuntimeException(e);
        } finally {
//Todo objeto que referencie o banco de dados deve ser fechado
            if (resultado != null && !resultado.isClosed()) {
                resultado.close();

            }
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        return paciente;
    }



}
