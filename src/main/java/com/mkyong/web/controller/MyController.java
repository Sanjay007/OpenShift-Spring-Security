package com.mkyong.web.controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.mkyong.security.TokenTransfer;
import com.mkyong.security.TokenUtils;
import com.mkyong.users.dao.UserDao;


@Component
@Path("/user")
public class MyController {
	
	@Autowired
	UserDetailsService myUserDetailsService;
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDao userDao;
		
	/*@RequestMapping(value = "/rest/user/auth", method = RequestMethod.POST)
    public ResponseEntity<TokenTransfer> listAllUsers(@PathVariable("username")String UserName,@PathVariable("password")String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(UserName, password);
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
//       // UserDetails users =new us myUserDetailsService.loadUserByUsername("mkyong");
//		
//      System.out.println( userDao.loadAll().size());
//      
//      User u=new User("sanju", "GG", true);
// 
//    
//
//    userDao.updateUser(u);
		 
       // return new ResponseEntity<String>("ggg", HttpStatus.OK);
		
		UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(UserName);

		TokenTransfer t=new TokenTransfer(TokenUtils.createToken(userDetails));
		
		
     
    }
	*/
	@Path("authenticate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TokenTransfer authenticate(@FormParam("username") String username, @FormParam("password") String password)
	{
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
		UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);

		return new TokenTransfer(TokenUtils.createToken(userDetails));
	}
	
	
}
