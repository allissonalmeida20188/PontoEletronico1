
package pontoeletronico1;

abstract public class Funcionario {
     public String nome;
     public String cpf;
     protected double salario;
     public int Matricula;
     public String Senha;
     
     
     public String getSenha() {
		return Senha;
	}
	public void setSenha(String Senha) {
		this.Senha = Senha;
	}   
      public int getMatricula() {
		return Matricula;
	}
	public void setMatricula(int Matricula) {
		this.Matricula = Matricula;
	}
     
        public String getNome() {
		return nome;
	}
	public void setnome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
        public double salario() {
		return salario;
	}
	public void setsalario(double salario) {
		this.salario = salario;
	}

   
    boolean estaLogado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setEstaLogado(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getHorasTrab() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getSalario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}