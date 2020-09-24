package com.TomorrowLand.o2o.enums;

public enum ShopStateEnum {
	CHECK(0, "Pending"), OFFLINE(-1, "Illegal"), SUCCESS(1, "Success"), PASS(2, "Pass"), INNER_ERROR(-1001,
			"Interal Error"), NULL_SHOPID(-1002, "Empty shopId"),NULL_SHOP(-1003, "Empty shop info");
	private int state;
	private String stateInfo;

	private ShopStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/**
	 * 依据传入的state返回相应的enum值
	 */
	public static ShopStateEnum stateOf(int state) {
		for (ShopStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
}
