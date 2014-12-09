package com.shopOL.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.shopOL.dao.AdminDao;
import com.shopOL.dao.UserDao;

public class SalesOrderBean {
	private String address;
	private int adminId;
	private int userId;
	private Integer id;
	private String oid;
	private String totalPrice;
	private String odate;
	private String status;
	private String adminName;
	private String userName;
	private String phone;
	private String remark;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
		adminName=AdminDao.getAdminBean(adminId).getName();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
		userName=UserDao.getUserBean(userId).getUsername();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		String fmt = "yyyy-MM-dd HH:mm:SS";
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		Date date;
		try {
			date = sdf.parse(odate);
			this.odate = sdf.format(date);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if (status.equals("FIN")) {
			this.status="待处理订单";
		} else if(status.equals("END")){
			this.status="已处理订单";
		}
	}

	public String getAdminName() {
		return adminName;
	}

	public String getUserName() {
		return userName;
	}
	
	@Override
	public String toString() {
		return "SalesOrderBean [address=" + address + ", adminId=" + adminId
				+ ", userId=" + userId + ", id=" + id + ", oid=" + oid
				+ ", totalPrice=" + totalPrice + ", odate=" + odate
				+ ", status=" + status + ", adminName=" + adminName
				+ ", userName=" + userName + ", phone=" + phone + ", remark="
				+ remark + "]";
	}
}
