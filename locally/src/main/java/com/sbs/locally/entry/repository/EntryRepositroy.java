package com.sbs.locally.entry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.locally.entry.entity.Entry;

public interface EntryRepositroy extends JpaRepository<Entry, Integer>{

	List<Entry> findAllById(Integer id);

}
