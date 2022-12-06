package b2c.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import b2c.app.model.Test;
import b2c.app.model.User;
import b2c.app.repository.TestRepo;

@Service
@Transactional
public class TestService {
	
	private TestRepo testRepo;
	
	public TestService(TestRepo testRepo) {
		this.testRepo = testRepo;
	}
	
	public void saveTest(Test test) {
		testRepo.save(test);
	}
	
	public List<Test> getAllActiveTestByUserId(Long userId) {
		return testRepo.findAllByUserIdAndActive(userId, true);
	}
	
	public List<Test> getAllInctiveTestByUserId(Long userId) {
		return testRepo.findAllByUserIdAndActive(userId, false);
	}
	
	public long deleteByOrderNumber(long order) {
		return testRepo.deleteByOrderNumber(order);
	}
	
	public Test findByOrderNumber(long orderNumber) {
		Test test = testRepo.findByOrderNumber(orderNumber);
		return test;
	}
	
	public List<Test> getAllByDate(User user) {
		return testRepo.findAllByUser(user);
	}
}