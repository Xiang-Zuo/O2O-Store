package com.TomorrowLand.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.TomorrowLand.o2o.BaseTest;
import com.TomorrowLand.o2o.dto.ImageHolder;
import com.TomorrowLand.o2o.dto.ShopExecution;
import com.TomorrowLand.o2o.entity.Area;
import com.TomorrowLand.o2o.entity.PersonInfo;
import com.TomorrowLand.o2o.entity.Shop;
import com.TomorrowLand.o2o.entity.ShopCategory;
import com.TomorrowLand.o2o.enums.ShopStateEnum;
import com.TomorrowLand.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop() throws ShopOperationException, FileNotFoundException {
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
		shop.setShopName("Test Store 1");
		shop.setShopDesc("test 1");
		shop.setShopAddr("test 1");
		shop.setPhone("123");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("pending");
		File shopImg = new File("/Users/xiangzuo/Desktop/JavaSpringBootProject/Image/coffee.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
		ShopExecution se = shopService.addShop(shop, imageHolder );
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
	
}
