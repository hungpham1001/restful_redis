package com.example.demo.Dao;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.Note;

@Service
public class NoteDao {
	@Resource(name = "redisTemplate")
	HashOperations<String, Integer, Note> hashOperations;
	
	public List<Note> findAll(String date){
		List<Note> notes = hashOperations.values(date);
		return notes;
	}
	public Note findById(String date, Integer id) {
		Map<Integer, Note> map = hashOperations.entries(date);
		if(map.isEmpty()||!map.containsKey(id)) {
			return null;
		}
		return map.get(id);
	}
	public void save(Note note) {
		hashOperations.put(note.getNote_date(), note.getId(), note);
	}
	public void update(Note note) {
		this.delete(note.getNote_date(), note.getId());
		this.save(note);
	}
	public void delete(String date, Integer id) {
		hashOperations.delete(date, id);
	}
}
