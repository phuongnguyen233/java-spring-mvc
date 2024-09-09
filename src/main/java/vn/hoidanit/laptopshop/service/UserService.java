package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public String handleHello() {
        return "Hello from Service";
    }
    public List<User> getallUser(){
        return this.userRepository.findAll();
    }
    public List<User> getallUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }
    public User handleSaveUser(User user){
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }
    public User getInfoUserById(long id){
        return this.userRepository.findById(id);
    }
    public void deleteUserById(long id){
       this.userRepository.deleteById(id);
    }
}