package br.com.tcc.utils;

import java.util.Calendar;
import java.util.Random;

public class Test {
	
	public static void main(String[] args) {
		
		String resul = geraRandomMat();
		
		System.out.println(resul);		
		
	}
	
	public static String geraRandomMat(){
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

}