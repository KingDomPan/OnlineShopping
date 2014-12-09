package com.us.os.qd.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "T_SALESORDER")
public class SalesOrder {
	private String address;
	private Admin admin;
	private User buyer;
	private Integer id;
	private Set<SalesItem> items = new HashSet<SalesItem>();
	private Date odate;
	private String oid;
	private String phone;
	private String remark;
	private String status;
	private Double totalPrice;
	public String getAddress() {
		return address;
	}
	@OneToOne
	@JoinColumn(name = "adminId")
	public Admin getAdmin() {
		return admin;
	}
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getBuyer() {
		return buyer;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
	@Cascade({ CascadeType.SAVE_UPDATE })
	public Set<SalesItem> getItems() {
		return items;
	}

	public Date getOdate() {
		return odate;
	}
	public String getOid() {
		return oid;
	}
	public String getPhone() {
		return phone;
	}

	public String getRemark() {
		return remark;
	}

	public String getStatus() {
		return status;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setItems(Set<SalesItem> items) {
		this.items = items;
	}

	public void setOdate(Date odate) {
		this.odate = odate;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "SalesOrder [address=" + address + ", id=" + id + ", items="
				+ items + ", odate=" + odate + ", status=" + status + "]";
	}
}
