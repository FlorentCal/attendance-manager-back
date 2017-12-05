/**
 * 
 */
package dev.attendancemanager.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.attendancemanager.entite.Absence;
import dev.attendancemanager.entite.AbsenceStatus;
import dev.attendancemanager.entite.Ferie;
import dev.attendancemanager.entite.FerieType;
import dev.attendancemanager.entite.User;
import dev.attendancemanager.repository.AbsenceRepository;
import dev.attendancemanager.repository.FerieRepository;

/**
 * @author ETY8
 *
 */
@RestController
@RequestMapping("/feries")
@CrossOrigin(origins = "*")
public class FerieController {
	
	
		
		@Autowired
		FerieRepository ferieRepository;

		@GetMapping
		public List<Ferie> getFeries() {
			
			return ferieRepository.findAll();
		}
		
		@PostMapping()
		public List<Ferie> createAbsence(@RequestBody Ferie jf){
			Optional<Ferie> ferie = ferieRepository.findByDate(jf.getDate());
			
			if (!ferie.isPresent()){
				ferieRepository.save(jf);
				return ferieRepository.findAll();
			}
			// si la date existe déja 
			return null;
			
		}
	
		@DeleteMapping(path="/{id}")
		public Ferie deleteFerie(@PathVariable int id){
			Ferie ferie = ferieRepository.findOne(id);
			
			ferieRepository.delete(id);
			
			
			return ferie;
		}
}
