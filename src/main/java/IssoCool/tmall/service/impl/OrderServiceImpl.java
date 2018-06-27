package IssoCool.tmall.service.impl;

import IssoCool.tmall.mapper.OrderMapper;
import IssoCool.tmall.pojo.Order;
import IssoCool.tmall.pojo.OrderExample;
import IssoCool.tmall.pojo.OrderItem;
import IssoCool.tmall.pojo.User;
import IssoCool.tmall.service.OrderItemService;
import IssoCool.tmall.service.OrderService;
import IssoCool.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order c) {
        orderMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    public List<Order> list(){
        OrderExample example =new OrderExample();
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);

    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public float add(Order o, List<OrderItem> ois) {
		// TODO 自动生成的方法存根
		float total=0;
		add(o);
		
		for(OrderItem oi:ois) {
			oi.setOid(o.getId());
			orderItemService.update(oi);
			total+=oi.getNumber()*oi.getProduct().getPromotePrice();
		}
		return total;
	}

	@Override
	public List list(int uid, String excludedStatus) {
		// TODO 自动生成的方法存根
		OrderExample example = new OrderExample();
		example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
		example.setOrderByClause("id desc");
		return orderMapper.selectByExample(example);
	}


}
