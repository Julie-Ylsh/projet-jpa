package BanqueService;

import java.util.Scanner;

import DAO.BanqueDAO;

public abstract class BanqueService {
	public abstract void executeUC(Scanner scanner, BanqueDAO dao);
}
