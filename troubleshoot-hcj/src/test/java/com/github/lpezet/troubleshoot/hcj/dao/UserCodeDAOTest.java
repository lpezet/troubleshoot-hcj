package com.github.lpezet.troubleshoot.hcj.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.github.lpezet.troubleshoot.hcj.be.UserCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class UserCodeDAOTest {
	@Autowired
	private IUserCodeDAO mDAO;
	
	@Autowired
	private DBUtils mDBUtils;

	@Before
	@BeforeTransaction
	public void setup() throws Exception {
		mDBUtils.validateDatabase();
	}

	@Test
	public void load() throws Exception {
		UserCode u = mDAO.findOne(1l);
		assertNotNull(u);
		assertEquals(1, u.getTypeId());
	}

	@Test
	public void save() throws Exception {
		UserCode oCode = new UserCode();
		oCode.setCode("A1B2C3D4E5F6");
		oCode.setUsername("hello");
		oCode.setTypeId(1);
		oCode = mDAO.save(oCode);
		assertPersisted(oCode);
	}
	
	@Test
	public void options() {
		UserCode oCode = new UserCode();
		oCode.setOptions(Collections.singletonMap("toto", (Object) "titi"));
		oCode.setUsername("hello");
		oCode.setCode("A1B2C3D4E5F7");
		oCode.setTypeId(1);
		oCode = mDAO.save(oCode);
		assertPersisted(oCode);
	}

	private void assertPersisted(UserCode pBE) {
		assertNotNull(pBE);
		assertTrue(pBE.getId() > 0);
	}
}
