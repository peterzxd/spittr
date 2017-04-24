package spittr.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;



@Configuration
@EnableWebSecurity//杩欎釜娉ㄨВ鏈変竴涓甫MVC鐨勫瓙绫伙紝鍖哄埆灏辨槸瀛愮埗绫伙紝涓轰簡SpringMVC鐨勬鏋剁殑浣跨敤
public class SecurityConfig extends
        WebSecurityConfigurerAdapter{
    @Autowired
    private  DataSource dataSource;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated()//鎷︽埅鎵�鏈夎姹�
//		.and()
//		.formLogin()//鑷甫涓�涓猣orm绐椾綋
//		.and()
//		.httpBasic();//闃叉璺ㄧ珯璇锋眰
		
		
		http.formLogin()
		.loginPage("/login")
		.and()
		.logout()
		.logoutSuccessUrl("/")
		.and()
		.rememberMe()
		.tokenRepository(new InMemoryTokenRepositoryImpl())
		.tokenValiditySeconds(2419200)
		.key("spittrKey")
		.and()
		.httpBasic()
		.realmName("Spittr")
		.and()
		.authorizeRequests()
		.antMatchers("/").authenticated()
		.antMatchers("/spitter/me").authenticated()
		.antMatchers(HttpMethod.POST,"/spittles").authenticated()
		.anyRequest().permitAll()
		.and().csrf().disable();
	
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
//		//浠庡唴瀛樹腑璁よ瘉(鍙�傜敤娴嬭瘯)
//		auth.inMemoryAuthentication()
//		//娣诲姞鐢ㄦ埛锛屽瘑鐮�  roles鏄鎴�
//		.withUser("user").password("password").roles("USER")
//		.and()
//		.withUser("adimin").password("password").roles("USER","ADMIN");
//	}
//	
	//鍩轰簬鏁版嵁搴撹璇�
		
		auth.jdbcAuthentication().dataSource(dataSource)
		//鍘绘暟鎹簱鑷鏌ヨ锛宻pring涓嚜甯︾殑鏈夛紝浣嗘槸涓嶆槸姣忎釜鏁版嵁搴撻兘鏄竴鏍风殑锛屾墍浠ヨ鑷繁鍐�
		.usersByUsernameQuery("SELECT username,password,true FROM Spitter WHERE username=?")
		
		.authoritiesByUsernameQuery("SELECT username,'ROLE_USER' FROM Spitter where username=?");
		
	}
	
	
	
	
}
