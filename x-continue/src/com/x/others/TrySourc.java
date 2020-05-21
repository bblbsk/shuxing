package com.x.others;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TrySourc{

	public static void main(String[] args) {
		try(
				BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\csx\\Desktop\\45.sql"));
			){
			while(true){
				String value = br.readLine();
				if ("".equals(value) || value == null) {
					break;
				}
				System.out.println(value);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			System.out.println("end.......");
		}
	}
}
