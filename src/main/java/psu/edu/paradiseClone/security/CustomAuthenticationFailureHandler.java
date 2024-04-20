package psu.edu.paradiseClone.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import psu.edu.paradiseClone.entity.Customer;
import psu.edu.paradiseClone.service.CustomerService;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private CustomerService customerService;

    public CustomAuthenticationFailureHandler(CustomerService theCustomerService) {
        customerService = theCustomerService;
    }


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
        System.out.println("In customAuthenticationFailureHandler");
        System.out.println(exception.getMessage());

//        String userName = authentication.getName();
//
//        System.out.println("userName=" + userName);
//
//        Customer theCustomer = customerService.findByEmail(userName);
//
//        // now place in the session
//        HttpSession session = request.getSession();
//        session.setAttribute("user", theCustomer);

        // forward to home page
        response.sendRedirect(request.getContextPath() + "/");
		
	}
}
