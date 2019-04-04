package com.sh.controller;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sh.domain.Car;
import com.sh.domain.Cart;
import com.sh.domain.Collection;
import com.sh.domain.Comment;
import com.sh.domain.Endorsement;
import com.sh.domain.OrderInfo;
import com.sh.domain.Subscribe;
import com.sh.dto.CarDTO;
import com.sh.dto.CartDataDTO;
import com.sh.dto.CommonDataDto;
import com.sh.dto.OrderDTO;
import com.sh.dto.Result;
import com.sh.dto.SubscribeCar;
import com.sh.repos.CarRepos;
import com.sh.repos.CartRepos;
import com.sh.repos.CollectionRepos;
import com.sh.repos.CommentRepos;
import com.sh.repos.EndorsementRepos;
import com.sh.repos.OrderRepos;
import com.sh.repos.SubscribeRepos;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarRepos carRepos;
	@Autowired
	private CollectionRepos collectionRepos;
	@Autowired
	private CartRepos cartRepos;
	@Autowired
	private CommentRepos commentRepos;
	@Autowired
	private OrderRepos orderRepos;
	@Autowired
	private SubscribeRepos subscribeRepos;
	@Autowired
	private EndorsementRepos endorsementRepos;

	// 添加汽车
	@RequestMapping("/add")
	public Result add(@RequestParam String name, String brand, BigDecimal price, String img, String factory,
			String factoryBg, String advantage, String defect, String consumption,
			@RequestParam String recommendedType) {
		Car car = new Car();
		car.setName(name);
		car.setBrand(brand);
		car.setPrice(price);
		car.setImg(img);
		car.setFactory(factory);
		car.setFactoryBg(factoryBg);
		car.setRecommendedAmount(new Random().nextInt(3000));
		car.setSalesVolume(new Random().nextInt(3000));
		car.setAdvantage(advantage);
		car.setDefect(defect);
		car.setMileage(new Random().nextInt(3000));
		car.setConsumption(consumption);
		car.setRecommendedType(recommendedType);
		carRepos.save(car);
		return Result.ok("添加成功");
	}

	// 根据id查询汽车
	@RequestMapping("/findById")
	public Result findById(@RequestParam long id, @RequestParam long userId) {
		CarDTO carDTO = new CarDTO();
		Car car = carRepos.getOne(id);
		carDTO.setCollected(collectionRepos.findByCarIdAndUserId(id, userId) != null);

		List<Comment> comments = commentRepos.findByCarId(car.getId(), new Sort(Sort.Direction.DESC, "createTime"));
		carDTO.setComments(comments);
		carDTO.setCar(car);
		Result result = Result.ok("获取成功");
		result.put("data", carDTO);
		return result;
	}

	// 查推荐排行
	@RequestMapping("/findTopRecommended")
	public Result findTopRecommended(String recommendedType) {
		List<Car> cars = carRepos.findByRecommendedType(recommendedType,
				new Sort(Sort.Direction.DESC, "recommendedAmount"));
		Result result = Result.ok("获取成功");
		result.put("data", cars);
		return result;
	}

	// 查销量排行
	@RequestMapping("/findTopSalesVolume")
	public Result findTopSalesVolume() {
		List<Car> cars = carRepos.findAll(new Sort(Sort.Direction.DESC, "salesVolume"));
		Result result = Result.ok("获取成功");
		result.put("data", cars);
		return result;
	}

	// 查所有及按条件查询
	@RequestMapping("/findAll")
	public Result findAll(String name, String brand, String factory, Integer fromMileage, Integer toMileage,
			Integer fromConsumption, Integer toConsumption) {
		System.err.println("name:" + name + " brand:" + brand + " factory:" + factory + " fromMileage:" + " toMileage"
				+ toMileage + " fromConsumption:" + fromConsumption + " toConsumption:" + toConsumption);
		List<Car> cars = carRepos.findAll((root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (null != name && !name.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
			}
			if (null != brand && !brand.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("brand"), "%" + brand + "%"));
			}
			if (null != factory && !factory.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("factory"), "%" + factory + "%"));
			}
			if (null != fromMileage && fromMileage != 0) {
				predicates.add(criteriaBuilder.between(root.get("mileage"), fromMileage, toMileage));
			}
			if (null != fromConsumption) {
				predicates.add(criteriaBuilder.between(root.get("consumption"), fromConsumption, toConsumption));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		});

		Result result = Result.ok("获取成功");
		result.put("data", cars);
		return result;
	}

	// 收藏汽车
	@RequestMapping("/collect/add")
	public Result addCollect(@RequestParam long carId, @RequestParam long userId) {
		try {
			Collection collection = new Collection();
			collection.setCarId(carId);
			collection.setUserId(userId);
			collectionRepos.save(collection);
			Result result = Result.ok("收藏成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.error("不能重复收藏");
	}

	// 取消收藏汽车
	@RequestMapping("/collect/cancel")
	public Result cancelCollect(@RequestParam long id) {
		collectionRepos.deleteById(id);
		Result result = Result.ok("取消收藏成功");
		return result;
	}

	// 获取所有收藏的汽车
	@RequestMapping("/collect/getAll")
	public Result getAllCollect(@RequestParam long userId) {
		List<CommonDataDto> data = collectionRepos.findCollection(userId);
		Result result = Result.ok("获取成功");
		result.put("data", data);
		return result;
	}

	// 加入购物车
	@RequestMapping("/cart/add")
	public Result addToCart(@RequestParam long carId, @RequestParam long userId, @RequestParam String insurance,
			@RequestParam String amount, @RequestParam String returnDate) throws ParseException {
		Cart cart = new Cart();
		cart.setCarId(carId);
		cart.setUserId(userId);
		cart.setInsurance(insurance);
		cart.setAmount(new BigDecimal(amount));
		cart.setReturnDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(returnDate));
		cartRepos.save(cart);
		Result result = Result.ok("加入购物车成功");
		return result;
	}

	// 从购物车移除
	@RequestMapping("/cart/cancel")
	public Result removeFromCart(@RequestParam long id) {
		cartRepos.deleteById(id);
		Result result = Result.ok("移除成功");
		return result;
	}

	// 查看已加入购物车中的汽车
	@RequestMapping("/cart/getAll")
	public Result getCart(@RequestParam long userId) {
		List<CartDataDTO> data = cartRepos.getCart(userId);
		Result result = Result.ok("获取成功");
		result.put("data", data);
		return result;
	}

	// 提交订单
	@RequestMapping("/order/add")
	@Transactional
	public Result createOrder(@RequestParam long carId, @RequestParam long userId, @RequestParam String price,
			@RequestParam String insurance, @RequestParam String returnDate) {
		try {
			Car car = carRepos.getOne(carId);
			if (car.isRent()) {
				return Result.error("该汽车已被租出");
			}
			if (car.isSub()) {
				return Result.error("该汽车已被预约");
			}
			car.setSalesVolume(car.getSalesVolume() + 1);
			car.setRent(true);
			carRepos.save(car);
			// 从购物车中删除
			cartRepos.deleteByCarIdAndUserId(carId, userId);

			OrderInfo order = new OrderInfo();
			order.setCarId(carId);
			order.setUserId(userId);
			order.setAmount(new BigDecimal(price));
			order.setInsurance(insurance);
			order.setReturnDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(returnDate));
			order.setStatus(0);
			order.setCreateTime(new Date());
			orderRepos.save(order);
			Result result = Result.ok("提交成功");
			return result;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return Result.error("操作失败");
	}

	// 删除订单
	@RequestMapping("/order/delete")
	@Transactional
	public Result deleteOrder(@RequestParam long id) {
		try {
			OrderInfo orderInfo = orderRepos.getOne(id);
			if (orderInfo.getStatus() != 1) {
				return Result.error("订单未结束，不能删除");
			}
			orderRepos.deleteById(id);
			commentRepos.deleteByOrderId(id);
			Result result = Result.ok("删除成功");
			return result;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return Result.error("操作失败");
	}

	// 查询所有订单
	@RequestMapping("/order/getAll")
	public Result getAllOrder(@RequestParam long userId) {
		List<OrderDTO> orders = orderRepos.findOrderDTOs(userId);
		Result result = Result.ok("获取成功");
		result.put("data", orders);
		return result;
	}

	// 结束行程，评价订单
	@RequestMapping("/order/finish")
	@Transactional
	public Result finishOrder(@RequestParam long id, @RequestParam long userId, @RequestParam long carId,
			String content) {
		try {
			Comment comment = new Comment();
			comment.setCarId(carId);
			comment.setContent(content);
			comment.setCreateTime(new Date());
			comment.setOrderId(id);
			comment.setUserId(userId);
			commentRepos.save(comment);

			OrderInfo order = orderRepos.getOne(id);
			order.setStatus(1);
			orderRepos.save(order);

			Car car = carRepos.getOne(carId);
			car.setRent(false);
			car.setSub(false);
			car.setMileage(car.getMileage() + new Random().nextInt(100) + 100);
			carRepos.save(car);
			Result result = Result.ok("操作成功");
			return result;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return Result.error("操作失败");
	}

	// 预约汽车
	@RequestMapping("/sub/add")
	@Transactional
	public Result sub(@RequestParam long carId, @RequestParam long userId, @RequestParam String subDate,
			@RequestParam String insurance, @RequestParam String amount, @RequestParam String returnDate) {
		try {

			Car car = carRepos.getOne(carId);
			if (car.isRent()) {
				return Result.error("该汽车已被租出");
			}
			if (car.isSub()) {
				return Result.error("该汽车已被预约");
			}
			car.setSub(true);
			carRepos.save(car);

			Subscribe subscribe = new Subscribe();
			subscribe.setCarId(carId);
			subscribe.setUserId(userId);
			subscribe.setSubDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(subDate));
			subscribe.setInsurance(insurance);
			subscribe.setAmount(new BigDecimal(amount));
			subscribe.setReturnDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(returnDate));
			subscribeRepos.save(subscribe);
			Result result = Result.ok("预约成功");
			return result;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return Result.error("预约失败");
	}

	// 查看预约的汽车
	@RequestMapping("/sub/getAllByUser")
	@Transactional
	public Result getSub(@RequestParam long userId) {
		try {
			// 查出所有的预约信息，将过期的对应汽车更新为未预约
			List<Subscribe> subscribeAll = subscribeRepos.findAll();
			for (Subscribe subscribe : subscribeAll) {
				if (!subscribe.isOvertime() && subscribe.getSubDate().before(new Date())) {
					Car car = carRepos.getOne(subscribe.getCarId());
					car.setSub(false);
					carRepos.save(car);
					subscribe.setOvertime(true);
					subscribeRepos.save(subscribe);
				}
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}

		List<Subscribe> subscribes = subscribeRepos.findByUserId(userId);
		List<SubscribeCar> subscribeCars = new ArrayList<>();
		for (Subscribe subscribe : subscribes) {
			SubscribeCar subscribeCar = new SubscribeCar();
			subscribeCar.setSubscribeId(subscribe.getId());
			subscribeCar.setUserId(userId);
			subscribeCar.setSubDate(subscribe.getSubDate());
			subscribeCar.setOvertime(subscribe.isOvertime());
			subscribeCar.setInsurance(subscribe.getInsurance());
			subscribeCar.setReturnDate(subscribe.getReturnDate());
			Car car = carRepos.getOne(subscribe.getCarId());
			subscribeCar.setCarId(car.getId());
			subscribeCar.setName(car.getName());
			subscribeCar.setBrand(car.getBrand());
			subscribeCar.setPrice(subscribe.getAmount());
			subscribeCar.setImg(car.getImg());

			subscribeCars.add(subscribeCar);
		}
		return Result.ok("获取成功").put("data", subscribeCars);
	}

	// 从预约页面开始行程
	@RequestMapping("/sub/addOrder")
	@Transactional
	public Result createOrderFromSub(@RequestParam long subscribeId, @RequestParam long carId,
			@RequestParam long userId, @RequestParam String price) {
		try {
			Car car = carRepos.getOne(carId);
			car.setRent(true);
			car.setSub(false);
			carRepos.save(car);

			OrderInfo order = new OrderInfo();
			order.setCarId(carId);
			order.setUserId(userId);
			order.setAmount(new BigDecimal(price));
			order.setStatus(0);
			order.setCreateTime(new Date());
			orderRepos.save(order);

			subscribeRepos.deleteById(subscribeId);
			Result result = Result.ok("提交成功");
			return result;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return Result.error("提交失败");
	}

	// 添加违章记录
	@RequestMapping("/endorsement/add")
	public Result addEndorsement(long carId, long userId, String name, String brand, String info, String date)
			throws ParseException {
		Endorsement endorsement = new Endorsement();
		endorsement.setCarId(carId);
		endorsement.setUserId(userId);
		endorsement.setName(name);
		endorsement.setBrand(brand);
		endorsement.setInfo(info);
		endorsement.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
		endorsementRepos.save(endorsement);
		return Result.ok("保存成功");
	}

	// 通过用户id查询违章记录
	@RequestMapping("/endorsement/getByUserId")
	public Result getEndorsementByUserId(long userId) {
		List<Endorsement> endorsements = endorsementRepos.findByUserId(userId);
		return Result.ok("获取成功").put("data", endorsements);
	}
}
