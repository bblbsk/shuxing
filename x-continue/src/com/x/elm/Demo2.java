package com.x.elm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.xml.internal.ws.util.StringUtils;

public class Demo2 {

	/**
	 * 设置源图片为背景透明，并设置透明度
	 * 
	 * @param srcFile
	 *            源图片
	 * @param desFile
	 *            目标文件
	 * @param alpha
	 *            透明度
	 * @param formatName
	 *            文件格式
	 * @throws IOException
	 */
	public static void transparentImage(String srcFile, String desFile,
			int alpha, String formatName) throws IOException {
		BufferedImage temp = ImageIO.read(new File(srcFile));// 取得图片
		transparentImage(temp, desFile, alpha, formatName);
	}

	/**
	 * 设置源图片为背景透明，并设置透明度
	 * 
	 * @param srcImage
	 *            源图片
	 * @param desFile
	 *            目标文件
	 * @param alpha
	 *            透明度
	 * @param formatName
	 *            文件格式
	 * @throws IOException
	 */
	public static void transparentImage(BufferedImage srcImage, String desFile,
			int alpha, String formatName) throws IOException {
		int imgHeight = srcImage.getHeight();// 取得图片的长和宽
		int imgWidth = srcImage.getWidth();
		int c = srcImage.getRGB(3, 3);
		// 防止越位
		if (alpha < 0) {
			alpha = 0;
		} else if (alpha > 10) {
			alpha = 10;
		}
		BufferedImage bi = new BufferedImage(imgWidth, imgHeight,
				BufferedImage.TYPE_4BYTE_ABGR);// 新建一个类型支持透明的BufferedImage
		for (int i = 0; i < imgWidth; ++i)// 把原图片的内容复制到新的图片，同时把背景设为透明
		{
			for (int j = 0; j < imgHeight; ++j) {
				// 把背景设为透明
				if (srcImage.getRGB(i, j) == c) {
					bi.setRGB(i, j, c & 0x00ffffff);
				}
				// 设置透明度
				else {
					int rgb = bi.getRGB(i, j);
					rgb = ((alpha * 255 / 10) << 24) | (rgb & 0x00ffffff);
					bi.setRGB(i, j, rgb);
				}
			}
		}
		ImageIO.write(bi, formatName, new File(desFile));
	}
	
	public static void main(String[] args) throws IOException {
		String scr = "C:\\Users\\daojia\\Desktop\\seal.png";
		String des = "C:\\Users\\daojia\\Desktop\\seal2.png";
		transparentImage(scr, des, 80, "png");
		System.out.println("ok");
	}
}
