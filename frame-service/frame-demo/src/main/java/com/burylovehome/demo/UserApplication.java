
package com.burylovehome.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;


/**
 * 
 * [用户] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年4月16日 <br>
 * @since v1.0.0<br>
 * @see com.burylovehome.demo <br>
 */
@ComponentScan({"com.burylovehome", "com.frame"})
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
        System.out.println("******************************************************************");
        System.out.println("******************************************************************");
	    System.out.println("********Start Spring Boot Sucessfully UserApplication!!***********");
	    System.out.println("******************************************************************");
	    System.out.println("******************************************************************");
	}
}

