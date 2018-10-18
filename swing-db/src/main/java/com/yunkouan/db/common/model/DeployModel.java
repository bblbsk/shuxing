package com.yunkouan.db.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @Description: 部署Model
 * @author-shaoguoli
 * @date 2017年3月15日 下午5:28:35
 */
@XmlRootElement(name = "DeployModel")
public class DeployModel {
	
	private String choiceModel; 		//功能模型（部署/升级）
	private String choiceServer;		//选择服务器类型（上位机/海关WEB/国检WEB）
	private String projectName;			//项目名: etc: 天津华宇
	private String lineNumber;			//线体序号(A/B/C/D)
	private String choiceServerIp;  	//选择服务地址
	private Integer choiceHttpPort;  	//tomcat http端口号 相当于8080
	private Integer choiceShutdownPort; //tomcat 关闭服务端口号 相当于8005
	private Integer choiceAJPPort;  	//tomcat ajp连接端口号  相当于8009
	private String dbPassword;  		//数据库密码
	private String tomcatPath;			//tomcat安装路径
	private String warFileSignature;	//上传文件签名
	private String warFilePath;         //持久化到本地war文件全路径
	private String tomcatTitle;			//tomcat服务标题
	private String projectTitle;		//项目登录展示名：etc:天津华宇在线查验系统
	
	
	public DeployModel() {
		super();
	}

	public String getChoiceModel() {
		return choiceModel;
	}

	public void setChoiceModel(String choiceModel) {
		this.choiceModel = choiceModel;
	}

	public String getChoiceServer() {
		return choiceServer;
	}

	public void setChoiceServer(String choiceServer) {
		this.choiceServer = choiceServer;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getChoiceServerIp() {
		return choiceServerIp;
	}

	public void setChoiceServerIp(String choiceServerIp) {
		this.choiceServerIp = choiceServerIp;
	}

	public Integer getChoiceHttpPort() {
		return choiceHttpPort;
	}

	public void setChoiceHttpPort(Integer choiceHttpPort) {
		this.choiceHttpPort = choiceHttpPort;
	}

	public Integer getChoiceShutdownPort() {
		return choiceShutdownPort;
	}

	public void setChoiceShutdownPort(Integer choiceShutdownPort) {
		this.choiceShutdownPort = choiceShutdownPort;
	}

	public Integer getChoiceAJPPort() {
		return choiceAJPPort;
	}

	public void setChoiceAJPPort(Integer choiceAJPPort) {
		this.choiceAJPPort = choiceAJPPort;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getTomcatPath() {
		return tomcatPath;
	}

	public void setTomcatPath(String tomcatPath) {
		this.tomcatPath = tomcatPath;
	}

	public String getWarFileSignature() {
		return warFileSignature;
	}

	public void setWarFileSignature(String warFileSignature) {
		this.warFileSignature = warFileSignature;
	}

	public String getWarFilePath() {
		return warFilePath;
	}

	public void setWarFilePath(String warFilePath) {
		this.warFilePath = warFilePath;
	}
	
	public String getTomcatTitle() {
		return tomcatTitle;
	}

	public void setTomcatTitle(String tomcatTitle) {
		this.tomcatTitle = tomcatTitle;
	}
	
	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	@Override
	public String toString() {
		return "DeployModel [choiceModel=" + choiceModel + ", choiceServer="
				+ choiceServer + ", projectName=" + projectName
				+ ", lineNumber=" + lineNumber + ", choiceServerIp="
				+ choiceServerIp + ", choiceHttpPort=" + choiceHttpPort
				+ ", choiceShutdownPort=" + choiceShutdownPort
				+ ", choiceAJPPort=" + choiceAJPPort + ", dbPassword="
				+ dbPassword + ", tomcatPath=" + tomcatPath
				+ ", warFileSignature=" + warFileSignature + ", warFilePath="
				+ warFilePath + ", tomcatTitle=" + tomcatTitle
				+ ", projectTitle=" + projectTitle + "]";
	}

}
