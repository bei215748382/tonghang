package com.tonghang.server.mapper;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tonghang.server.mybatis.handler.TestBase;
import com.tonghang.server.service.AdminService;
import com.tonghang.server.vo.TodayIncVo;

public class TPhoneMapperTest extends TestBase{
    
    @Autowired
    private TPhoneMapper tPhoneMapper;
    
    @Autowired
    private AdminService adminService;

    @Test
    public void testGetTodayNum() {
       List<TodayIncVo> list =  tPhoneMapper.getTodayNum();
       for(TodayIncVo l : list){
           System.out.println(l.toString());
       }
    }

    @Test
    public void get_inc_data(){
        System.out.println(tPhoneMapper.get_inc_data());
    }
    
    @Test
    public void getDistributionMap(){
        Map<String, Object> map = adminService.getDistributionMap();
        for (String key : map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
         }
        
    }
    
  
}
