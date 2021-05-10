package com.example.euris.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.euris.dao.IDaoArithmetic;

@RestController
@RequestMapping("/arithmetic")

public class ContollerArithmetic {
	
	@Autowired
	private IDaoArithmetic dao;

	
    @GetMapping("/sum/{cost1}/{cost2}")
    public String sum(@PathVariable String cost1,@PathVariable String cost2) {
        return dao.sum(cost1, cost2);
    }
	
    
    @GetMapping("/sub/{cost1}/{cost2}")
    public String sub(@PathVariable String cost1,@PathVariable String cost2) {
        return dao.sub(cost1, cost2);
    }
	
    
    @GetMapping("/mul/{cost1}/{number}")
    public String mul(@PathVariable String cost1,@PathVariable int number) {
        return dao.mul(cost1, number);
    }
	
    
    @GetMapping("/div/{cost1}/{number}")
    public String div(@PathVariable String cost1,@PathVariable int number) {
        return dao.div(cost1, number);
    }
	
    
    
    
}

