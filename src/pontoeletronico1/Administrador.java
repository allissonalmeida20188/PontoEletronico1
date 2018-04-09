
package pontoeletronico1;
import java.util.ArrayList;
import java.util.List;
public class Administrador extends Funcionario {
    List<Funcionario> lista = new ArrayList<Funcionario>();
    private int matricula = 123;
    private String senha = "123";
   
    public Administrador() {
	
    }
   
    @Override
    public int getMatricula() {
	return this.matricula;
    }
    @Override
    public String getSenha() {
	return this.senha;
    }
   
    @Override
    public void setMatricula(int m) {
	this.matricula = m;
    }
    @Override
    public void setSenha(String s) {
	this.senha = s;
    }
   
    public void cadastrarFunc(Funcionario f) {
	
	lista.add(f);
    }
   
}