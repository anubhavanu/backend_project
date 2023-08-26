package com.example.fileupload.interceptor;

import com.example.fileupload.helper.SHADecryption;
import com.example.fileupload.model.User;
import com.example.fileupload.model.UserMapping;
import com.example.fileupload.repository.UserMappingRepository;
import com.example.fileupload.repository.Userrepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//import static org.springframework.boot.jdbc.DataSourceBuilder.DataSourceProperty.PASSWORD;
//import static org.springframework.boot.jdbc.DataSourceBuilder.DataSourceProperty.USERNAME;


@Slf4j
public class generalinterceptor  implements HandlerInterceptor {
   @Autowired
     Userrepository userrepository;
    @Autowired
    UserMappingRepository userMappingRepository;

    //    private static Logger log= (Logger) LoggerFactory.getLogger(generalinterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("generalinterceptor -preHandle");
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {


//            String base64Credentials = authHeader.substring("Basic ".length());
//            byte[] decodedCredentials = Base64.getDecoder().decode(base64Credentials);
//            String credentials = new String(decodedCredentials, StandardCharsets.UTF_8);

            String[] parts = authHeader.split(":");
            String username = parts[1];


            String pass = parts[0];



            String password = SHADecryption.toHexString(SHADecryption.getSHA(pass));
            User u = userrepository.findUserByName(username);
            UserMapping l=userMappingRepository.findUserByName(username);


            if (u.getUsername().equals(username) && password.equals(u.getPassword()) && l.getRoleId().equals("admin") && l.getUsername().equals(u.getUsername())) {
                return true;
            }
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        return false;




    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("generalinterceptor -preHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("generalinterceptor -preHandle");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
