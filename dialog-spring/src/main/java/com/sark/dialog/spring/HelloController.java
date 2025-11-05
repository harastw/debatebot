// com/sark/dialog/spring/HelloController.java
package com.sark.dialog.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
    	int counter = 0;
    	for (int i = 0; i < 10; i++) {
    		counter++;
    	}
    	String str = String.valueOf(counter);
        return str;
    }
}