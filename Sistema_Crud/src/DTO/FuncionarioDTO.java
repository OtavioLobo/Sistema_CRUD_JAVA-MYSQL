
package DTO;


public class FuncionarioDTO {
    
    private String nome_funcionario;
    private String endereco_funcionario;
    private int id_funcionario;
    private int cod_cargo;
    
    
    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getCod_cargo() {
        return cod_cargo;
    }

    public void setCod_cargo(int cod_cargo) {
        this.cod_cargo = cod_cargo;
    }
    
    
    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getEndereco_funcionario() {
        return endereco_funcionario;
    }

    public void setEndereco_funcionario(String endereco_funcionario) {
        this.endereco_funcionario = endereco_funcionario;
    }

    
    
    
    
}
