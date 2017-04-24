package spittr.web;

import java.util.Date;
import java.util.List;

import org.h2.engine.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.domain.Spittle;
import spittr.repository.SpittleRepository;



@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private SpittleRepository spittleRepository;
	

	public SpittleRepository getSpittleRepository() {
		return spittleRepository;
	}

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		super();
		this.spittleRepository = spittleRepository;
	}

	@RequestMapping(method = RequestMethod.GET) 
	public List<Spittle> spittles(
			@RequestParam(value = "count", defaultValue = "20") int count) { // spittleList
		return spittleRepository.findBySpitterId(count);
	}

	@RequestMapping(value = "/{spittleId}")
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {

		Spittle spittle = spittleRepository.findOne(spittleId);
		if (spittle == null) {
			throw new SpittleNotFoundException();
		}
		model.addAttribute(spittle);
		return "spittle";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveSpittle(SpittleForm form, Model model,Session s) {
		try {
			spittleRepository
					.save(new Spittle(null,form.getMessage(), new Date(),form.getLongitude(), form.getLatitude(),form.getSpitter()));
			return "redirect:/spittles";
		} catch (DuplicateSpittleException e) {
			return "error/duplicate";
		}
	}

}
