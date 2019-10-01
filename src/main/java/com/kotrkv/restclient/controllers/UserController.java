package com.kotrkv.restclient.controllers;

import com.kotrkv.restclient.model.Role;
import com.kotrkv.restclient.model.User;
import com.kotrkv.restclient.service.RoleService;
import com.kotrkv.restclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String getUsers(Model model) {
        System.out.println("/admin");
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        return "listUsers";
    }

    @GetMapping("/users")
    public void get() {
        int i = 0;
    }

    @PostMapping("/users")
    public String updateUser(@ModelAttribute User user, @RequestParam Integer id, @RequestParam Integer roleId) {

        Role role = roleService.findById(roleId).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userService.update(user);
        return "redirect:/";
    }

    @PostMapping("/admin/addUser")
    public String add(@ModelAttribute User user, @RequestParam Integer roleId) {
        Role role = roleService.findById(roleId).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/admin/deleteUser")
    public String delete(@RequestParam Integer id) {
        userService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("error", "User not found");
        return "errorPage";
    }
}