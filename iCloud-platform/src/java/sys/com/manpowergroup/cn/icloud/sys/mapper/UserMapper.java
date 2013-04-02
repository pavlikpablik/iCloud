package com.manpowergroup.cn.icloud.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepository;
import com.manpowergroup.cn.icloud.sys.entity.Resource;
import com.manpowergroup.cn.icloud.sys.entity.User;
import com.manpowergroup.cn.icloud.sys.entity.UserOrgan;
import com.manpowergroup.cn.icloud.sys.entity.UserResource;
@MyBatisRepository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    void insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
    
    public User selectUserByLoginName(String username);


    
	List<Resource> selectUserResources(Long id);
	
    User selectByNameAndPassword(Map<String, Object> param);
    
    User get(Long id);
    
    User queryOrgNameByUserId(Long id);
    
    List<User> query(Map<String, Object> parameters, RowBounds rowBounds);
    
    List<User> query(Map<String, Object> parameters);

	void update(User user);

	void delete(Long id);

	long count(Map<String, Object> parameters);
	
	List<User> findAll();

	void deleteUserResource(Long userId);

	void batchInsertUserResources(List<UserResource> list);
	
	void deleteUserOrgan(Long userId);
	
	void batchInsertUserOrgan(List<UserOrgan> list);

	void deleteUser(Long id);
	
	void updateStatus(User user);

	String queryPasswordById(Long id);

	void updatePassword(User user);

	List<User> queryUserByOrganId(Long id);
	
	
}