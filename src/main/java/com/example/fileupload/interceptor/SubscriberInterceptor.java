package com.example.fileupload.interceptor;

import com.example.fileupload.model.secondary.Subscriber;
import com.example.fileupload.repository.secondary.SubscriberRepository;
import com.example.fileupload.service.OfficeService;
import com.example.fileupload.service.SubscriberInitializeService;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class SubscriberInterceptor implements HandlerInterceptor {
    @Autowired
    SubscriberRepository subscriberRepositories;

    @Autowired
    SubscriberInitializeService subscriberInitializeServices;

//    @Autowired
//    Bucket4JController bucket4JController;

    @Autowired
    OfficeService officeServices;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("SubscriberInterceptor -preHandle");
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {

            String[] parts = authHeader.split(":");
            String username = parts[1];
            String pass = parts[0];
            Subscriber s=subscriberRepositories.find_Subscriber_By_Subscriber_name(username);



            if (s!=null) {
               Bucket bucket= subscriberInitializeServices.subscriber_Initialize(s.getSubscribed_plan());
               if(!bucket.tryConsume(1))
               {
//                   return ResponseEntity.badRequest()
//
//                           .contentType(MediaType.parseMediaType("application/String"))
//                           .body("!!!!!!!SYSTEM ERROR !!!!!!!  TO MANY HITS!!!!!!   PLEASE TRY AFTER SOMETIME!!!!!");
//
                  // response.getWriter().write("!!!!!!!SYSTEM ERROR !!!!!!!  TO MANY HITS!!!!!!   PLEASE TRY AFTER SOMETIME!!!!!");
                   response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "!!!!!!!SYSTEM ERROR !!!!!!!  TO MANY HITS!!!!!!   PLEASE TRY AFTER SOMETIME!!!!!");
                   return false;
               }

//                return ResponseEntity.ok()
//
//                        .contentType(MediaType.parseMediaType("application/String"))
//                        .body("API   HIT    SUCCESSFUL");//("API   HIT    SUCCESSFUL");
 //              response.getWriter().write("API   HIT    SUCCESSFUL!!!!!!!");
                return true;
            }
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        return false;






    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("SubscriberInterceptor -preHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("SubscriberInterceptor -preHandle");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


}
