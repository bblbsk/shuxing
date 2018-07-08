package com.x.monitor;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileMonitorUtil {
	public static FileAlterationMonitor addMonitor(String path) {
		FileAlterationMonitor monitor = new FileAlterationMonitor(50);// �����
		FileAlterationObserver observer = new FileAlterationObserver(path);// ��ĳ��·���Ĺ۲���
		observer.addListener(new PushFileAlterationListener(path));// ��Ӽ����¼���Ӧ����path�޹أ�rsyncͬ����Ҫ
		monitor.addObserver(observer);// ���۲�����ӵ������
		try {
			monitor.start();// ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monitor;// ����ֹͣ
	}

	public static void main(String[] args) {
		String path = "\\\\172.16.16.119\\Temp";
		FileAlterationMonitor monitor = FileMonitorUtil.addMonitor(path);
		try {
//			Thread.sleep(10000);// ����������ߺ�ֹͣ������monitor����һֱ����
//			monitor.stop();// ֹͣ���
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}