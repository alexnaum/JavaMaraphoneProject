package service;

import dto.UserDTO;

interface IUser {
    UserDTO getUserById(Long id);
    Long createUser(UserDTO data);
}
