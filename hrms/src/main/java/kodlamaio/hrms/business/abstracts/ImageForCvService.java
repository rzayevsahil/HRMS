package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.ImageForCv;

public interface ImageForCvService {
	
	Result add(ImageForCv jobSeekerImage,MultipartFile imageFile);
	Result update(ImageForCv imageForCv);
	Result delete(int id);
	
	DataResult<ImageForCv> getById(int id);
	DataResult<List<ImageForCv>> getAll();
	DataResult<ImageForCv> getByJobSeekerId(int id);
	
}
