package com.manpowergroup.cn.icloud.sys.service;

import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.sys.entity.User;


public interface UserService {

	Page<User> queryUserByCondition(Map<String, Object> parameters,Page<User> page);
	
	boolean checkLoginNameValidity(String staffName);
	
	User queryUserById(Long id) throws Exception;

	Result save(User user,Long currOperatorId) throws Exception;

	List<User> findAll();

	void saveUserResource(Long userId, String resourceIds);

	void updateStatus(User user, Long currOperatorId);

	Result savePassword(User user, Long currOperatorId) throws Exception;
	
    Result delete(Long id);
	
	List<User> queryUserByOrganId(Long id);

	boolean checkLoginPasswordValidity(Long id, String password) throws Exception;
	
	User queryOrgNameByUserId(Long id) throws Exception;

}
