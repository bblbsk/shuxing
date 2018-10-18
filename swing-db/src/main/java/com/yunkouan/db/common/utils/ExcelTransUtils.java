package com.yunkouan.db.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.yunkouan.db.common.model.EdiHeaderModel;
import com.yunkouan.db.common.utils.Constant.EdiParams;

/**
 * @ClassName: ExcelTransUtils
 * @Description: Excel转换为model的工具
 * @author-csx
 * @date 2017年5月4日 下午2:26:15
 */
public class ExcelTransUtils {

	public static List<EdiHeaderModel> fileToModel(File excel) {
		List<EdiHeaderModel> models = new ArrayList<EdiHeaderModel>();
		try {
			Workbook book = Workbook.getWorkbook(excel);
			//获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			//逐行获取数据转换为model
			for (int i = 0; i < sheet.getRows(); i++) {
				EdiHeaderModel model = new EdiHeaderModel();
				//第一行为Titie，不进行转换
				if(i == 0){
					continue;
				}
				Cell[] cells = sheet.getRow(i);
				model.setBarcode_no(cells[0].getContents());
				model.setEntry_id(cells[1].getContents());
				model.setOrder_no(cells[2].getContents());
				model.setMain_g_name(cells[3].getContents());
				model.setI_e_flag(EdiParams.IPORT.equals(cells[4].getContents()) ? EdiParams.IPORT : EdiParams.EPORT);
				model.setDec_Type(EdiParams.DEC_CHINESE.equals(cells[5].getContents()) ? EdiParams.DEC : EdiParams.NDEC);
				model.setGross_wt(Double.valueOf(cells[6].getContents()));
				model.setLogistics_name(cells[7].getContents());
				model.setSender_name(cells[8].getContents());
				model.setOwner_name(cells[9].getContents());
				model.setOwner_address(cells[10].getContents());
				model.setPack_no(Double.valueOf(cells[11].getContents()));
				//添加
				models.add(model);
			}
			book.close();
		} catch (BiffException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return models;
	}

}
