package DAO;

import DTO.CargoDTO;
import DTO.FuncionarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FuncionarioDAO {

    Connection conexao;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<FuncionarioDTO> lista = new ArrayList<>();
    ArrayList<CargoDTO> listacargo = new ArrayList<>();

    public void cadastrarFuncionario(FuncionarioDTO objFuncionarioDTO) {

        //Comando sql que eu desejo realizar;
        String sql = "insert into funcionario (nome_funcionario, endereco_funcionario, cod_cargo) values (?, ?, ?)";

        conexao = new ConexaoDAO().conectaBD();

        
        try {
            
             if("".equals(objFuncionarioDTO.getNome_funcionario()) || "".equals(objFuncionarioDTO.getEndereco_funcionario())) {
              
                JOptionPane.showMessageDialog(null, "Preencher os Campos: ");

            } else {
                 
                pstm = conexao.prepareStatement(sql);

                pstm.setString(1, objFuncionarioDTO.getNome_funcionario());
                pstm.setString(2, objFuncionarioDTO.getEndereco_funcionario());
                pstm.setInt(3, objFuncionarioDTO.getCod_cargo());

                pstm.execute();
                pstm.close();

             }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Cadastrar: " + erro);
        }

    }
    
    public void editarFuncionario(FuncionarioDTO objFuncionarioDTO) {
        conexao = new ConexaoDAO().conectaBD();
  
        String sql = "UPDATE funcionario SET nome_funcionario = ?, endereco_funcionario = ?, cod_cargo = ? WHERE id_funcionario = ?";
        
           try {
            
            pstm = conexao.prepareStatement(sql);
            
            
            if("".equals(objFuncionarioDTO.getNome_funcionario()) || "".equals(objFuncionarioDTO.getEndereco_funcionario())) {
              
                JOptionPane.showMessageDialog(null, "Preencher os Campos: ");

            } else {
                
            pstm.setString(1, objFuncionarioDTO.getNome_funcionario());
            pstm.setString(2, objFuncionarioDTO.getEndereco_funcionario());
            pstm.setInt(3, objFuncionarioDTO.getCod_cargo());
            pstm.setInt(4, objFuncionarioDTO.getId_funcionario());
            
            pstm.execute();
            pstm.close();
            
            }


        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Editar: " + erro);
        }
        
    }
    
    public void ExcluirFuncionario(FuncionarioDTO objFuncionarioDTO) {
        conexao = new ConexaoDAO().conectaBD();
        
        String sql = "delete from funcionario where id_funcionario= ?";
        
        try {
            
            pstm = conexao.prepareStatement(sql);
            
            pstm.setInt(1, objFuncionarioDTO.getId_funcionario());
            
            pstm.execute();
            pstm.close();
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Excluir: " + erro);
        }
    }
    
    public ArrayList<FuncionarioDTO> PesquisarFuncionario() {
        conexao = new ConexaoDAO().conectaBD();

        
        String sql = "Select * from funcionario";
        
        try {
            
            pstm = conexao.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()) {
                
                FuncionarioDTO objFuncionarioDTO = new FuncionarioDTO();
                
                objFuncionarioDTO.setId_funcionario(rs.getInt("id_funcionario"));
                objFuncionarioDTO.setNome_funcionario(rs.getString("nome_funcionario"));
                objFuncionarioDTO.setEndereco_funcionario(rs.getString("endereco_funcionario"));
                
                lista.add(objFuncionarioDTO);
                    
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar: " + erro);
        }
        
        return lista;
    
    }

    public ArrayList<CargoDTO> PesquisarCargo() {
        conexao = new ConexaoDAO().conectaBD();

        
        String sql = "SELECT f.id_funcionario, f.nome_funcionario, f.endereco_funcionario, c.nome_cargo\n" +
                     "FROM funcionario f\n" +
                     "JOIN cargo c ON f.cod_cargo = c.id_cargo;";
        
        try {
            
            pstm = conexao.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()) {
                
                CargoDTO objCargoDTO = new CargoDTO();
                
                objCargoDTO.setNome_cargo(rs.getString("c.nome_cargo"));
                listacargo.add(objCargoDTO);
                    
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO PesquisarCargo: " + erro);
        }
        
        return listacargo;
    
    }
    
    public ResultSet listarCargo() {
        conexao = new ConexaoDAO().conectaBD();
        
        String sql = "SELECT * FROM cargo ORDER BY nome_cargo;";
        
        try {
            pstm = conexao.prepareCall(sql);
            return pstm.executeQuery();
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO ListarCaro: " + error);
            return null;
        }
        
    
    }
    
    
}
