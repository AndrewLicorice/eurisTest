package com.example.euris.dao;

import com.example.euris.util.BasicDao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.euris.dao.IDaoArithmetic;

@Repository
public class DaoArithmetic extends BasicDao implements IDaoArithmetic {

	public DaoArithmetic(
			@Value("${db.address}") String dbAddress,
	        @Value("${db.user}") String user,
	        @Value("${db.psw}") String password) {
                  super(dbAddress, user, password);	
     }

	
	
	// SUM
	// Questo metodo somma due "cost", utlizza due metodi accessori: psdToPence e penceToPsd
	@Override
	public String sum(String cost1, String cost2) {
		int penceOne = psdToPence(cost1);
		int penceTwo = psdToPence(cost2);
		int sum = penceOne + penceTwo;
		return penceToPsd(sum);
	}

	
	
	// SUBSTRACTION
	// Questo metodo fa la differenza di due "cost", utlizza due metodi accessori: psdToPence e penceToPsd
	@Override
	public String sub(String cost1, String cost2) {
		int penceOne = psdToPence(cost1);
		int penceTwo = psdToPence(cost2);
		int sub = penceOne - penceTwo;
		return penceToPsd(sub);
	}

	
	
	// MULTIPLICATION
	// Questo metodo moltiplica due "cost", utlizza due metodi accessori: psdToPence e penceToPsd
	@Override
	public String mul(String cost, int number) {
		int pence = psdToPence(cost);
		int mul = pence * number;
		return penceToPsd(mul);
	}

	
	
	// DIVISION
	// Questo metodo fa il quoziente tra due "cost", utlizza due metodi accessori: psdToPence e penceToPsd
	@Override
	public String div(String cost, int number) {
		int pence = psdToPence(cost);
		int div = pence/number;
		int rest = pence%number;
		return penceToPsd(div) + " (" + penceToPsd(rest) + ")";
	}

	
	
	
	
	// HELPERS
	
	// FROM PENCE TO PSD
	// Questo metodo trasforma il parametro  che arriva in formato "pence" in formato "psd"
	private String penceToPsd(int totPence) {
		String res = "";
		int pence = totPence%12;
		int shillingTemp = totPence/12;
		int shilling = shillingTemp%20;
		int pound = shillingTemp/20;
		res = pound + "p " + shilling + "s " + pence + "d";
		return res;
	
}

	
	// FROM PSD TO PENCE
	//  Questo metodo trasforma il parametro  che arriva in formato "psd" in formato "pence"
	static private int psdToPence(String cost) {
		int res = 0;
		String psd[] = cost.split(" ");
		String now;
		for (int i = 0; i < psd.length; i++) {
			 now = "";
			 for (int j = 0; j < psd[i].length(); j++) {
				char ch = psd[i].charAt(j);
				  if (Character.isDigit(ch)) {
					now += ch;
				  } else {
					switch (ch) {
					case 'p':
						res += 240 * Integer.parseInt(now);
						break;
					case 's':
						res += 12 * Integer.parseInt(now);
						break;
					case 'd':
						res += Integer.parseInt(now);
						break;	
					}
				}
			}
		}
		return res;
	}
	
	
	
	
	
	
	
	
}