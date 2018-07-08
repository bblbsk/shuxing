package com.x.monitor;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class PushFileAlterationListener implements FileAlterationListener{  
    private String rootpath = "";  
    public PushFileAlterationListener(String rootpath) {  
        this.rootpath = rootpath;  
    }  
    @Override  
    public void onDirectoryChange(File file) {  
    }  

    @Override  
    public void onDirectoryCreate(File file) {  
        System.out.println("Directory create: " + file.getAbsolutePath()+"  "+file.getParent());  
    }  

    @Override  
    public void onDirectoryDelete(File file) {  
        System.out.println("Directory delete: " + file.getAbsolutePath()+"  "+file.getParent());  
    }  

    @Override  
    public void onFileChange(File file) {  
        System.out.println("File change: " + file.getAbsolutePath()+"  "+file.getParent());  
    }  

    @Override  
    public void onFileCreate(File file) {  
        System.out.println("File created: " + file.getAbsolutePath()+"  "+file.getParent());  
    }  

    @Override  
    public void onFileDelete(File file) {  
        System.out.println("File deleted: " + file.getAbsolutePath()+"  "+file.getParent());  
    }  

    @Override  
    public void onStart(FileAlterationObserver filealterationobserver) {  
        System.out.println("start");  
    }  

    @Override  
    public void onStop(FileAlterationObserver filealterationobserver) {  
        System.out.println("end");  
    }  
}  