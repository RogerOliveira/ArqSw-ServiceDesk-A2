package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Usuario;

/**
 * 
 * @author roger.oliveira-816117932
 *
 */

@Repository
public class UsuarioDAO {
	private Connection conn;

	@Autowired
	public UsuarioDAO(DataSource dataSource) throws IOException {
		try {

			this.conn = dataSource.getConnection();

		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	public boolean verificarUsuario(Usuario usuario) throws IOException {

		String queryConsulta = "select username, password from fila where username = ? and password = ?";
		try (PreparedStatement pst = conn.prepareStatement(queryConsulta);) {

			pst.setString(1, usuario.getUsername());
			pst.setString(2, usuario.getPassword());
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}

		} catch (SQLException e) {
			throw new IOException(e);
		}

	}

}
