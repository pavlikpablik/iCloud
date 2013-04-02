package com.manpowergroup.cn.icloud.sys.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.core.utils.Collections3;
import com.manpowergroup.cn.icloud.sys.entity.Resource;
import com.manpowergroup.cn.icloud.sys.entity.User;
import com.manpowergroup.cn.icloud.sys.mapper.ResourceMapper;
import com.manpowergroup.cn.icloud.sys.mapper.UserMapper;
import com.manpowergroup.cn.icloud.sys.service.LoginService;

@Service
@Transactional(readOnly=true)
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", username);
		param.put("password", password);
		
		return userMapper.selectByNameAndPassword(param);
	}

	@Override
	public Map<String, Set<String>> getUserResource(Long id) {
		
		
		List<Resource> resources = userMapper.selectUserResources(id);
		Set<String> actions = new HashSet<String>();;
		Set<String> roles =new HashSet<String>();;
		if (Collections3.isNotEmpty(resources)) {
			
			for (Resource resource : resources) {
				if (StringUtils.isNotBlank(resource.getUrl())) {
					actions.add(resource.getUrl());
				}
				if (StringUtils.isNotBlank(resource.getName())) {
					roles.add(resource.getName());
				}
			
			}
		}
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		map.put(ICloudConst.USER_ACTIONS, actions);
		map.put(ICloudConst.USER_ROLES, roles);
		return map;
	}

	@Override
	public List<Resource> findAllResource() {
		return resourceMapper.selectAll();
	}

}
