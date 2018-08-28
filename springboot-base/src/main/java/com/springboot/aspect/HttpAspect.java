package com.springboot.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {
	
	/**
	 * 在spring框架配置AOP的时候，不管是XML配置文件还是注解的方式都需要定义pointcut切入点
	 * 例如：
	 * 		execution(public * com.springboot.controller..*.*(..))
	 * 	1.execution(): 表达式主体
	 * 	2.第一个*号：表示返回类型， *号表示所有的类型
	 *  3.包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，
	 *  		com.springboot.controller包、子孙包下所有类的方法。
	 *  4.第二个*号：表示类名，*号表示所有的类
	 *  5.*(..)：最后这个星号表示方法名，*号表示所有的方法， *后面括弧里面表示方法的参数，
	 *  		  两个点表示任何参数
	 */
	@Pointcut("execution(public * com.springboot.controller.HelloController.*(..))")
	public void pointCut() {
		//AOP切点
	}
	
	@Before("pointCut()")
	public void doBefore() {
		System.out.println("======== 执行Before方法 =========");
	}
	
	@After("pointCut()")
	public void doAfter() {
		System.out.println("======== 执行After方法 =========");
	}
	
}
