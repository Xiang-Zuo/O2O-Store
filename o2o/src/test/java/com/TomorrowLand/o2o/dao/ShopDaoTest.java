package com.TomorrowLand.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.TomorrowLand.o2o.BaseTest;
import com.TomorrowLand.o2o.entity.Area;
import com.TomorrowLand.o2o.entity.PersonInfo;
import com.TomorrowLand.o2o.entity.Shop;
import com.TomorrowLand.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("Test Store");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("123");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("pending");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);

	}
	
	@Test
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopDesc("test-update");
		shop.setShopAddr("test-update");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);

	}
}
