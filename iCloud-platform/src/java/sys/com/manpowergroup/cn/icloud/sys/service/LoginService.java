package com.manpowergroup.cn.icloud.sys.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.manpowergroup.cn.icloud.sys.entity.Resource;
import com.manpowergroup.cn.icloud.sys.entity.User;

public interface LoginService {

	User getUserByUsernameAndPassword(String staffName, String password);

	Map<String, Set<String>> getUserResource(Long id);

	List<Resource> findAllResource();

}
