
package pontoeletronico1;

public  class  Usuario extends Funcionario {
    public  String login="ads";
    
     public String getlogin() {
		return login;
	}
	public void setlogin(String login) {
		this.login = login;
	}
        
    public void verificacao (String log,String sen){
        if (login.equals(log)&& sen.equals("12345"))
                {
            System.out.println("Acessor Permitido");
           }
                else{
            System.out.println("Acessor Negado");
	
        }
        
    }
}