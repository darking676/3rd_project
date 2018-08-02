package com.bit.shop01.cart.model.entity;

public class CartVo3 {

	private int basketNum;
	private String memId;
	private int productNum;
	private int quantity;

	public CartVo3() {
		// TODO Auto-generated constructor stub
	}

	public CartVo3(int basketNum, String memId, int productNum, int quantity) {
		super();
		this.basketNum = basketNum;
		this.memId = memId;
		this.productNum = productNum;
		this.quantity = quantity;
	}

	public int getBasketNum() {
		return basketNum;
	}

	public void setBasketNum(int basketNum) {
		this.basketNum = basketNum;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartVo [basketNum=" + basketNum + ", memId=" + memId + ", productNum=" + productNum + ", quantity="
				+ quantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + basketNum;
		result = prime * result + ((memId == null) ? 0 : memId.hashCode());
		result = prime * result + productNum;
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartVo3 other = (CartVo3) obj;
		if (basketNum != other.basketNum)
			return false;
		if (memId == null) {
			if (other.memId != null)
				return false;
		} else if (!memId.equals(other.memId))
			return false;
		if (productNum != other.productNum)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
}
