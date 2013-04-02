package com.manpowergroup.cn.icloud.sys.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.utils.DESPlus;
import com.manpowergroup.cn.core.utils.IdcardInfoExtractor;
import com.manpowergroup.cn.core.utils.IdcardValidator;
import com.manpowergroup.cn.icloud.sys.entity.Branch;
import com.manpowergroup.cn.icloud.sys.entity.User;
import com.manpowergroup.cn.icloud.sys.mapper.BranchMapper;
import com.manpowergroup.cn.icloud.sys.mapper.UserMapper;
import com.manpowergroup.cn.icloud.sys.service.RegisterService;

@Service
@Transactional(rollbackFor=Exception.class)
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BranchMapper branchMapper;
    
    
	/** 注册时校验帐号是否重复
	 * <p> 描述	:  方法的主要功能和使用场合</p>
	 * <p> 备注	:  其他对方法的说明信息</p>
	 * @param whq
	 * @return
	 */
	@Override
	public boolean checkLoginNameValidity(String username) {
	    User user = userMapper.selectUserByLoginName(username);
		
		return user==null;
	}
	
	/** 2013-03-07 add by bob
	 * 通过供应商ID判断用户表中是否存在供应商
	 */
	public boolean checkVendorIdValidity(Long vendorId){
		Branch branch = branchMapper.selectUserByVendorId(vendorId);
		
		return branch==null;
	}
	@Override
	public Result  register(User user,String checkCode,String checkCodeOrg) throws Exception{
	    
	    //
	    Result result = new  Result();
	    
	    if(StringUtils.isBlank(checkCode)){
            result.setResult(false);
            result.setResultText("请输入验证码。");
            return result;
        }
	    if(!checkCode.equals(checkCodeOrg)){
            result.setResult(false);
            result.setResultText("验证码输入错误。");
            return result;
        }
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
	    if(!StringUtils.isBlank(user.getLoginName())&&userMapper.selectUserByLoginName(user.getLoginName())!=null){
            result.setResult(false);
            result.setResultText("该用户名已经被注册。");
            return result;
        }
	    if(!user.getSurePassword().equals(user.getSurePassword())){
            result.setResult(false);
            result.setResultText("两次输入的密码不相同。");
            return result;
        }
       if(StringUtils.isNotBlank(user.getIdcard())&&!IdcardValidator.isValidate18Idcard(user.getIdcard())){
            result.setResult(false);
            result.setResultText("请输入正确的身份证号码,不支持15位");
            return result;
        }
       
       if(branchMapper.selectUserByVendorId(user.getBranch().getVendorId())!=null){
           result.setResult(false);
           result.setResultText("该公司已经被注册");
           return result;
       }
       
       //可以保存了
       if(StringUtils.isNotBlank(user.getIdcard())){
       IdcardInfoExtractor idcard = new IdcardInfoExtractor(user.getIdcard());
           user.setBirthday(idcard.getBirthdayStr());
           user.setSex(idcard.getSex());
       }
       user.setCreateDate(new Date());
       user.setEnabled(User.ENABLED.ENABLED.getValue());
       user.setIssys(User.IS_SYS.IS_SYS.getValue());
       user.setStatus(0);
       //密码加密
       user.setPassword(DESPlus.encrypt(user.getPassword()));
       result.setResult(true);
       result.setResultText("保存成功");
	    
        Branch branch = user.getBranch();
        
        branch.setCreateDate(new Date());
         branchMapper.insert(branch);
        
        user.setBranchId(branch.getId());
        
        /*user.setId(new Long(100));*/
	    userMapper.insert(user);
	    return result;
	}
}
