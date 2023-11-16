package com.example.ex01.Controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;
@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping(value = "/errorPage",method = RequestMethod.GET)
    public String errorPage(HttpServletRequest httpServletRequest , Model model){
        String errorMsg = "";
        int HttpErrorCode = getErrorCode(httpServletRequest);
        switch (HttpErrorCode){
            case 400 : {
                errorMsg = "http error code: 400 . Bad requesr";
                break;
            }
            case 401 : {
                errorMsg = "http error code: 401 . Unauthorized";
                break;
            }
            case 404 : {
                errorMsg = "http error code: 404 . Resource not found";
                break;
            }
            case 500 : {
                errorMsg = "http error code: 500 . Internal Server Error";
                break;
            }
        }
        model.addAttribute("errorMsg" , errorMsg);
        return "errorPage";
    }
    private int getErrorCode(HttpServletRequest httpServletRequest){
        Object errorCode = httpServletRequest.getAttribute(ERROR_STATUS_CODE);
        return errorCode == null ? 0 : (Integer)errorCode;
    }
}
