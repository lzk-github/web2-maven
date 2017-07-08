package com.kaishengit.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.ProjectDao;
import com.kaishengit.entity.Project;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;

public class ProjectService {

	private ProjectDao pd = new ProjectDao();
	public Page<Project> findPageByParams(int pageNo, String key, String value) {
		//1,需求,根据前端传来的pageno,key\value获取Pagelist集合,list里面有List<Project>集合
		//2,先获取list集合,然后set到page里面,
		//3,怎么获取list集合?
		//4,通过sql查询,select * from t_project where key=value limit ?,?;
		//5,获取limit后面的开始行数及其pagesize
		int count = pd.countByParams(key,value);
		Page<Project> page = new Page<>(pageNo,count);
		int start = page.getStart();
		int pageSize = page.getPageSize();
		List<Project> list = pd.findListByParams(key,value,start,pageSize);
		page.setItems(list);
		return page;
		
		
	}
	public void saveByMap(Map<String, String> map) {
		if(StringUtils.isNotEmpty(map.get("projectName"))) {
			Project project = pd.findByName(map.get("projectName"));
			if(project != null) {
				throw new ServiceException("项目已存在!");
			} else {
				project = new Project();
				project.setProjectName(map.get("projectName"));
				project.setSumMoney(Double.parseDouble(map.get("sumMoney")));
				project.setRemainMoney(Double.parseDouble(map.get("remainMoney")));
				project.setRepayRate(Double.parseDouble(map.get("repayRate")));
				project.setMonths(Integer.parseInt(map.get("months")));
				project.setStartDate(map.get("startDate"));
				project.setEndDate(map.get("endDate"));
				project.setRemark(map.get("remark"));
				
				pd.save(project);
			}
		} else {
			throw new ServiceException("传入参数错误!");
		}
		
	}
	/**
	 * 获取正常状态的项目列表
	 * @return
	 */
	public List<Project> findNormalList() {
		
		return pd.findNormarl();
	}
	public Project findById(String projectId) {
		
		return pd.findById(projectId);
	}
	/**
	 * 当发起一个客户发起投资请求时,对应项目的剩余可投资金额需改变
	 * @param projectId
	 * @param investMoney
	 */
	public void updateWhenInvest(String projectId, String investMoney) {
		Project project = pd.findById(projectId);
		double remainMoney = project.getRemainMoney();
		project.setRemainMoney(remainMoney - Double.parseDouble(investMoney));
		pd.update(project);
		
	}
	public void updateWhenDeleteInvest(int projectId, int investMoney) {
		Project pro = pd.findById(String.valueOf(projectId));
		pro.setRemainMoney(pro.getRemainMoney() + investMoney);
		pd.update(pro);
	}

	
}
