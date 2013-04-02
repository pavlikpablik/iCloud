package com.manpowergroup.cn.icloud.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.DESPlus;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.IdcardInfoExtractor;
import com.manpowergroup.cn.core.utils.IdcardValidator;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.icloud.sys.entity.User;
import com.manpowergroup.cn.icloud.sys.entity.UserOrgan;
import com.manpowergroup.cn.icloud.sys.entity.UserResource;
import com.manpowergroup.cn.icloud.sys.entity.UserRole;
import com.manpowergroup.cn.icloud.sys.mapper.BranchMapper;
import com.manpowergroup.cn.icloud.sys.mapper.RoleMapper;
import com.manpowergroup.cn.icloud.sys.mapper.UserMapper;
import com.manpowergroup.cn.icloud.sys.service.UserService;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private RoleMapper roleMapper;

	@Transactional(readOnly=true)
	/**
	 * 分页查询员工信息
	 */
	public Page<User> queryUserByCondition(Map<String, Object> parameters,Page<User> page) {
		List<User> Users = userMapper.query(parameters,ICloudDataUtil.getRowBounds(page));
		page.setTotalCount(userMapper.count(parameters));
		page.setResult(Users);
		return page;
	}
	
	/**
	 * 验证信息是否重复
	 */
	@Override
	public boolean checkLoginNameValidity(String username) {
	    User user = userMapper.selectUserByLoginName(username);
		
		return user==null;
	}
	


	/**
	 * 查询员工信息。
	 * @throws Exception 
	 */
	@Transactional(readOnly=true)
	public User queryUserById(Long id) throws Exception {
		User user = userMapper.get(id);
		user.setPassword(DESPlus.decrypt(user.getPassword()));
		return user;
	}
	
	/**
	 * 根据Id查询组织架构信息
	 * @throws Exception 
	 */
	@Transactional(readOnly=true)
	public User queryOrgNameByUserId(Long id) throws Exception{
		User user=userMapper.queryOrgNameByUserId(id);
		return user;
	}

	/**
	 * 保存员工信息
	 */
	@Override
	public Result save(User user,Long currOperatorId) throws Exception {
		Result result = new  Result();
		
	    
	    if(StringUtils.isBlank(user.getLoginName())){
	        result.setResult(false);
	        result.setResultText("用户名不能为空。");
	        return result;
	    }
	    if(StringUtils.isBlank(user.getPassword())){
            result.setResult(false);
            result.setResultText("密码不能为空。");
            return result;
        }
	    if(StringUtils.isBlank(user.getSurePassword())){
            result.setResult(false);
            result.setResultText("确认密码不能为空。");
            return result;
        }
	    if(user.getId()==null&& StringUtils.isNotBlank(user.getLoginName())
	    		&&userMapper.selectUserByLoginName(user.getLoginName())!=null){
            result.setResult(false);
            result.setResultText("该用户名已经被注册。");
            return result;
        }
	    if(!user.getPassword().equals(user.getSurePassword())){
            result.setResult(false);
            result.setResultText("两次输入的密码不相同。");
            return result;
        }
       if(StringUtils.isNotBlank(user.getIdcard())&&!IdcardValidator.isValidate18Idcard(user.getIdcard())){
            result.setResult(false);
            result.setResultText("请输入正确的身份证号码,不支持15位");
            return result;
        }
       
       //可以保存了
       if(StringUtils.isNotBlank(user.getIdcard())){
       IdcardInfoExtractor idcard = new IdcardInfoExtractor(user.getIdcard());
           user.setBirthday(idcard.getBirthdayStr());
           user.setSex(idcard.getSex());
       }
       user.setEnabled(User.ENABLED.ENABLED.getValue());
       user.setIssys(User.IS_SYS.IS_NOT_SYS.getValue());
       
       user.setStatus(0);
       user.setPassword(DESPlus.encrypt(user.getPassword()));
       result.setResult(true);
       result.setResultText("保存成功");
       if(user.getId()==null)
       {
    	   user.setCreateDate(user.getCreateDate());
    	   user.setModifyDate(user.getModifyDate());
    	   userMapper.insert(user);
       }else{
    	   user.setModifyDate(user.getModifyDate());
    	   List<Integer> ids = user.getIds();
    	   roleMapper.deleteRoleByUserId(user.getId());
    	   if(ids != null && ids.size() > 0)
    	   {
    		   List<UserRole> list = Lists.newArrayList();
    		   for(int i = 0; i < ids.size(); i++)
    		   {
    			   UserRole newUserRole = new UserRole();
    			   newUserRole.setRoleId(Long.valueOf(ids.get(i)));
    			   newUserRole.setUserId(user.getId());
    			   list.add(newUserRole);
    		   }
    	       roleMapper.batchInsertUserRole(list);
    	   }
    	   userMapper.updateByPrimaryKey(user);
       }
       //更新中间表
		List<UserOrgan> list = Lists.newArrayList();
		if (StringUtils.isNotBlank(user.getOrgId())) {
			String[] array = StringUtils.split(user.getOrgId(),",");
			
			for (String r : array) {
				if (StringUtils.isBlank(r)) {
					continue;
				}
				UserOrgan ur = new UserOrgan();
				ur.setOrganId(Long.valueOf(r));
				ur.setUserId(user.getId());
				list.add(ur);
			}
		}
		userMapper.deleteUserOrgan(user.getId());
		if (list!=null && list.size()>0) {
			userMapper.batchInsertUserOrgan(list);
		}
       
       
	   return result;
	}

	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public void saveUserResource(Long userId, String resourceIds) {
		
		if (userId==null) {
			throw new RuntimeException("用户信息不存在，请重试");
		}
		
		List<UserResource> list = Lists.newArrayList();
		if (StringUtils.isNotBlank(resourceIds)) {
			String[] array = StringUtils.split(resourceIds,",");
			
			for (String r : array) {
				if (StringUtils.isBlank(r)) {
					continue;
				}
				UserResource ur = new UserResource();
				ur.setResourceId(Long.valueOf(r));
				ur.setUserId(userId);
				list.add(ur);
			}
		}
		userMapper.deleteUserResource(userId);
		if (list!=null && list.size()>0) {
			userMapper.batchInsertUserResources(list);
		}
	}

	public Result delete(Long id) {
		Result result = new  Result();
		if(id != null)
		{
			userMapper.deleteUser(id);
		}
		result.setResult(true);
	    result.setResultText("删除成功");
		return result;
	}
	
	
	
	public List<User> queryUserByOrganId(Long id){
		return userMapper.queryUserByOrganId(id);
	}
	
	public void updateStatus(User user,Long currOperatorId) {
		userMapper.updateStatus(user);
	}

	public Result savePassword(User user, Long currOperatorId) throws Exception {
        user.setPassword(DESPlus.encrypt(user.getPassword()));
        userMapper.updatePassword(user);
		return ICloudDataUtil.getResult("密码更新成功", true);
	}

	public boolean checkLoginPasswordValidity(Long id, String password) throws Exception {
		Boolean flag = true;
		if(!DESPlus.encrypt(password).equals(userMapper.queryPasswordById(id)))
		{
			flag = false;
		}
		return flag;
	}
}
