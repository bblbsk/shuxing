package com.x.elm;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
  * @ClassName: LogConsoleDemo
  * @Description: TODO
  * @author-csx
  * @date 2018��4��9�� ����8:14:20
  *
  *	ͳ��ÿһ��ÿһ��IP��״̬������˶��ٴ�
  *192.168.1.3,404,2017-2-21 10:10
  *192.168.1.3,404,2017-2-21 10:10
  *192.168.1.3,404,2017-2-21 10:10
  *192.168.1.3,200,2017-2-21 10:10
  *192.168.1.3,404,2017-2-21 10:10
  *192.168.1.3,200,2017-2-21 10:30
  *192.168.1.3,404,2017-2-22 10:10
  *192.168.1.3,404,2017-2-22 10:10
  *192.168.1.3,404,2017-2-22 10:10
  *
  */
public class LogConsoleDemo {
	
	// ��������
	static Map<String, Integer> counts = new HashMap<String, Integer>();

	public static void main(String[] args) throws IOException {
		
		// �����ļ�
		BufferedReader reader = Files.newBufferedReader(Paths.get("C:\\Users\\csx\\Desktop\\log.txt"), StandardCharsets.UTF_8);
		String row = null;
		while ((row = reader.readLine()) != null) {
			// ����
			parse(row);
		}
		// ���
		Set<Entry<String, Integer>> result = counts.entrySet();
		for (Entry<String, Integer> entry : result) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	
	/**
	  *
	  * @Title: parse
	  * @Description: ����ÿһ������
	  * 	�ٷָ�ÿһ�� 192.168.1.3,404,2017-2-21 10:10
	  * 	�ڻ�ȡkey
	  * 	���ж��Ƿ���ڣ������ڳ�ʼ�������ڼ�1
	  * @param data    ÿһ������
	  * @return void    ��������
	  * @throws
	  */
	public static void parse(String data){
		String[] items = data.split(",");
		String key = new StringBuilder().
							append(items[0]).append("-").				// 192.168.1.3-
							append(items[1]).append("-").				// 404-
							append(items[2].substring(0, 9)).toString();//2017-2-21 10:10
		if (counts.containsKey(key)) {
			Integer count = counts.get(key);
			counts.put(key, ++count);
		} else {
			counts.put(key, 1);
		}
	}
	
	
}
