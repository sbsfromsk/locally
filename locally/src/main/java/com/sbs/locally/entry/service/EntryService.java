package com.sbs.locally.entry.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.locally.entry.entity.Entry;
import com.sbs.locally.entry.repository.EntryRepositroy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntryService {
	
	private final EntryRepositroy entryRepository;
	
	public List<Entry> findById(Integer id) {
		
		return entryRepository.findAllById(id);
	}

	
}
