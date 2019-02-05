package fr.isima.server

import fr.isima.business.allQuotes
import fr.isima.business.toIndexedQuotes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}


@SpringBootApplication
@ComponentScan("fr.isima")
class Application {

    @Bean
    fun quotes() = allQuotes.toIndexedQuotes()
}

@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.run {
            authorizeRequests().antMatchers("/resources/**")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
        }.run {
            formLogin()
                    .successHandler { request, response, authentication ->
                        request.session.setAttribute("username", authentication.name)
                        response.sendRedirect("/main")
                    }
                    .permitAll()
                    .and()
        }.run {
            logout()
                    .permitAll()
        }

    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .inMemoryAuthentication()
                .withUser(User.withDefaultPasswordEncoder().username("grut").password("APass").roles("USER"))
                .withUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER"))
    }
}