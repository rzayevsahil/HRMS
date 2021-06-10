package kodlamaio.hrms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ImageForCvService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.ImageForCv;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@RestController
@RequestMapping("/api/images/")
public class ImagesController {

	private ImageForCvService imageForCvService;
	private JobSeekerService jobSeekerService;

	@Autowired
	public ImagesController(ImageForCvService imageForCvService, JobSeekerService jobSeekerService) {
		super();
		this.imageForCvService = imageForCvService;
		this.jobSeekerService = jobSeekerService;
	}

	@PostMapping("add")
	public Result add(@RequestParam(value = "jobseekerId") int id, @RequestParam(value = "imageFile") MultipartFile imageFile) {
		JobSeeker jobSeeker = this.jobSeekerService.getById(id).getData();
		ImageForCv imageForCv = new ImageForCv();
		imageForCv.setJobSeeker(jobSeeker);
		return this.imageForCvService.add(imageForCv, imageFile);
	}

	@PutMapping("update")
	public Result update(@RequestBody ImageForCv imageForCv) {
		return this.imageForCvService.update(imageForCv);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestParam("id") int id) {
		return this.imageForCvService.delete(id);
	}

	@GetMapping("getById")
	public DataResult<ImageForCv> getById(@RequestParam("id") int id) {
		return this.imageForCvService.getById(id);
	}

	@GetMapping("getall")
	public DataResult<List<ImageForCv>> getAll() {
		return this.imageForCvService.getAll();
	}

	@GetMapping("getByJobSeekerId")
	public DataResult<ImageForCv> getByJobSeekerId(@RequestParam int id) {
		return this.imageForCvService.getByJobSeekerId(id);
	}

}