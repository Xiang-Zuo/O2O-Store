package com.TomorrowLand.o2o.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TomorrowLand.o2o.dao.ShopDao;
import com.TomorrowLand.o2o.dto.ImageHolder;
import com.TomorrowLand.o2o.dto.ShopExecution;
import com.TomorrowLand.o2o.entity.Shop;
import com.TomorrowLand.o2o.enums.ShopStateEnum;
import com.TomorrowLand.o2o.exceptions.ShopOperationException;
import com.TomorrowLand.o2o.service.ShopService;
import com.TomorrowLand.o2o.util.ImageUtil;
import com.TomorrowLand.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	
//	@Override
//	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
//		//将页码转换成行码
//		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
//		//依据查询条件，调用dao层返回相关的店铺列表
//		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
//		//依据相同的查询条件，返回店铺总数
//		int count = shopDao.queryShopCount(shopCondition);
//		ShopExecution se = new ShopExecution();
//		if (shopList != null) {
//			se.setShopList(shopList);
//			se.setCount(count);
//		} else {
//			se.setState(ShopStateEnum.INNER_ERROR.getState());
//		}
//		return se;
//	}
//
//	@Override
//	public Shop getByShopId(long shopId) {
//		return shopDao.queryByShopId(shopId);
//	}
//
//	@Override
//	@Transactional
//	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
//		if (shop == null || shop.getShopId() == null) {
//			return new ShopExecution(ShopStateEnum.NULL_SHOP);
//		} else {
//			// 1.判断是否需要处理图片
//			try {
//				if (thumbnail.getImage() != null && thumbnail.getImageName() != null
//						&& !"".equals(thumbnail.getImageName())) {
//					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
//					if (tempShop.getShopImg() != null) {
//						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
//					}
//					addShopImg(shop, thumbnail);
//				}
//				// 2.更新店铺信息
//				shop.setLastEditTime(new Date());
//				int effectedNum = shopDao.updateShop(shop);
//				if (effectedNum <= 0) {
//					return new ShopExecution(ShopStateEnum.INNER_ERROR);
//				} else {
//					shop = shopDao.queryByShopId(shop.getShopId());
//					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
//				}
//			} catch (Exception e) {
//				throw new ShopOperationException("modifyShop error:" + e.getMessage());
//			}
//		}
//	}

	
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("Shop Creation failed");
			}else {
				if (thumbnail.getImage() != null) {
					try {
						addShopImg(shop, thumbnail);
					} catch (Exception e) {
						throw new ShopOperationException("Add shopImg failed: " + e.getMessage());
					}
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("Update shopImg failed");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		// TODO Auto-generated method stub
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(shopImgAddr);
	}

}
