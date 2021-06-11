package kodlamaio.hrms.core.utilities.imageUpload;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.DataResult;

public interface ImageUploadService {

	@SuppressWarnings("rawtypes")
	DataResult<Map> uploadImageFile(MultipartFile imageFile);
}
