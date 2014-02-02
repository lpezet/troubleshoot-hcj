package com.github.lpezet.troubleshoot.hcj.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IBaseDAO<T, Key extends Serializable> extends JpaRepository<T, Key>, JpaSpecificationExecutor<T> {

}
