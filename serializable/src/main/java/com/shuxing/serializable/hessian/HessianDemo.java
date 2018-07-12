package com.shuxing.serializable.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class HessianDemo {

	public static void main(String[] args) throws IOException {

		HessionModel zhansan = new HessionModel();
		zhansan.setName("daojia");
		zhansan.setAge(22);

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// Hessian的序列化输出
		HessianOutput ho = new HessianOutput(os);

		ho.writeObject(zhansan);

		byte[] zhansanByte = os.toByteArray();

		ByteArrayInputStream is = new ByteArrayInputStream(zhansanByte);
		// Hessian的反序列化读取对象
		HessianInput hi = new HessianInput(is);
		HessionModel person = (HessionModel) hi.readObject();
		System.out.println("姓名：" + person.getName());
		System.out.println("年龄：" + person.getAge());

	}

}