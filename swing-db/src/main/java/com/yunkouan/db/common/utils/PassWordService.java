package com.yunkouan.db.common.utils;

import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.yunkouan.db.common.model.DeployModel;
import com.yunkouan.db.common.utils.Constant.MysqlParams;
import com.yunkouan.db.common.utils.JaxbUtil.CollectionWrapper;




/**
  * @ClassName: PassWordService
  * @Description: 配置解释文件服务
  * @author-csx
  * @date 2017年5月26日 下午5:26:52
  *
  */
public class PassWordService {


	/**
	  *
	  * @Title: getReadMeInfos
	  * @Description: 读取readme文件获取数据库登录密码
	  * @param @return    设定文件
	  * @return DeployModel    返回类型
	  * @throws
	  */
	public static DeployModel getReadMeInfos() {
		//读取的Readme文件
		File readmeFile = null;
		//获取Readme文件路径
		File readMePath = new File(MysqlParams.MYSQL_DB_PASSWORD_FILE);
		if(!readMePath.exists()){
			return new DeployModel();
		}
		//获取Readme文件路径下所有文件
		File[] readmes = readMePath.listFiles();
		if(readmes != null && readmes.length > 0){
			//取一个就行
			readmeFile = readmes[0];
		}
		//文件不能为空
		if(readmeFile == null){
			return new DeployModel();
		}
		//返回结果
		JaxbUtil resultBinder = new JaxbUtil(DeployModel.class, CollectionWrapper.class); 
		Unmarshaller unmarshaller = resultBinder.createUnmarshaller();
		DeployModel deployModel = null;
		try {
			deployModel = (DeployModel) unmarshaller.unmarshal(readmeFile);
		} catch (JAXBException e) {
			
		}
		return deployModel;
	}
	

}
