package br.com.tecnisys.core.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

public class PasswordUtils {

	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);

	public PasswordUtils() {
	}

	/**
	 * Gera um hash utilizando o BCrypt.
	 * 
	 * @param senha
	 * @return String
	 */
	public static String gerarHashMD5(String senha) {
		if (senha == null) {
			return senha;
		}

		String md5Hex = DigestUtils.md5DigestAsHex(senha.getBytes());
		
		log.info("Gerando hash com o MD5.");

		return md5Hex;
	}

}