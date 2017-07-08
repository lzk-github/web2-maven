package com.kaishengit.service;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.kaishengit.dao.InterestDao;
import com.kaishengit.dao.InvestDao;
import com.kaishengit.entity.Interest;
import com.kaishengit.entity.Invest;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;

public class InvestService {

	private InvestDao investDao = new InvestDao();
	private InterestDao interestDao = new InterestDao();
	private InterestService interestService = new InterestService();
	private SalaryService ss = new SalaryService();
	private CustomerService customerService = new CustomerService();
	private ProjectService projectService = new ProjectService();

	/**
	 * 根据投资信息保存数据入库
	 * 
	 * @param 投资信息
	 */
	public void saveInvests(int accId, String custId, String projectId, String remainMoney, String investMoney,
			String repayRate, String months, String signDate, String endDate) {

		// --1,根据投资信息生成投资数据并入库,返回自动生成的主键值,目的是为了生成利息流水时
		int investId = investDao.save(accId, custId, projectId, investMoney, repayRate, months, signDate, endDate);

		// --2,根据投资信息生成客户利息流水,months-->for循环,生成n比流水
		interestService.addMultiplyData(custId, investId, accId, months, investMoney, repayRate, signDate, endDate);

		// --3,根据投资信息给雇员生成佣金数据
		ss.addSalaryData(accId, investId, investMoney);

		// --4,根据投资信息给客户生成积分数据并入库
		customerService.updateWithPoint(custId, investMoney);

		// --5,新增投资后,项目的剩余金额要发生改变
		projectService.updateWhenInvest(projectId, investMoney);
	}

	public Page<Invest> findPageObjByParams(String p, String key, String value) {
		int pageNo = 1;
		if (StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		// 找到筛选后的数据的条目数量
		int count = investDao.findCountByParams(key, value);

		Page<Invest> pageObj = new Page<Invest>(pageNo, count);
		int start = pageObj.getStart();
		int pageSize = pageObj.getPageSize();

		List<Invest> items = investDao.findListByParams(key, value, start, pageSize);
		pageObj.setItems(items);

		return pageObj;
	}

	/**
	 * 删除投资信息
	 * 
	 * @param id
	 */
	public void delInvestById(String investId) {
		if (StringUtils.isNumeric(investId)) {
			// 需求,判断投资后的利息是否被领取,如果领取,则不能删除;如果没有领取,则可以删除;
			// 如果没有领取利息,① 删除利息流水 ② 还原客户积分 ③ 删除佣金数据 ④ 还原project的remainmoney ⑤
			// 删除invest流水
			Invest invest = investDao.findById(investId);
			int count = interestService.findCountByParams(investId, Interest.INTEREST_TOOK);
			if (count > 0) {
				throw new ServiceException("该投资已领取过利息,不能删除!");
			} else {
				// ① 删除利息流水
				interestService.delByInvestId(investId);
				//② 还原客户积分
				customerService.reUpPointByCustIdAndInvestMoney(invest.getCustomerId(),invest.getInvestMoney());
				//③ 删除佣金数据 
				ss.delByInvestId(invest.getId());
				//④ 还原project的remainmoney
				projectService.updateWhenDeleteInvest(invest.getProjectId(),invest.getInvestMoney());
				//⑤ 删除invest流水
				investDao.delById(investId);
			}
		} else {
			throw new ServiceException("传入参数异常");
		}

	}

	public void unuseById(String investId) {
		Invest invest = investDao.findById(investId);
		if(invest != null) {
			String signDate = invest.getSignDate();
			DateTime now = new DateTime();
			DateTime signTime = new DateTime(signDate);
			int days = Days.daysBetween(signTime, now).getDays();
			if(days < 30) {
				throw new ServiceException("30天内不能解约!");
			} else {
				//把没有到期的利息流水删除
				interestDao.delByInvestIdAndState(investId,Interest.INTEREST_BEFORE_TOOKDATE);
				//改变invest的状态
				invest.setState(Invest.INVEST_STATE_UNUSE);
				investDao.update(invest);
			}
		} else {
			throw new ServiceException("传参异常!");
		}
		
	}

}














