package sisFrases.Testes;

import sisFrases.DAO.FraseDAO;

public class RetornaPost {

	public static void main(String[] args) {
		FraseDAO fraseDao = new FraseDAO();
		System.out.println(fraseDao.buscaPostString(1));
	}

}
