package org.engina.service;

import org.engina.dto.request.DoLoginRequestDto;
import org.engina.dto.request.SaveUserRequestDto;
import org.engina.exception.ErrorType;
import org.engina.exception.UserServiceException;
import org.engina.mapper.IUserMapper;
import org.engina.repository.IUserRepository;
import org.engina.repository.entity.User;
import org.engina.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,Long> {
    private final IUserRepository repository;
    public UserService(IUserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public String saveUserDto(SaveUserRequestDto dto) {
        if (dto.getUsername().isEmpty()) {
            save(IUserMapper.INSTANCE.toUser(dto));
            return "Kayıt başarılı!";
        }
        return "Kullanıcı adı alınmış.";
    }

    public String doLogin(DoLoginRequestDto dto) {
        Optional<User> user = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user.isEmpty()) {
            throw new UserServiceException(ErrorType.DOLOGIN_USERNAMEORPASSWORD_NOTEXISTS);
        }
        return "Giriş başarılı!";
    }
}
