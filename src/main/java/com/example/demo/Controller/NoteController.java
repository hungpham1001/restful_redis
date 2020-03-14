package com.example.demo.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.NoteDao;
import com.example.demo.Entity.Note;
import com.example.demo.Services.Key;

@RestController
@RequestMapping(path = "/note", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteController {
	@Autowired
	NoteDao noteDao;
	@Autowired
	Key key;
	@GetMapping()
	public ResponseEntity<List<Note>> findAllNote(@RequestParam("date") String date){
		List<Note> notes = noteDao.findAll(date);
		if(notes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(notes,HttpStatus.OK);
	}
	@GetMapping(params = "id")
	public ResponseEntity<Note> findById(
			@RequestParam("id") int id,
			@RequestParam("date") String date){
		Note note = noteDao.findById(date, id);
		if(note!=null) {
			return new ResponseEntity<Note>(note,HttpStatus.OK);
		}
		return new ResponseEntity<Note>(HttpStatus.NO_CONTENT);
	}
	@PostMapping()
	public ResponseEntity<Note> save(@RequestBody Note note) throws IOException {
		key.storeKey();
		note.setId(key.readKey());
		noteDao.save(note);
		return new ResponseEntity<Note>(HttpStatus.CREATED);
	}
	@PutMapping()
	public ResponseEntity<Note> update(@RequestBody Note note){
		Note oldNote = noteDao.findById(note.getNote_date(),note.getId());
		if(oldNote==null) {
			return new ResponseEntity<Note>(HttpStatus.NO_CONTENT);
		}
		SimpleDateFormat df = new SimpleDateFormat();
		Date date = new Date();
		df.applyPattern("dd-mm-yyyy");
		note.setNote_date(df.format(date));
		noteDao.save(note);
		return new ResponseEntity<Note>(HttpStatus.CREATED);
	}
	@DeleteMapping()
	public ResponseEntity<Note> delete(@RequestParam("id") int id, @RequestParam("date") String date){
		Note note = noteDao.findById(date, id);
		if(note==null) {
			return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
		}
		noteDao.delete(date, id);
		return new ResponseEntity<Note>(HttpStatus.OK);
	}
}
