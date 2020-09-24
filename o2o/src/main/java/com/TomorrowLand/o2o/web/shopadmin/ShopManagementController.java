package com.TomorrowLand.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.TomorrowLand.o2o.dto.ImageHolder;
import com.TomorrowLand.o2o.dto.ShopExecution;
import com.TomorrowLand.o2o.entity.PersonInfo;
import com.TomorrowLand.o2o.entity.Shop;
import com.TomorrowLand.o2o.enums.ShopStateEnum;
import com.TomorrowLand.o2o.exceptions.ShopOperationException;
import com.TomorrowLand.o2o.service.AreaService;
import com.TomorrowLand.o2o.service.ShopService;
import com.TomorrowLand.o2o.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
//	@Autowired
//	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
//		if (!CodeUtil.checkVerifyCode(request)) {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "输入了错误的验证码");
//			return modelMap;
//		}
		// 1.接收并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "upload image cannot be empty");
			return modelMap;
		}
		// 2.注册店铺
		if (shop != null && shopImg != null) {
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwner(owner);
			ShopExecution se;
			try {
				ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
				se = shopService.addShop(shop, imageHolder);
				if (se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					// 该用户可以操作的店铺列表
//					@SuppressWarnings("unchecked")
//					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
//					if (shopList == null || shopList.size() == 0) {
//						shopList = new ArrayList<Shop>();
//					}
//					shopList.add(se.getShop());
//					request.getSession().setAttribute("shopList", shopList);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "Please enter shop info");
			return modelMap;
		}
	}


}
