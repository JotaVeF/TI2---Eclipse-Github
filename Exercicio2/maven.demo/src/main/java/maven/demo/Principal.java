package maven.demo;

import java.io.*;

import maven.demo.DAO;
import maven.demo.Usuario;

public class Principal {
	public static void Listar (DAO dao) {
		Usuario[] usuarios = dao.getUsuarios();
		usuarios = dao.getUsuarios();
		System.out.println("\nUsuarios:");		
		for(int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}
	}
	
	public static void Inserir (DAO dao, int codigo, String login, String senha, char sexo) {
		Usuario usuario = new Usuario(codigo, login, senha, sexo);
		if(dao.inserirUsuario(usuario) == true) {
			System.out.println("Inserção com sucesso -> " + usuario.toString());
		}
	}

	public static void Excluir (DAO dao, int codigo) {
		if(dao.excluirUsuario(codigo) == true) {
			System.out.println("Excluido com sucesso");
		}
	}
	public static void AtualizarCodigo (DAO dao, int codigo, int novoCodigo) {
		Usuario[] usuarios = dao.getUsuarios();
		usuarios = dao.getUsuarios();		
		for(int i = 0; i < usuarios.length; i++) {
			if (usuarios[i].getCodigo() == codigo) {
				usuarios[i].setCodigo(novoCodigo);
				dao.atualizarUsuario(usuarios[i]);
			}
		}
	}
	public static void AtualizarLogin (DAO dao, int codigo, String novoLogin) {
		Usuario[] usuarios = dao.getUsuarios();
		usuarios = dao.getUsuarios();		
		for(int i = 0; i < usuarios.length; i++) {
			if (usuarios[i].getCodigo() == codigo) {
				usuarios[i].setLogin(novoLogin);
				dao.atualizarUsuario(usuarios[i]);
			}
		}
	}
	public static void AtualizarSenha (DAO dao, int codigo, String novaSenha) {
		Usuario[] usuarios = dao.getUsuarios();
		usuarios = dao.getUsuarios();		
		for(int i = 0; i < usuarios.length; i++) {
			if (usuarios[i].getCodigo() == codigo) {
				usuarios[i].setSenha(novaSenha);
				dao.atualizarUsuario(usuarios[i]);
			}
		}
	}
	public static void AtualizarSexo (DAO dao, int codigo, char novoSexo) {
		Usuario[] usuarios = dao.getUsuarios();
		usuarios = dao.getUsuarios();		
		for(int i = 0; i < usuarios.length; i++) {
			if (usuarios[i].getCodigo() == codigo) {
				usuarios[i].setSexo(novoSexo);
				dao.atualizarUsuario(usuarios[i]);
			}
		}
	}
	
	public static void menu () {
		System.out.println("\n======== MENU ========");
		System.out.println("1 - Listar usuarios");
		System.out.println("2 - Inserir usuarios");
		System.out.println("3 - Excluir usuarios");
		System.out.println("4 - Atualizar usuarios");
		System.out.println("5 - Sair");
		System.out.println("======================");
	}
	
	
	public static void main(String[] args) throws IOException {
		
		DAO dao = new DAO();
		dao.conectar();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));

		int opcao = 0;
		int codigo = 0;
		String login = "";
		String senha = "";
		char sexo = '?';
		int atualizar= 0;
		
		while (opcao != 5) {
			menu();
			System.out.print("\nEntre com a opcao:");
			opcao = Integer.parseInt(br.readLine());
			
			if(opcao == 1) {
				Listar (dao);
			}else if(opcao == 2) {
				System.out.print("\nCodigo: ");
				codigo = Integer.parseInt(br.readLine());
				System.out.print("Login: ");
				login = br.readLine();
				System.out.print("Senha: ");
				senha = br.readLine();
				System.out.print("sexo: ");
				sexo = br.readLine().charAt(0);
				
				Inserir (dao, codigo, login, senha, sexo); 	
				
			}else if(opcao == 3) {
				System.out.print("\nCodigo: ");
				codigo = Integer.parseInt(br.readLine());
				Excluir (dao, codigo);
				
			}else if(opcao == 4) {
				System.out.print("\nCodigo do usuario a ser atualizado: ");
				codigo = Integer.parseInt(br.readLine());
				
				System.out.print("Atualizar o codigo? (1=sim; 0=nao):");
				atualizar = Integer.parseInt(br.readLine());
				if(atualizar == 1) {
					int novoCodigo = Integer.parseInt(br.readLine());
					AtualizarCodigo (dao, codigo, novoCodigo);
				}
				
				System.out.print("Atualizar o login? (1=sim; 0=nao):");
				atualizar = Integer.parseInt(br.readLine());
				if(atualizar == 1) {
					String novoLogin = br.readLine();
					AtualizarLogin(dao, codigo, novoLogin);
				}
				
				System.out.print("Atualizar a senha? (1=sim; 0=nao):");
				atualizar = Integer.parseInt(br.readLine());
				if(atualizar == 1) {
					String novaSenha = br.readLine();
					AtualizarSenha (dao, codigo, novaSenha);
				}
				
				System.out.print("Atualizar o sexo? (1=sim; 0=nao):");
				atualizar = Integer.parseInt(br.readLine());
				if(atualizar == 1) {
					char novoSexo = br.readLine().charAt(0);
					AtualizarSexo (dao, codigo, novoSexo);
				}
			}
		}

		dao.close();
	}
}
