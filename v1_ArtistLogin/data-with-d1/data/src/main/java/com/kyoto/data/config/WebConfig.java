package com.kyoto.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AdminInterceptor adminInterceptor;

    // Uncomment this if you are also using the ArtistInterceptor we built earlier!
    // @Autowired
    // private ArtistInterceptor artistInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 🔒 ADMIN SECURITY RULES
        // Protect the dashboard and all admin action routes (like delete/create)
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/adminDashboard", "/admin/**")
                .excludePathPatterns("/adminLogin"); // Never block the login page itself!

        // 🔒 ARTIST SECURITY RULES (If you are keeping the artist side)
        // registry.addInterceptor(artistInterceptor)
        //         .addPathPatterns("/artistdashboard", "/addSong", "/uploadSong", "/artist/**")
        //         .excludePathPatterns("/artistlogin", "/artistsignup", "/playSong", "/album/**");
    }
}