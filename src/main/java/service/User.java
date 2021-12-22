package service;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"id","name","email"})
@Getter @Setter
public class User implements IUser{
    private int id;
    private String name;
    private String email;
    public User(){
    }
    public User(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public Long createUser(UserDTO data) {
        return null;
    }
}
