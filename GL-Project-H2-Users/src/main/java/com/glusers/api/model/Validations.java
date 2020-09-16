package com.glusers.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Validations {
	private String textVal;
	private boolean result;
	public String usrname;
	
	public Validations() {
		
	}
	/*validaEmail revisa que email este bien formado y a la vez obtiene 
	 * un username, que va como ultimo elemento del resultado lista */
	public List<Object> validaEmail(String text) {
		result = false;
		textVal="";
		usrname="";
		List<Object> resultList= new ArrayList<Object>();
		if(text.contains("@")&& text.contains(".")) {
			StringTokenizer tokens=new StringTokenizer(text, "@");
			if(tokens.countTokens()==2) {
				String txt1 = tokens.nextToken();
				String txt2 = tokens.nextToken();
				usrname = txt1+txt2;
				if(txt2.contains(".")) {
					StringTokenizer tk = new StringTokenizer(txt2, ".");
					
					if(tk.countTokens()==2) {
						String txtleft = tk.nextToken();
						String txtright = tk.nextToken();
						if(txtright.length()==2 || txtright.length()==3) {
							result = true;
							textVal= "email tiene la forma correcta";
							resultList.add(result);
							resultList.add(textVal);
						}
					}
					else {
						textVal = "email mal formado, tiene mas de un punto en dominio";
						result = false;
						resultList.add(result);
						resultList.add(textVal);
					}
					
				}
				else {
					textVal = "email esta mal formado, no contiene informacion de dominio";
					result = false;
					resultList.add(result);
					resultList.add(textVal);
				}
				
				
			}
			else {
				textVal ="email esta mal formado, tiene mas de una @";
				result = false;
				resultList.add(result);
				resultList.add(textVal);
			}
			
		}
		else {
			textVal = "email no contiene @ ni punto";
			result =  false;
			resultList.add(result);
			resultList.add(textVal);
		}
		resultList.add(usrname);
		return resultList;
	}
	
	public List<Object> validaPass(String text) {
		result = false;
		textVal= "";
		List<Object> resultList= new ArrayList<Object>();
		char[] evText = text.toCharArray();
		int mayusculas=0;
		int numeros = 0;
		int minusculas = 0;
		if(text.length()<4) {
			textVal= "error tamaño menor que minimo permitido";
			result= false;
			resultList.add(result);
			resultList.add(textVal);
			
		}
		else {
			for(int i=0; i<evText.length;i++) {
				if(Character.isDigit(evText[i])) {
					numeros++;
				}
				else {
					if(Character.isLowerCase(evText[i])) {
						minusculas++;
					}
					if(Character.isUpperCase(evText[i])) {
						mayusculas++;
					}
				}
			}
			System.out.println("Numeros: "+numeros+", mayusculas : "+mayusculas+", minusculas : "+minusculas);
			if(numeros==2 && mayusculas==1 && minusculas==(evText.length-3)) {
				result = true;
				textVal="Password bien formada";
				resultList.add(result);
				resultList.add(textVal);
			}
			else {
				result=false;
				resultList.add(result);
				if (numeros >2) {
					textVal = "error tu clave contiene mas de dos numeros";
					
					
					resultList.add(textVal);
				}
				else {
					textVal = "error tu clave contiene menos de dos numeros";
					
					
					resultList.add(textVal);
				}
				if(mayusculas <1) {
					textVal = "error tu clave no contiene una letra mayúscula";
					
					
					resultList.add(textVal);
				}
				else {
					textVal = "error tu clave tiene mas de 1 letra mayúscula";
					
					
					resultList.add(textVal);
				}
				if(minusculas<1) {
					textVal = "error tu clave no contiene minúsculas";
					
					
					resultList.add(textVal);
				}
				else {//***********
					int numminimo = evText.length - 3;
					if(minusculas>numminimo) {
						textVal = "error tu clave contiene demasiadas minúsculas";
					}
					else {
						textVal = "error tu clave no contiene suficientes minúsculas";
					}
					
					
					resultList.add(textVal);
				}
			}
		}
		
		return resultList;
	}

}
