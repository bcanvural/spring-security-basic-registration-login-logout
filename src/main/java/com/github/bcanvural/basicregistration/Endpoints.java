package com.github.bcanvural.basicregistration;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.github.bcanvural.basicregistration.exceptions.EmailExistsException;

@Controller
public class Endpoints {

    private final UserService service;

    @Autowired
    public Endpoints(final UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @RequestMapping(value = "/secured", method = RequestMethod.GET)
    public String showSecuredPaged(WebRequest request, Model model) {
        return "secured";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result, WebRequest request, Errors errors) {

        User registered = new User();

        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }

        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }

        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        } else {
            return new ModelAndView("successRegister", "", null);
        }
    }

    private User createUserAccount(final UserDto accountDto, final BindingResult result) {
        User registered;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }
}
