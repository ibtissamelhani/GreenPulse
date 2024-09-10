package service;

import entities.Consumption;
import entities.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String cin, String name , int age) {
        User newUser = new User();
        newUser.setCin(cin);
        newUser.setName(name);
        newUser.setAge(age);
        return userRepository.save(newUser);
    }

    public void deleteUser(String cin) {
        Optional<User> deletedUsers =  getUserByCin(cin);
        if(deletedUsers.isPresent()) {
            System.out.println(" " + deletedUsers.get().getCin() + " " + deletedUsers.get().getName());
            userRepository.delete(cin);
        }else {
            System.out.println(" user not found");
        }

    }

    public User updateUser(String cin, String name , int age) {
        User newUser = new User();
        newUser.setCin(cin);
        newUser.setName(name);
        newUser.setAge(age);
        return userRepository.update(newUser);
    }

    public List<User> getAllUsers() {
         return userRepository.findAll();
    }

    public Optional<User> getUserByCin(String cin) {
        return userRepository.findByCin(cin);
    }

    public List<User> getUsersWithConsumptions() {
        return userRepository.getAllUsersWithConsumptions();
    }

    public List<User> getUsers() {

        List<User> users = getUsersWithConsumptions();
        return users.stream()
                .filter(user ->
                        user.getConsumptions()
                                .stream()
                                .mapToDouble(Consumption::getConsumptionImpact)
                                .sum() > 3000
                        ).collect(Collectors.toList());

    }
}
