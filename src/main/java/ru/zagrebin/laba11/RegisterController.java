package ru.zagrebin.laba11;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.zagrebin.laba11.security.UserRepository;
import ru.zagrebin.laba11.security.UserRecord;

@Controller
public class RegisterController {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public RegisterController(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("username", "");
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@RequestParam String username,
                                 @RequestParam String password,
                                 Model model) {
        // простая валидация
        if (username == null || username.isBlank() || password == null || password.length() < 8) {
            model.addAttribute("error", "Имя пользователя обязательно, пароль минимум 8 символов");
            model.addAttribute("username", username);
            return "register";
        }

        // проверка уникальности
        if (userRepo.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            model.addAttribute("username", username);
            return "register";
        }

        // сохранение с BCrypt
        UserRecord user = new UserRecord();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole("ROLE_USER");
        userRepo.save(user);

        // после регистрации можно перенаправить на логин
        return "redirect:/login";
    }
}