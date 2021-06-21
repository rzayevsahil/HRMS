package kodlamaio.hrms.business.concretes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.entities.concretes.City;

@Service
public class CityManager implements CityService{

	private CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		super();
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<List<City>> getAll() {
		return new SuccessDataResult<List<City>>(this.cityDao.findAll(),"Data listelendi");
	}

	@Override
	public Result add(City city) throws FileNotFoundException, DocumentException {
		Result result=BusinessRules.run(cityNameExists(city));
		
		//InputStreamReader input = new InputStreamReader(new FileInputStream("input.txt"));
		if (result.isSuccess()) {
			String cityNameUpperCase=city.getName().toUpperCase();
			city.setName(cityNameUpperCase);
			this.cityDao.save(city);
			return new SuccessResult("City added");
		}
		String cityNameUpperCase=city.getName().toUpperCase();
		city.setName(cityNameUpperCase);
		/*Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/repos/hrms-backend/hrms/Pdf/merhaba.pdf"));
			document.open();
			document.add(new Paragraph(city.getName()));
			document.close();
			writer.close();
			//PdfPTable table=new PdfPTable(null);
		} catch (DocumentException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		

		/*Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk(city.getName(), font);*/

		
		
		return result;
	}

	@Override
	public Result update(City city) {
		Result result=BusinessRules.run(cityNameExists(city));
		if (result.isSuccess()) {
			this.cityDao.save(city);
			return new SuccessResult("City updated");
		}
		return result;
	}

	@Override
	public Result delete(int cityId) {
			this.cityDao.deleteById(cityId);
			return new SuccessResult("City deleted");		
	}
	
	@Override
	public DataResult<City> getById(int id) {
		return new SuccessDataResult<City>(this.cityDao.getById(id));
	}

	
	//********************** Kurallar *****************************
	
	private Result cityNameExists(City city) {
		if(cityDao.findAllByName(city.getName().toUpperCase()).stream().count()!=0) {
			return new ErrorResult("This CityName is available");
		}
		return new SuccessResult();
	}
	
}
