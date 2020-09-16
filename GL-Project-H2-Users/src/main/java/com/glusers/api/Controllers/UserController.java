package com.glusers.api.Controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.glusers.api.model.Phone;
import com.glusers.api.model.User;
import com.glusers.api.model.Validations;
import com.glusers.api.exceptions.ResourceNotFoundException;
import com.glusers.api.exceptions.Response;
import com.glusers.api.repository.PhoneRepository;
import com.glusers.api.repository.UserRepository;
import com.glusers.api.security.model.AuthenticationRequest;
import com.glusers.api.security.model.AuthenticationResponse;
import com.glusers.api.security.services.MyUserDetailsService;
import com.glusers.api.security.services.util.JwtUtil;


@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PhoneRepository phoneRepository;
	private String Token;
	Validations val = new Validations();
	Response res;
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
		);
		}catch(BadCredentialsException e){
			throw new Exception("Incorrect username or password",e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		Token = jwt;
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
	    // get all users
		@GetMapping("/all")	
		public Response getAllUsers(){
			
			List<User> lu= this.userRepository.findAll();
			if(lu.isEmpty()) {
				logger.info("Lista vacia de usuarios");
				res = new Response("Lista vacia de usuarios");
			}
			else {
				
				res = new Response(lu);
				
			}
			return res;
		}
		
		//get user by id
		@GetMapping("/{id}")
		
		public Response users(@PathVariable (value = "id")Integer userId) {
			//return this.userRepository.findById(userId)
			//		.orElseThrow(() -> new ResourceNotFoundException("User not found with id :"+userId));
			Optional<User> usr = this.userRepository.findById(userId);
			if(usr != null) {
				res = new Response(usr);
			}
			else {
				res = new Response("No se ha encontrado usuario con id "+userId);
				logger.error("No se ha encontrado usuario con id "+userId);
			}
			return res;
		}
		

		
		//create user
		@PostMapping("/create")		
		public Response createUser(@RequestBody User user) throws Exception {
			String usrname ="";
			//validar email y password antes de guardar el dato
			List<Object> valEmail = val.validaEmail(user.getEmail());
			boolean vEmail = (boolean) valEmail.get(0);
			usrname = (String)valEmail.get(valEmail.size()-1);
			
			if(!vEmail) {
				valEmail.remove(0);
				valEmail.add(0, "Error en verificación de email");
				logger.error("Error en verificación de email");
				res =new Response(valEmail);
				return res;
			}
			//validar password
			List<Object> valpass = val.validaPass(user.getPassword());
			boolean vpass = (boolean)valpass.get(0);
			if(!vpass) {
				valpass.remove(0);
				valpass.add(0, "Error en validación de password");
				logger.error("Error en validación de password");
				
				res=new Response(valpass);
				return res;
			}
			    user.setToken(Token);
			//validar que no este repetido el mail
			List<User> allusers = this.userRepository.findAll();
			if(allusers.size()>0) {
				for(User u : allusers){
					if(u.getEmail().equals(user.getEmail())) {
						res = new Response("El correo ya esta registrado");
						logger.error("Error el correo"+user.getEmail()+" ya esta registrado");
						return res;
					}
					
				}
			}
			//guardar los phones en phoneRepository
			List<Phone> phones = user.getPhones();
			for(Phone ph: phones) {
				
				this.phoneRepository.save(ph);
			}
			return new Response(this.userRepository.save(user));
		}
		
		//update user
		@PutMapping("/{id}")
		
		public Response updateUser(@RequestBody User user, @PathVariable("id") Integer userId) {
		    //no esta dentro del objetivo de la evaluacion, pero debe grabarse sobre la misma id
			//que tenia inicialmente, ver con cuidado la actualizacion de los telefonos, por ahora
			//no funciona, pero le falta poco.
			
			User existingUser = this.userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id :"+userId));
			
				
				existingUser.setName(user.getName());
				existingUser.setEmail(user.getEmail());
				existingUser.setModified(user.getDateandTimeNow());
				existingUser.setLast_login(user.getDateandTimeNow());
				existingUser.setPassword(user.getPassword());
				existingUser.setPhones(user.getPhones());
			//guardar los phones
			List<Phone> phones = user.getPhones();
			for(Phone ph: phones) {
				this.phoneRepository.save(ph); 
				
			}
			
			
			res = new Response(this.userRepository.save(existingUser));
			return res;
		}
		//delete user by id
		@DeleteMapping("/{id}")
		
		public Response deleteUser(@PathVariable ("id") Integer userId){
			List <Object> mylistResponse = new ArrayList<Object>();
			User existingUser = this.userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id :"+userId));
			
			this.userRepository.delete(existingUser);
			mylistResponse.add("User "+userId+" fue eliminado exitosamente");
			mylistResponse.add(ResponseEntity.ok().build());
			res = new Response(mylistResponse);
			return res;
		}

}
