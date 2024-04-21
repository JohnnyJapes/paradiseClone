package psu.edu.paradiseClone.security;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import psu.edu.paradiseClone.entity.Customer;
import psu.edu.paradiseClone.service.CustomerService;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private CustomerService customerService;

    public CustomAuthenticationSuccessHandler(CustomerService theCustomerService) {
        customerService = theCustomerService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        System.out.println("In customAuthenticationSuccessHandler");

        String userName = authentication.getName();

        System.out.println("userName=" + userName);

        Customer theCustomer = customerService.findByEmail(userName);

        // now place in the session
        HttpSession session = request.getSession();
        session.setAttribute("user", theCustomer);

        // forward to home page
        response.sendRedirect(request.getContextPath() + "/home?success");
    }

}