package com.kaishengit.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.AccDao;
import com.kaishengit.dao.CompanyDao;
import com.kaishengit.entity.Acc;
import com.kaishengit.entity.Company;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;

public class AccService {

	private AccDao ad = new AccDao();
	private CompanyDao cd = new CompanyDao();

	public void save(String name, String idNo, String tel, String companyId, String dept) throws RuntimeException {
		Acc acc = ad.findByIdNo(idNo);

		if (acc == null) {
			acc = new Acc();
			acc.setName(name);
			acc.setIdNo(idNo);
			acc.setTel(tel);
			acc.setCompanyId(Integer.parseInt(companyId));
			acc.setDept(dept);
			acc.setPassword(DigestUtils.md5Hex(Config.get("password.salt") + "123456"));

			ad.save(acc);
		} else {
			throw new RuntimeException("身份证号已存在!保存失败");
		}
	}

	public Page<Acc> findByPageNo(int pageNo) {
		// 先找到总数据条目数
		int total = ad.findTotalCount();
		// 获取开始数据
		Page<Acc> page = new Page<>(pageNo, total);
		List<Acc> list = ad.findByPage(page.getStart(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	public Page<Acc> findByParams(int pageNo, String attr, String value) throws RuntimeException {
		if (StringUtils.isNotEmpty(attr) && attr.equals("company_id")) {
			if (StringUtils.isNotEmpty(value)) {
				Company com = cd.findByCompanyName(value);
				if (com != null) {
					value = String.valueOf(com.getId());
				} else {
					throw new RuntimeException("没有该公司信息!");
				}
			}
		}

		int count = ad.findCountByParams(attr, value);

		Page<Acc> page = new Page<>(pageNo, count);
		int start = page.getStart();
		int pageSize = page.getPageSize();
		List<Acc> list = ad.findByMultiParams(attr, value, start, pageSize);
		page.setItems(list);

		return page;

		/*
		 * if(StringUtils.isEmpty(attr) || StringUtils.isEmpty(value)) { return
		 * findByPageNo(pageNo); }
		 * //如果选择通过公司名字查询list列表,那么先要找到公司的这个名字对应Acc表中的id,目的只返回Acc对象
		 * if(attr.equals("company_id")) { Company company =
		 * cd.findByCompanyName(value); if(company == null) { return null; }
		 * else { int id = company.getId(); value = String.valueOf(id); } }
		 */
		// 现在有3个参数,pageNo,key=列名,value=列名对应的值,根据这3个参数组成sql语句,去数据库中
		// 查List<Acc>列表,然后把这个list放到page里,用page.setItems();
		// TODO List<Acc> accs = ad.findByParams(pageNo,attr,value);
		// 应该先找到在满足attr=value的时候有多少条数据,然后根据page类得到limit ?, ?(start,pagesize)
		/*
		 * int count = ad.findCountByParams(attr,value); Page<Acc> page = new
		 * Page<>(pageNo,count); int start = page.getStart(); List<Acc> accs =
		 * ad.findByParams(attr, value, start,page.getPageSize());
		 * page.setItems(accs); return page;
		 */
	}

	public void delAccById(int id) {
		Acc acc = ad.findById(id);
		if (acc != null) {
			ad.delById(id);
		} else {
			throw new RuntimeException("该id没有对应数据!");
		}
	}

	public Acc findById(int id) {
		Acc acc = ad.findById(id);
		if (acc == null) {
			throw new RuntimeException("没有此id数据");
		} else {

			return acc;
		}
	}

	public void edit(String id,String companyId, String dept) {
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(companyId) && StringUtils.isNotEmpty(dept)) {
			if(StringUtils.isNumeric(id) && StringUtils.isNumeric(companyId)) {
				Acc acc = ad.findById(Integer.parseInt(id));
				if(acc != null) {
					acc.setCompanyId(Integer.parseInt(companyId));
					acc.setDept(dept);
					ad.update(acc);
				} else {
					
					throw new RuntimeException("没有该id对应的数据!");
				}
			} else {
				throw new RuntimeException("请传入正确的公司id!");
			}
		} else {
			throw new RuntimeException("请传入正确的参数(公司/部门)!");
		}
		
	}

	public Acc login(String name, String password,String remoteIp) {
		Acc acc = ad.findByName(name);
		if(password != null) {
			password = DigestUtils.md5Hex(Config.get("password.salt") + password);
		}
		if(acc != null && acc.getPassword().equals(password)) {
			
			acc.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
			acc.setLastLoginIp(remoteIp);
			ad.update(acc);
			
			return acc;
		} else {
			throw new ServiceException("账号或密码错误!");
		}
			
	}

}
