package br.com.caelum.livraria.bean;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Teste {
	public static void main(String[] args) {
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Date date = new Date();
		 Date a = new Date(dateFormat.format(date));
		 a.setDate(a.getDate() + 20);
		 Calendar c = Calendar.getInstance();
		 // c.setTime(objDetalhes.getPrazoatendimento());
		 c.add(Calendar.DATE, +20);
		 a = (Date) c.getTime();
		 System.out.println( new java.sql.Timestamp(a.getTime()));
	}
}
