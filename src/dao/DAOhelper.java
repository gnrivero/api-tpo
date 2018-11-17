package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class DAOhelper {
	
	private static DateFormat year_month_day_time;
	private static DateFormat day_month_year_time;
	
	public static DateFormat getAnioMesDiaHoraDateFormat(){
		if (year_month_day_time == null)
			year_month_day_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return year_month_day_time; 
	}
	
	public static DateFormat getDiaMesAnioDateFormat(){
		if (day_month_year_time == null)
			day_month_year_time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		return day_month_year_time; 
	}
	
	public static <E> String escribirSentenciaIn(List<E> listaDeObjetos){
		
		StringBuilder sentenciaIn = new StringBuilder(" IN (");
		
		if (listaDeObjetos.get(0) instanceof String){
			for (Object elemento : listaDeObjetos){
				sentenciaIn.append("'").append(elemento).append("'").append(",");					
			}
		}else if (listaDeObjetos.get(0) instanceof Integer){
			for (Object elemento : listaDeObjetos){
				sentenciaIn.append(elemento).append(",");			
			}
		}else{
			throw new RuntimeException("Unsupported Type");
		}
		sentenciaIn.replace(sentenciaIn.toString().length() - 1 , sentenciaIn.toString().length(), "");
		sentenciaIn.append(")");
		
		return sentenciaIn.toString();
	}	
}