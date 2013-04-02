package com.manpowergroup.cn.icloud.sys.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.sys.entity.Organ;
import com.manpowergroup.cn.icloud.sys.entity.User;
import com.manpowergroup.cn.icloud.sys.model.Tree;
import com.manpowergroup.cn.icloud.sys.service.OrganService;
import com.manpowergroup.cn.icloud.sys.service.UserService;

public class OrganAction extends BaseAction{

	private static final long serialVersionUID = -7872270951525919673L;
	
	@Autowired
	private OrganService organService;
	
	@Autowired
    private UserService userService;
	
	//@Autowired
	//private BranchService branchService;
	
	//private List<Branch> branchList;
	private Organ organ;
	private Page<Organ> page;
	private List<User> userList;

	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Page<Organ> getPage() {
		return page;
	}

	public void setPage(Page<Organ> page) {
		this.page = page;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	/*public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}*/

	public String page() throws Exception {
		queryProvinceByCondition();
		return "page";
	}
	
	public String query() throws Exception{
		queryProvinceByCondition();
		return "list"; 
	}
	
	/**
	 * 新增或修改操作
	 * @return
	 * @throws Exception
	 */
	public String edit()
	{
		if( organ!=null && organ.getId()!=null ){
			organ=organService.getOrganById(organ.getId());
		}else{
			organ=new Organ();
		}
		organ.setBranchId(this.getCurrBranchId());
		return "edit";
	}
	
	/**
	 * 详细页面跳转方法
	 * @return
	 * @throws Exception
	 */
	public String detail()throws Exception{
		this.organ = this.organService.getOrganById(organ.getId());
		return "detail";
	}
	
	/**
	 * 保存更新后的资源信息
	 * @return
	 */
	public String save() throws Exception
	{
			Result result = organService.save(organ,this.getCurrOperatorId(),this.getCurrBranchId());
			Struts2Utils.renderJson(result, headers);
			//resourceList=resourceService.getResourceList();
			return null;
	}
	
	/**
	 * 按公司编号查找用户并构建树形结构
	 * @return
	 * @throws Exception
	 */
	public String queryAllTreeByBranchId() throws Exception{
		List<Tree> trees= organService.queryAllTreeByBranchId(this.getCurrBranchId());
		List<Organ> organList=null;
		Organ tempOrgan=null;
		if (organ.getId()!=null){
		 tempOrgan=organService.queryOrganById(organ.getId());
		 organList=organService.queryOrganIdsById(organ.getId());
		}
		if(organ!=null&&organ.getId()!=null){
			for(int i=0;i<trees.size();i++){
				if(Integer.valueOf(trees.get(i).getId()).longValue()==tempOrgan.getParentId()){
					trees.get(i).setChecked(true);
				}
			}
		}
		if(organList!=null&& organList.size()!=0){
			for(int x=0;x<organList.size();x++){
				for(int y=0;y<trees.size();y++){
					if(Integer.valueOf(trees.get(y).getId()).longValue()==organList.get(x).getId()){
					trees.get(y).setNocheck(true);
					}
				}
			}
		}
		Struts2Utils.renderText(jsonMapper.toJson(trees), headers);
		return null;
	}
	
	
	
	
	/**
	 * 按公司编号和多个组织编号查找组织架构并构建树形结构
	 * @return
	 * @throws Exception
	 */
	public String queryAllTreeByBranchIdAndIds() throws Exception{
		List<Tree> trees= organService.queryAllTreeByBranchId(this.getCurrBranchId());
		String id=request.getParameter("orgId");
		if(id!=null&& id!=""){
		String[] ids=id.split(",");
		if(ids!=null&&ids.length>0){
			for(int x=0;x<ids.length;x++){
				for(int y=0;y<trees.size();y++){
					if(Integer.valueOf(trees.get(y).getId()).longValue()==Long.parseLong(ids[x])){
					trees.get(y).setChecked(true);
					}
				}
			}
		}
		}
		
		
		
		
		
		
		
		
		Struts2Utils.renderText(jsonMapper.toJson(trees), headers);
		return null;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 查询功能
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void queryProvinceByCondition() throws Exception{
		page = this.initPage(page);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//查询条件
		parameters.put("name", request.getParameter("name"));
		parameters.put("remark", request.getParameter("remark"));
		parameters.put("branchId", this.getCurrBranchId());
		
		page = organService.queryProvinceByCondition(parameters,page); 
	}
	
	/**
	 * 分页刷新
	 * @return
	 * @throws Exception
	 */
	public String prepaging() throws Exception{
		queryProvinceByCondition();
		return "list";
	}
	
	/**
	 * 调用层级页面显示树形结构
	 * @return
	 * @throws Exception
	 */
	public String level() throws Exception{
		return "level";
	}
	
	/**
	 * 显示该组织架构下的所有用户信息
	 * @return
	 * @throws Exception
	 */
	public String searchUser() throws Exception{
		
		userList=userService.queryUserByOrganId(organ.getId());
		
		return "searchUser";
	}
}
