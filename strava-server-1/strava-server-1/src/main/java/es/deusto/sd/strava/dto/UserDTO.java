package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.repository.*;
import java.util.ArrayList;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;


import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.UserChallenge;

public class UserDTO implements UserRepository{

	private String username;
	private String token;

	private ArrayList<UserChallenge> challenges;
	private float weight; //in kg
	private float height; //in cm
	private int maxheartRate; //bpm
	private int restHeartRate; //bpm
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public ArrayList<UserChallenge> getChallenges() {
		return challenges;
	}
	public void setChallenges(ArrayList<UserChallenge> challenges) {
		this.challenges = challenges;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public int getMaxheartRate() {
		return maxheartRate;
	}
	public void setMaxheartRate(int maxheartRate) {
		this.maxheartRate = maxheartRate;
	}
	public int getRestHeartRate() {
		return restHeartRate;
	}
	public void setRestHeartRate(int restHeartRate) {
		this.restHeartRate = restHeartRate;
	}



	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <S extends User> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends User> java.util.List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteAllInBatch(Iterable<User> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public User getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends User> java.util.List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends User> java.util.List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends User> java.util.List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public java.util.List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public java.util.List<User> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends User> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public java.util.List<User> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends User> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends User> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <S extends User> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public <S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public java.util.List<User> findByEmail(String Email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public java.util.List<User> findByUsername(String Username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
