package io.github.rura6502.spring_05_mvc_02_locale;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  /**
   * USING SESSION firstly, read locale from session secondly, read
   * 'accept-language' in header
   */
   @Bean("sessionLocaleResolver")
  public LocaleResolver sessionLocaleResolever() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver(); // if session is not, read 'accep-language'
                                                                        // header
    localeResolver.setDefaultLocale(new Locale("en"));
    return localeResolver;
  }

  /**
   * USING COOKIE firstly, read locale from cookie secondly, read
   * 'accept-language' in header
   */
  @Bean("cookieLocaleResolever")
  public LocaleResolver cookieLocaleResolever() {
    CookieLocaleResolver localeResolver = new CookieLocaleResolver(); // if cookie is not, read 'accep-language' header
    localeResolver.setDefaultLocale(new Locale("en"));
    localeResolver.setCookieName("language");
    localeResolver.setCookieMaxAge(3600); // 쿠키 유지 시간, -1일 경우 브라우저 종료 시 쿠키 삭제
    localeResolver.setDefaultLocale(new Locale("en"));
    return localeResolver;
  }

  @Configuration
  class I18nConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public LocaleChangeInterceptor localChangeInterceptor() {
      LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
      localeChangeInterceptor.setParamName("language");
      return localeChangeInterceptor;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
      CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
      cookieLocaleResolver.setCookieName("language");
      cookieLocaleResolver.setDefaultLocale(new Locale("en"));
      return cookieLocaleResolver;
    }
  }
}
