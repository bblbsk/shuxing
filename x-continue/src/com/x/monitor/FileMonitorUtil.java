package com.x.monitor;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileMonitorUtil {
	public static FileAlterationMonitor addMonitor(String path) {
		FileAlterationMonitor monitor = new FileAlterationMonitor(50);// 监控器
		FileAlterationObserver observer = new FileAlterationObserver(path);// 对某个路径的观察者
		observer.addListener(new PushFileAlterationListener(path));// 添加监听事件响应，与path无关，rsync同步需要
		monitor.addObserver(observer);// 将观察者添加到监控器
		try {
			monitor.start();// 启动
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monitor;// 便于停止
	}

	public static void main(String[] args) {
		String path = "\\\\172.16.16.119\\Temp";
		FileAlterationMonitor monitor = FileMonitorUtil.addMonitor(path);
		try {
//			Thread.sleep(10000);// 如果不加休眠和停止操作，monitor将会一直监听
//			monitor.stop();// 停止监控
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}