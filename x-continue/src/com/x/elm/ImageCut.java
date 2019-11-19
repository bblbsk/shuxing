package com.x.elm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageCut {

	public static void main(String[] args) throws Exception {

		long begin = System.currentTimeMillis();
		BufferedImage bufferedImage = ImageIO
				.read(new URL(
						"https://images.djtest.cn/mis/lanling/f1fefa41b11d661508e009edba9de431.png"));
		// BufferedImage bufferedImage = ImageIO.read(new
		// File("C:\\Users\\daojia\\Desktop\\seal.png"));
		BufferedImage image = trimMargin(bufferedImage,
				bufferedImage.getRGB(20, 20));

		File outputfile = new File("C:\\Users\\daojia\\Desktop\\temp.png");
		boolean result = ImageIO.write(image, "png", outputfile);

		System.out.println(result + ", taste:"
				+ (System.currentTimeMillis() - begin));

		// BufferedImage bufferedImage2 = ImageIO.read(new
		// File("C:\\Users\\daojia\\Desktop\\seal.png"));
		//
		// int width = bufferedImage2.getWidth();
		// int height = bufferedImage2.getHeight();
		//
		// for (int i = 0; i < width; i++) {
		//
		// for (int j = 0; j < height; j++) {
		// if (bufferedImage2.getRGB(i, j) == -1) {
		// continue;
		// }
		// System.out.println("x:" + i + ", y:" + j + ", rgb:" +
		// bufferedImage2.getRGB(i, j));
		// }
		// }
	}

	public static BufferedImage trimMargin(BufferedImage im, int rgb) {
		im = trimTopAndBottom(im, rgb, 6, 4);
		im = trimLeftAndRight(im, rgb, 6, 1);
		return im;
	}

	public static BufferedImage trimTopAndBottom(BufferedImage im, int rgb,
			int colorDistThresh, int objThickThresh) {
		boolean bForeground = false;
		int nMarginTop = 0;
		for (int j = 0; j < im.getHeight(); j++) {
			int nObjLen = 0;
			for (int i = 0; i < im.getWidth(); i++) {
				// if ((im.getRGB(i, j) & 0xFFFFFF) != 0xFFFFFF) {
				if (rgbDistance(im.getRGB(i, j), rgb) >= colorDistThresh) {
					nObjLen++;
					if (nObjLen >= objThickThresh) {
						bForeground = true;
						break;
					}
				} else {
					nObjLen = 0;
				}
				// 把背景设为透明
				if (im.getRGB(i, j) == rgb) {
					im.setRGB(i, j, rgb & 0x00ffffff);
				}
			}
			if (bForeground) {
				nMarginTop = j > 20 ? j - 20 : j;
				break;
			}
		}

		if (!bForeground) {
			return im;
		}

		bForeground = false;
		int nMarginBottom = im.getHeight() - 1;
		for (int j = im.getHeight() - 1; j > nMarginTop; j--) {
			int nObjLen = 0;
			for (int i = 0; i < im.getWidth(); i++) {
				// if ((im.getRGB(i, j) & 0xFFFFFF) != 0xFFFFFF) {
				if (rgbDistance(im.getRGB(i, j), rgb) >= colorDistThresh) {
					nObjLen++;
					if (nObjLen >= objThickThresh) {
						bForeground = true;
						break;
					}
				} else {
					nObjLen = 0;
				}
			}
			if (bForeground) {
				nMarginBottom = j + 20 < im.getHeight() ? j + 20 : j;
				break;
			}
		}

		return im.getSubimage(0, nMarginTop, im.getWidth(), nMarginBottom
				- nMarginTop + 1);
	}

	public static BufferedImage trimLeftAndRight(BufferedImage im, int rgb,
			int colorDistThresh, int objThickThresh) {
		boolean bForeground = false;
		int nMarginLeft = 0;
		for (int i = 0; i < im.getWidth(); i++) {
			int nObjLen = 0;
			for (int j = 0; j < im.getHeight(); j++) {
				if (rgbDistance(im.getRGB(i, j), rgb) >= colorDistThresh) {
					nObjLen++;
					if (nObjLen >= objThickThresh) {
						bForeground = true;
						break;
					}
				} else {
					nObjLen = 0;
				}
				// 把背景设为透明
				if (im.getRGB(i, j) == rgb) {
					im.setRGB(i, j, rgb & 0x00ffffff);
				}
			}
			if (bForeground) {
				nMarginLeft = i > 20 ? i - 20 : i;
				break;
			}
		}

		if (!bForeground) {
			return im;
		}

		bForeground = false;
		int nMarginRight = im.getWidth() - 1;
		for (int i = im.getWidth() - 1; i > nMarginLeft; i--) {
			int nObjLen = 0;
			for (int j = 0; j < im.getHeight(); j++) {
				if (rgbDistance(im.getRGB(i, j), rgb) >= colorDistThresh) {
					nObjLen++;
					if (nObjLen >= objThickThresh) {
						bForeground = true;
						break;
					}
				} else {
					nObjLen = 0;
				}
			}
			if (bForeground) {
				nMarginRight = i + 20 < im.getWidth() ? i + 20 : i;
				break;
			}
		}

		return im.getSubimage(nMarginLeft, 0, nMarginRight - nMarginLeft + 1,
				im.getHeight());
	}

	public static int rgbDistance(int rgb1, int rgb2) {
		int r1 = (rgb1 >> 16) & 0xFF;
		int r2 = (rgb2 >> 16) & 0xFF;
		int distR = Math.abs(r1 - r2);
		int g1 = (rgb1 >> 8) & 0xFF;
		int g2 = (rgb2 >> 8) & 0xFF;
		int distG = Math.abs(g1 - g2);
		int b1 = rgb1 & 0xFF;
		int b2 = rgb2 & 0xFF;
		int distB = Math.abs(b1 - b2);
		return Math.max(Math.max(distR, distG), distB);
	}

//	/**
//	 * 设置源图片为背景透明，并设置透明度
//	 * 
//	 * @param srcImage
//	 *            源图片
//	 * @param desFile
//	 *            目标文件
//	 * @param alpha
//	 *            透明度
//	 * @param formatName
//	 *            文件格式
//	 * @throws IOException
//	 */
//	public static void transparentImage(BufferedImage srcImage, String desFile, int alpha, String formatName) throws IOException {
//		int imgHeight = srcImage.getHeight();// 取得图片的长和宽
//		int imgWidth = srcImage.getWidth();
//		int c = srcImage.getRGB(3, 3);
//		// 防止越位
//		if (alpha < 0) {
//			alpha = 0;
//		} else if (alpha > 10) {
//			alpha = 10;
//		}
//		BufferedImage bi = new BufferedImage(imgWidth, imgHeight,
//				BufferedImage.TYPE_4BYTE_ABGR);// 新建一个类型支持透明的BufferedImage
//		for (int i = 0; i < imgWidth; ++i)// 把原图片的内容复制到新的图片，同时把背景设为透明
//		{
//			for (int j = 0; j < imgHeight; ++j) {
//				// 把背景设为透明
//				if (srcImage.getRGB(i, j) == c) {
//					bi.setRGB(i, j, c & 0x00ffffff);
//				}
//				// 设置透明度
//				else {
//					int rgb = bi.getRGB(i, j);
//					rgb = ((alpha * 255 / 10) << 24) | (rgb & 0x00ffffff);
//					bi.setRGB(i, j, rgb);
//				}
//			}
//		}
//		ImageIO.write(bi, StringUtils.isEmpty(formatName) ? FORMAT_PNG : formatName, new File(desFile));
//	}

}
