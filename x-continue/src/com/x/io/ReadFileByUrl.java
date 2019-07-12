package com.x.io;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class ReadFileByUrl {

	public static void main(String[] args) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			BufferedImage image = ImageIO
					.read(new URL(
							"https://images.djtest.cn//mis/lanling/19d62b503e4bf8d06b0880be0cdeff8c.jpg"));
			ImageIO.write(image, "png", baos);
			baos.flush();
			byte[] bytes = baos.toByteArray();
			FileUtils.writeByteArrayToFile(new File(
					"C:\\Users\\daojia\\Desktop\\a.png"), bytes);
		} catch (IOException e) {
		}

		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(
					"http://static.djtest.cn/mis/credit/201903076678808150179_PSR_公共治安风险批量认证模板.xlsx")
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream inputStream = conn.getInputStream();

			int available = inputStream.available();
			byte[] bytes = new byte[available];
			inputStream.read(bytes, 0, available);

			FileUtils.writeByteArrayToFile(new File(
					"C:\\Users\\daojia\\Desktop\\a.xlsx"), bytes);
			System.out.println("ok");
		} catch (IOException e) {
		}
	}
}
