package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserRepository;
import com.example.entity.User;
import com.example.util.EncryptUtil;


@Service
public class UserServiceImpl {
	
	@Autowired
	UserRepository userRepository;
	
	@Cacheable("userCache")
	public User login(String username, String password){
		String passcode = EncryptUtil.getStrSHA(password);
		List<User> list = UserRepository.findByUsernameAndPasscode(username, passcode);
		/*if(list!=null && list.size() == 1){
			return list.get(0);
		}else*/
		return (list!=null && list.size() == 1) ? list.get(0) : null;
	}
	/**
	 * 
		属性	类型	描述
		value	String	可选的限定描述符，指定使用的事务管理器
		propagation	enum: Propagation	可选的事务传播行为设置
		isolation	enum: Isolation	可选的事务隔离级别设置
		readOnly	boolean	读写或只读事务，默认读写
		timeout	int (in seconds granularity)	事务超时时间设置
		rollbackFor	Class对象数组，必须继承自Throwable	导致事务回滚的异常类数组
		rollbackForClassName	类名数组，必须继承自Throwable	导致事务回滚的异常类名字数组
		noRollbackFor	Class对象数组，必须继承自Throwable	不会导致事务回滚的异常类数组
		noRollbackForClassName	类名数组，必须继承自Throwable	不会导致事务回滚的异常类名字数组
	 */
	@Transactional(rollbackFor=Exception.class)
	public User register(User user){
		User u = null;
		try{
			 u = userRepository.save(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return u;
	}
	
}
