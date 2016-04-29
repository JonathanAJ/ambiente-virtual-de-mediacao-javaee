package com.mediacaovirtual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mediacaovirtual.controller.Banco;
import com.mediacaovirtual.model.Usuario;

public class UsuarioDAO {
	
	public boolean isUsuario(String cpflogin, String senha){ 
		try {
			Connection con = new Banco().getConexao();
			String query = "SELECT * FROM usuario WHERE cpf_login=? AND senha=?";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, cpflogin);
			pstm.setString(2, senha);
			ResultSet rs = pstm.executeQuery();
			boolean isUsuario = rs.next();
			rs.close();
			pstm.close();
			con.close();
			if (isUsuario) {
				System.out.println("É um usuário");
				return true;
			} else {
				System.out.println("Não é um usuário");
				return false;
			}
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isUsuario(String cpflogin){ 
		try {
			Connection con = new Banco().getConexao();
			String query = "SELECT * FROM usuario WHERE cpf_login=?";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, cpflogin);
			ResultSet rs = pstm.executeQuery();
			boolean isUsuario = rs.next();
			rs.close();
			pstm.close();
			con.close();
			if (isUsuario) {
				System.out.println("É um usuário");
				return true;	
			} else {
				System.out.println("Não é um usuário");
				return false;
			}
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean createUsuario(String cpf, String senha, String nome){
		try {
			if(isUsuario(cpf)){
				return false;
			}else{
				Connection con = new Banco().getConexao();
				String query = "INSERT INTO usuario (cpf_login, senha, nome) VALUES (?, ?, ?)";
				PreparedStatement pstm = con.prepareStatement(query);
				pstm.setString(1, cpf);
				pstm.setString(2, senha);
				pstm.setString(3, nome);
				pstm.executeUpdate();
				pstm.close();
				con.close();
				System.out.println("Criado com sucesso!");
				return true;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteUsuario(String cpf, String senha){
		try {
			Connection con = new Banco().getConexao();
			String query = "DELETE FROM usuario WHERE cpf_login=? AND senha=?";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, cpf);
			pstm.setString(2, senha);
			int num = pstm.executeUpdate();
			pstm.close();
			con.close();
			if(num == 0){
				return false;
			}else{
				return true;	
			}
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateUsuario(String cpf, String senha, String nSenha){
		try {
			Connection con = new Banco().getConexao();
			String query = "UPDATE usuario SET senha=? WHERE cpf_login=? AND senha=?";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, nSenha);
			pstm.setString(2, cpf);
			pstm.setString(3, senha);
			int num = pstm.executeUpdate();
			pstm.close();
			con.close();
			if(num == 0){
				return false;
			}else{
				return true;	
			}
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public List<String> listarUsuario(){
		List<String> lista = new ArrayList<String>();
		try {
			Connection con = new Banco().getConexao();
			String query = "SELECT cpf_login FROM usuario";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()){
				lista.add(rs.getString("cpf_login"));
			}
			rs.close();
			stm.close();
			con.close();
			return lista;
		} catch (SQLException e){
			e.printStackTrace();
			return lista;
		}
	}
	
	public Usuario getUsuario(String cpf){
		try {
			Connection con = new Banco().getConexao();
			String query = "SELECT * FROM usuario WHERE cpf_login=?";
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, cpf);
			ResultSet rs = pstm.executeQuery();
			Usuario usuario = new Usuario();
			while(rs.next()){
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpfLogin(rs.getString("cpf_login"));
			}
			rs.close();
			pstm.close();
			con.close();
			return usuario;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}