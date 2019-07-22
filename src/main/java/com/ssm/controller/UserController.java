package com.ssm.controller;
import com.ssm.model.User;
import com.ssm.service.UserInfo;
import com.ssm.service.UserService;
import com.ssm.vo.RespVo;
import com.ssm.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    private final UserInfo userInfo;

    public UserController(UserService userService, UserInfo userInfo) {
        this.userService = userService;
        this.userInfo = userInfo;
    }

    @RequestMapping(value="/find",method= RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public RespVo find(int id){
        String string=userInfo.selectAll(id);
        System.err.print("1111111111111111");
        return  RespVo.success(string);
    }

    @RequestMapping(value="/list",method= RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public RespVo getAllUser(){
        List<User> user = userService.findAll();
        return RespVo.success(user);
    }

    public String toAddUser(){
        return "addUser";
    }


//    @RequestMapping("addUser")
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public RespVo addUser(@RequestBody UserVo vo){
        if (StringUtils.isEmpty(vo.getUsername())) {
            return RespVo.error("10003", "????????");
        }
        User user = new User();
//       user.setMobile(vo.getMobile());
//       user.setName(vo.getName());
        BeanUtils.copyProperties(vo, user);
        //user.setIsDel(0);
        userService.saveUser(user);;
        return RespVo.success("success");
    }

    @RequestMapping(value="/update",method= RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public RespVo UpdateUser(@RequestBody UserVo vo){
        if (StringUtils.isEmpty(vo.getId())) {
            return RespVo.error("10003", "失败");
        }
        User user = new User();
        user.setUsername(vo.getUsername());
        user.setUsername(vo.getUsername());
        BeanUtils.copyProperties(vo, user);
        //user.setIsDel(0);
        userService.saveUser(user);;
        return RespVo.success("success");

        //             if(userService.updateUser(user)){
        //                    user = userService.findUserById(user.getId());
        //                    model.addAttribute("user", user);
        //                    return "redirect:/user/userInfo";
        //                }
        //         return "/error";
    }


    @RequestMapping(value="/del",method= RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public RespVo deleteUser(Integer id){
        return RespVo.success("success");
    }

//    @RequestMapping("userInfo")
//    public String getUsers(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model){
//        //????????? ?????????????
//        PageHelper.startPage(pn, 5);
//        List<User> users = userService.findAll();
//        //????????????PageInfo??????
//        PageInfo page = new PageInfo(users,5);
//        model.addAttribute("pageInfo", page);
//        return "allUser";
//    }

}