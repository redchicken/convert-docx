package com.pong;

import java.io.File;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestService {
  String filePath;
  String folderPath;

  public TestService(String filePath, String folderPath) {
    this.filePath = filePath;
    this.folderPath = folderPath;
  }

  public void run(){
    log.info("Run with filePath:{}, folderPath:{}",filePath, folderPath);
    Utils.isConvertedAllDocxToPDF(folderPath, folderPath+ File.separator +"convert.ps1",1l);
  }
}
