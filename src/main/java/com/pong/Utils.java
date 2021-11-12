package com.pong;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class Utils {
  public static boolean isConvertedAllDocxToPDF(String folderpath, String scriptPath, long currentFolder){
    log.info("removed synchonized");
    log.info("folderpath: " + folderpath + " | scriptPath: " + scriptPath + " | currentFolder:" + currentFolder);
    try {
      if (!StringUtils.isBlank(folderpath)) {
        log.info("convertAllFolderDocxToPdf start convertAllFolderDocxToPdf folderpath {}", folderpath);
        String command = String.format(
            "powershell.exe -ExecutionPolicy Unrestricted  -NoLogo -NonInteractive -NoProfile -WindowStyle Hidden -File %s -folderpath %s",
            scriptPath, folderpath);
        log.info("Execute command: {}", command);
        log.info("convertAllFolderDocxToPdf build command result {}", command.toString());
        Runtime runtime = Runtime.getRuntime();
        Process powerShellProcess = runtime.exec(command);

        if(!powerShellProcess.waitFor(5, TimeUnit.MINUTES)) {
          //timeout - kill the process.
          log.info("powerShellProcess running command {}", powerShellProcess.exitValue());
          powerShellProcess.destroy();
          return false;
        }

        powerShellProcess.getOutputStream().close();
        String line;
        BufferedReader stdout = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
        while ((line = stdout.readLine()) != null) {
          log.info("convertAllFolderDocxToPdf stdout each line {}", line);
        }
        stdout.close();
        BufferedReader stderr = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
        while ((line = stderr.readLine()) != null) {
          log.info("convertAllFolderDocxToPdf stderr each line {}", line);
        }
        stderr.close();
        return true;
      } else {
        log.error(".......... convertAllFolderDocxToPdf invalid exception folderpath {}", folderpath);
      }
    } catch (Exception e) {

      log.error(".......... convertAllFolderDocxToPdf exeption {}, stack {}", e.getMessage(),
          Arrays.toString(e.getStackTrace()));
      e.printStackTrace();
    }
    return false;
  }
}
