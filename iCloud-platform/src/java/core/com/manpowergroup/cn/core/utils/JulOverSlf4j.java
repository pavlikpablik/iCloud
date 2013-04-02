package com.manpowergroup.cn.core.utils;

import javax.annotation.PostConstruct;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class JulOverSlf4j
{

   //Spring在所有属性注入后自动执行的函数.
   @PostConstruct
   public void init()
   {
      SLF4JBridgeHandler.install();
   }
}
