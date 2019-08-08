package com.ssm.controller;

import com.ssm.model.User;
import com.ssm.service.UserInfo;
import com.ssm.service.UserLogin;
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
    private  final UserService userService;
    private  final UserInfo userInfo;
   private  final UserLogin userLogin;

    @Autowired(required = true)
    public UserController(UserService userService, UserInfo userInfo,UserLogin userLogin) {
        this.userService = userService;
        this.userInfo = userInfo;
        this.userLogin=userLogin;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public RespVo find(int id) {
        String string = userInfo.selectUser(id);
        return RespVo.success(string);
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public RespVo login(@RequestBody UserVo vo) {
        if (StringUtils.isEmpty(vo.getUsername())) {
            return RespVo.error("10003", "失败");
        }
        User user=new User();
        BeanUtils.copyProperties(vo, user);
        userLogin.loginUser(user);
        return RespVo.success("success");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public RespVo getAllUser() {
        List<User> user = userService.findAll();
        return RespVo.success(user);
    }

    public String toAddUser() {
        return "addUser";
    }


    //    @RequestMapping("addUser")
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespVo addUser(@RequestBody UserVo vo) {
        if (StringUtils.isEmpty(vo.getUsername())) {
            return RespVo.error("10003", "失败");
        }
        User user = new User();
        BeanUtils.copyProperties(vo, user);
        userService.saveUser(user);
        return RespVo.success("success");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public RespVo UpdateUser(@RequestBody UserVo vo) {
        if (StringUtils.isEmpty(vo.getId())) {
            return RespVo.error("10003", "失败");
        }
        User user = new User();
        user.setUsername(vo.getUsername());
        user.setUsername(vo.getUsername());
        BeanUtils.copyProperties(vo, user);
        userService.updateUser(user);
        return RespVo.success("success");
    }


    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @CrossOrigin
    @ResponseBody
    public RespVo deleteUser(int id) {
        userService.deleteUser(id);
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