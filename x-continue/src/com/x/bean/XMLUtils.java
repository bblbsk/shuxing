package com.x.bean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <P>Title: yka-edi</P>
 * <P>Description: 操作XML数据的工具类</P>
 * <P>Copyright: Copyright(c) 2014</P>
 * <P>Company: yunkouan.com</P>
 * @author shaoguoli
 * @version 1.0
*/
public class XMLUtils {
	
	private Logger logger = LoggerFactory.getLogger(XMLUtils.class);
	
	/**
	 * @param <T>
	 * @brief 对象转xml 用于回传
	 * @param exchangeTableEntity
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException 
	 */
	public <T> InputStream Obj2Xml(T t) throws FileNotFoundException, UnsupportedEncodingException  {
		JAXBContext ctx;
		try {
			StringWriter writer = new StringWriter();
			ctx = JAXBContext.newInstance(t.getClass());
			
			Marshaller marshaller = ctx.createMarshaller();			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 格式化输出
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 设置输出编码,默认为UTF-8
//			OutputStream os = new FileOutputStream( Constant.UNIX_SEPARATOR + Constant.NO_DEAL_DIRECTORY + "/persons.xml" );  
			marshaller.marshal(t, writer);
			return new ByteArrayInputStream(writer.toString().getBytes("UTF-8"));  
		} catch (JAXBException e) {
			logger.error("JAXBException:",e);
		}
		return null;
	}
	
	/**
	 * @brief xml转对象
	 */
	public <T> T XmlToObj(InputStream is, Class<T> valueType) {
		try {	
			JAXBContext jaxbContext = JAXBContext.newInstance(valueType);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (T)unmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			logger.error("JAXBException:",e);
		}
		return null;
	}
	
    private File getFile(String dir, String fileName){
    	File xrayFile = new File(dir, fileName);
    	if(!xrayFile.exists())
			try {
				xrayFile.createNewFile();
			} catch (IOException e) {
				logger.error("JAXBException:",e);
			}
    	return xrayFile;
    }
    
}
