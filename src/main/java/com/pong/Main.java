package com.pong;


public class Main {
  public static void main(String[] args) {
    System.out.println("Running");
    if(args.length == 2){
      TestService testService = new TestService(args[0], args[1]);
      testService.run();
    }

  }
}
