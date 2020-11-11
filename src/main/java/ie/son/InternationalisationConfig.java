package ie.son;

import java.util.Locale;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
// Spring can now intercept requests, facilitate language change requests 
// using the "lang=" parameter in the URI and resolve those requests to 
// produce views in the appropriate messages contained in the
// message.properties files.
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
public class InternationalisationConfig implements WebMvcConfigurer  {
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci= new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	/* Using /index?lang=fr system */
	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver= new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.UK);
		return localeResolver;
	}


	//		<!--  adapted from https://stackoverflow.com/questions/27623405/thymeleaf-add-parameter-to-current-url -->

	@Bean
	public Function<String, String> currentUrlWithoutParam() {
		return param -> ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam(param).toUriString();
	}

}
