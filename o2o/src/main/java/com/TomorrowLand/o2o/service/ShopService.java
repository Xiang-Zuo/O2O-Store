package com.TomorrowLand.o2o.service;

import java.io.File;

import com.TomorrowLand.o2o.dto.ImageHolder;
import com.TomorrowLand.o2o.dto.ShopExecution;
import com.TomorrowLand.o2o.entity.Shop;

public interface ShopService {
	ShopExecution addShop(Shop shop, ImageHolder thumbnail);
}
