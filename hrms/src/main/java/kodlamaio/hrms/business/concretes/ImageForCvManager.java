package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ImageForCvService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.imageUpload.ImageUploadService;
import kodlamaio.hrms.dataAccess.abstracts.ImageForCvDao;
import kodlamaio.hrms.entities.concretes.ImageForCv;

@Service
public class ImageForCvManager implements ImageForCvService {
	
	private ImageForCvDao imageForCvDao;
	private ImageUploadService imageUploadService;
	
	@Autowired
	public ImageForCvManager(ImageForCvDao imageForCvDao, ImageUploadService imageUploadService) {
		super();
		this.imageForCvDao = imageForCvDao;
		this.imageUploadService = imageUploadService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result add(ImageForCv imageForCv, MultipartFile imageFile) {
		Map<String,String> uploadImage = this.imageUploadService.uploadImageFile(imageFile).getData();
		imageForCv.setUrl(uploadImage.get("url"));
		this.imageForCvDao.save(imageForCv);
		return new SuccessResult("Image added");
	}

	@Override
	public Result update(ImageForCv imageForCv) {
		this.imageForCvDao.save(imageForCv);
		return new SuccessResult("Image updated");
	}

	@Override
	public Result delete(int id) {
		this.imageForCvDao.deleteById(id);
		return new SuccessResult("Image deleted");
	}

	@Override
	public DataResult<ImageForCv> getById(int id) {
		return new SuccessDataResult<ImageForCv>(this.imageForCvDao.getById(id));
	}

	@Override
	public DataResult<List<ImageForCv>> getAll() {
		return new SuccessDataResult<List<ImageForCv>>(this.imageForCvDao.findAll());
	}

	@Override
	public DataResult<ImageForCv> getByJobSeekerId(int id) {
		return new SuccessDataResult<ImageForCv>(this.imageForCvDao.getByJobSeeker_id(id));
	}

}
