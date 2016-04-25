package br.com.tcc.utils;

import java.util.Calendar;
import java.util.Random;

public class Utilitario {
	
	public String gerarRandomMat(){
		char[] chart = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		char[] matricula = new char[4];

		int chartLenght = chart.length;
		Random rdm = new Random();

		for (int x = 0; x < 2; x++) {
			matricula[x] = chart[rdm.nextInt(chartLenght)];
		}
		
		Calendar data = Calendar.getInstance();
		Integer ano = data.get(Calendar.YEAR);
		String anoAtual = ano.toString();
		
		String str = anoAtual + new String(matricula);

		return new String(str);
	}
	
	
	public String gerarRandomMatAluno(){
		char[] chart = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		char[] matricula = new char[4];

		int chartLenght = chart.length;
		Random rdm = new Random();

		for (int x = 0; x < 2; x++) {
			matricula[x] = chart[rdm.nextInt(chartLenght)];
		}
		
		Calendar data = Calendar.getInstance();
		Integer ano = data.get(Calendar.YEAR);
		String anoAtual = ano.toString();
		
		String str = anoAtual + new String(matricula);

		return new String(str);
	}
	
	public String gerarNomeTurma() {
		for (int i = 65; i <= 90; i++) {  
		    char ch = (char) i;  
		    System.out.println(i + "  " + ch);  
		}
		return null;
	}

}
